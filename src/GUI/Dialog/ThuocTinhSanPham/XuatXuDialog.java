package GUI.Dialog.ThuocTinhSanPham;

import BUS.NhomQuyenBUS;
import BUS.XuatXuBUS;
import DAO.XuatXuDAO;
import DTO.ThuocTinhSanPham.XuatXuDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Panel.QuanLyThuocTinhSP;
import com.formdev.flatlaf.FlatIntelliJLaf;
import helper.Validation;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class XuatXuDialog extends JDialog implements MouseListener {

    HeaderTitle headTitle;
    JPanel top, main, bottom;
    InputForm ms;
    DefaultTableModel tblModel;
    JTable table;
    JScrollPane scrollTable;
    ButtonCustom add, del, update;
    XuatXuBUS msBUS = new XuatXuBUS();
    List<XuatXuDTO> list = msBUS.getAll(); // Sử dụng List thay vì ArrayList
    QuanLyThuocTinhSP qltt;
    private final NhomQuyenBUS nhomquyenBus = new NhomQuyenBUS();

    public XuatXuDialog(JFrame owner, QuanLyThuocTinhSP qltt, String title, boolean modal, int nhomquyen) {
        super(owner, title, modal);
        initComponent(qltt);
        loadQuyen(nhomquyen);
        loadDataTable(list);
    }

    public void loadQuyen(int nhomquyen) {
        if (!nhomquyenBus.checkPermisson(nhomquyen, "thuoctinh", "create")) {
            add.setVisible(false);
        }
        if (!nhomquyenBus.checkPermisson(nhomquyen, "thuoctinh", "delete")) {
            del.setVisible(false);
        }
        if (!nhomquyenBus.checkPermisson(nhomquyen, "thuoctinh", "update")) {
            update.setVisible(false);
        }
    }

    public void initComponent(QuanLyThuocTinhSP qltt) {
        this.qltt = qltt;
        this.setSize(new Dimension(425, 500));
        this.setLayout(new BorderLayout(0, 0));
        this.setResizable(false);
        headTitle = new HeaderTitle("XUẤT XỨ SẢN PHẨM");
        this.setBackground(Color.white);
        top = new JPanel();
        main = new JPanel();
        bottom = new JPanel();

        top.setLayout(new GridLayout(1, 1));
        top.setBackground(Color.WHITE);
        top.setPreferredSize(new Dimension(0, 70));
        top.add(headTitle);

        main.setBackground(Color.WHITE);
        main.setPreferredSize(new Dimension(420, 200));
        ms = new InputForm("Xuất xứ");
        ms.setPreferredSize(new Dimension(250, 70));
        table = new JTable();
        table.setBackground(Color.WHITE);
        table.addMouseListener(this);
        scrollTable = new JScrollPane(table);
        scrollTable.setBackground(Color.WHITE);
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã xuất xứ", "Nơi xuất xứ"};
        tblModel.setColumnIdentifiers(header);
        table.setModel(tblModel);
        scrollTable.setViewportView(table);
        scrollTable.setPreferredSize(new Dimension(420, 250));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        main.add(ms);
        main.add(scrollTable);

        add = new ButtonCustom("Thêm", "excel", 15, 100, 40);
        add.addMouseListener(this);
        del = new ButtonCustom("Xóa", "danger", 15, 100, 40);
        del.addMouseListener(this);
        update = new ButtonCustom("Sửa", "success", 15, 100, 40);
        update.addMouseListener(this);
        bottom.setBackground(Color.white);
        bottom.setLayout(new FlowLayout(1, 20, 20));
        bottom.add(add);
        bottom.add(del);
        bottom.add(update);
        bottom.setPreferredSize(new Dimension(0, 70));

        this.add(top, BorderLayout.NORTH);
        this.add(main, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
    }

    public void loadDataTable(List<XuatXuDTO> result) { // Sử dụng List thay vì ArrayList
        tblModel.setRowCount(0);
        for (XuatXuDTO ncc : result) {
            tblModel.addRow(new Object[]{
                ncc.getMaxuatxu(), ncc.getTenxuatxu()
            });
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == add) {
            if (Validation.isEmpty(ms.getText())) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập nơi xuất xứ mới");
            } else {
                String tenxuatxu = ms.getText();
                if (msBUS.checkDup(tenxuatxu)) {
                    int id = XuatXuDAO.getInstance().getAutoIncrement();
                    msBUS.add(new XuatXuDTO(id, tenxuatxu));
                    list = msBUS.getAll();  // Làm mới danh sách sau khi thêm
                    loadDataTable(list);
                    ms.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Xuất xứ đã tồn tại !");
                }
            }
        } else if (e.getSource() == del) {
            int index = getRowSelected();
            if (index != -1) {
                msBUS.delete(list.get(index), index);
                list = msBUS.getAll();  // Làm mới danh sách sau khi xóa
                loadDataTable(list);
                ms.setText("");
            }
        } else if (e.getSource() == update) {
            int index = getRowSelected();
            if (index != -1) {
                if (Validation.isEmpty(ms.getText())) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập nơi xuất xứ mới");
                } else {
                    String tenxuatxu = ms.getText();
                    if (msBUS.checkDup(tenxuatxu)) {
                        msBUS.update(new XuatXuDTO(list.get(index).getMaxuatxu(), tenxuatxu));
                        list = msBUS.getAll();  // Làm mới danh sách sau khi cập nhật
                        loadDataTable(list);
                        ms.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Xuất xứ đã tồn tại !");
                    }
                }
            }
        } else if (e.getSource() == table) {
            int index = table.getSelectedRow();
            if (index != -1) {
                ms.setText(list.get(index).getTenxuatxu());
            }
        }
    }

    public int getRowSelected() {
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn xuất xứ!");
        }
        return index;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Không cần thực hiện gì ở đây
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Không cần thực hiện gì ở đây
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Không cần thực hiện gì ở đây
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Không cần thực hiện gì ở đây
    }
}
