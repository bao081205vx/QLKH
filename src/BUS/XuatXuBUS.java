package BUS;

import DAO.XuatXuDAO;
import DTO.ThuocTinhSanPham.XuatXuDTO;
import java.util.ArrayList;
import java.util.List;

public class XuatXuBUS {
    private XuatXuDAO xuatxuDAO = XuatXuDAO.getInstance();
    private List<XuatXuDTO> listXuatXu;

    public XuatXuBUS() {
        this.listXuatXu = xuatxuDAO.selectAll();
    }

    public List<XuatXuDTO> getAll() {
        return new ArrayList<>(this.listXuatXu); // Trả về bản sao để đảm bảo tính bất biến
    }

    public String[] getArrTenXuatXu() {
        return this.listXuatXu.stream()
                              .map(XuatXuDTO::getTenxuatxu)
                              .toArray(String[]::new);
    }

    public XuatXuDTO getByIndex(int index) {
        if (index >= 0 && index < this.listXuatXu.size()) {
            return this.listXuatXu.get(index);
        }
        return null; // Hoặc ném ngoại lệ
    }

    public boolean add(XuatXuDTO xuatxu) {
        boolean check = xuatxuDAO.insert(xuatxu) != 0;
        if (check) {
            this.listXuatXu = xuatxuDAO.selectAll(); // Làm mới danh sách
        }
        return check;
    }

    public boolean delete(XuatXuDTO xuatxu, int index) {
        boolean check = xuatxuDAO.delete(Integer.toString(xuatxu.getMaxuatxu())) != 0;
        if (check) {
            this.listXuatXu = xuatxuDAO.selectAll(); // Làm mới danh sách
        }
        return check;
    }

    public int getIndexByMaXX(int maxx) {
        for (int i = 0; i < this.listXuatXu.size(); i++) {
            if (listXuatXu.get(i).getMaxuatxu() == maxx) {
                return i;
            }
        }
        return -1;
    }

    public String getTenXuatXu(int maxx) {
        int index = this.getIndexByMaXX(maxx);
        if (index != -1) {
            return this.listXuatXu.get(index).getTenxuatxu();
        }
        return null; // Hoặc ném ngoại lệ
    }

    public boolean update(XuatXuDTO xuatxu) {
        int index = getIndexByMaXX(xuatxu.getMaxuatxu());
        if (index != -1) {
            boolean check = xuatxuDAO.update(xuatxu) != 0;
            if (check) {
                this.listXuatXu = xuatxuDAO.selectAll(); // Làm mới danh sách
            }
            return check;
        }
        return false; // Không tìm thấy đối tượng để cập nhật
    }

    public boolean checkDup(String name) {
        return this.listXuatXu.stream()
                              .noneMatch(dto -> dto.getTenxuatxu().equalsIgnoreCase(name));
    }
}
