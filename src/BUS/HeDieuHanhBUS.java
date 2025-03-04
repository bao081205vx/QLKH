package BUS;

import DAO.HeDieuHanhDAO;
import DTO.ThuocTinhSanPham.HeDieuHanhDTO;
import java.util.ArrayList;

public class HeDieuHanhBUS {

    private HeDieuHanhDAO hdhDAO = new HeDieuHanhDAO();
    private ArrayList<HeDieuHanhDTO> listHeDieuHanh = new ArrayList<>();

    public HeDieuHanhBUS() {
        this.listHeDieuHanh = hdhDAO.selectAll();
    }

    public ArrayList<HeDieuHanhDTO> getAll() {
        return this.listHeDieuHanh;
    }

    public String[] getArrTenHeDieuHanh() {
        String[] result = new String[listHeDieuHanh.size()];
        for (int i = 0; i < listHeDieuHanh.size(); i++) {
            result[i] = listHeDieuHanh.get(i).getTenhdh();
        }
        return result;
    }

    public HeDieuHanhDTO getByIndex(int index) {
        if (index >= 0 && index < listHeDieuHanh.size()) {
            return this.listHeDieuHanh.get(index);
        } else {
            // Xử lý lỗi nếu chỉ mục không hợp lệ
            return null;
        }
    }

    public boolean add(HeDieuHanhDTO hdh) {
        boolean check = hdhDAO.insert(hdh) != 0;
        if (check) {
            this.listHeDieuHanh.add(hdh);
        }
        return check;
    }

    public boolean delete(HeDieuHanhDTO hdh, int index) {
        if (index >= 0 && index < this.listHeDieuHanh.size()) {
            boolean check = hdhDAO.delete(Integer.toString(hdh.getMahdh())) != 0;
            if (check) {
                this.listHeDieuHanh.remove(index);
            }
            return check;
        } else {
            // Xử lý lỗi nếu chỉ mục không hợp lệ
            return false;
        }
    }

    public int getIndexByMaMau(int mahdh) {
        for (int i = 0; i < this.listHeDieuHanh.size(); i++) {
            if (listHeDieuHanh.get(i).getMahdh() == mahdh) {
                return i;
            }
        }
        return -1; // Không tìm thấy
    }

    public String getTenHdh(int mahdh) {
        int index = this.getIndexByMaMau(mahdh);
        if (index != -1) {
            return this.listHeDieuHanh.get(index).getTenhdh();
        } else {
            // Xử lý lỗi nếu không tìm thấy
            return null;
        }
    }

    public boolean update(HeDieuHanhDTO hdh) {
        int index = getIndexByMaMau(hdh.getMahdh());
        if (index != -1) {
            boolean check = hdhDAO.update(hdh) != 0;
            if (check) {
                this.listHeDieuHanh.set(index, hdh);
            }
            return check;
        } else {
            // Không tìm thấy đối tượng để cập nhật
            return false;
        }
    }

    public boolean checkDup(String name) {
        for (HeDieuHanhDTO item : this.listHeDieuHanh) {
            if (item.getTenhdh().toLowerCase().contains(name.toLowerCase())) {
                return false; // Tồn tại bản sao
            }
        }
        return true; // Không tìm thấy bản sao
    }
}
