package BUS;

import DAO.ThuongHieuDAO;
import DTO.ThuocTinhSanPham.ThuongHieuDTO;
import java.util.ArrayList;

public class ThuongHieuBUS {

    private final ThuongHieuDAO lhDAO = ThuongHieuDAO.getInstance();
    private ArrayList<ThuongHieuDTO> listLH = new ArrayList<>();

    public ThuongHieuBUS() {
        listLH = lhDAO.selectAll();
    }

    public ArrayList<ThuongHieuDTO> getAll() {
        return this.listLH;
    }

    public ThuongHieuDTO getByIndex(int index) {
        // Kiểm tra chỉ số hợp lệ trước khi truy cập
        if (index >= 0 && index < this.listLH.size()) {
            return this.listLH.get(index);
        } else {
            return null; // Hoặc ném ngoại lệ nếu cần
        }
    }

    public int getIndexByMaLH(int maloaihang) {
        int i = 0;
        int vitri = -1;
        while (i < this.listLH.size() && vitri == -1) {
            if (listLH.get(i).getMathuonghieu() == maloaihang) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public Boolean add(String name) {
        // Không cung cấp giá trị cho mathuonghieu, vì đó là khóa chính tự động tăng
        ThuongHieuDTO lh = new ThuongHieuDTO(0, name); // 0 hoặc giá trị mặc định
        boolean check = lhDAO.insert(lh) != 0;
        if (check) {
            this.listLH.add(lh);
        }
        return check;
    }

    public Boolean delete(ThuongHieuDTO lh) {
        boolean check = lhDAO.delete(Integer.toString(lh.getMathuonghieu())) != 0;
        if (check) {
            this.listLH.remove(lh);
        }
        return check;
    }

    public Boolean update(ThuongHieuDTO lh) {
        boolean check = lhDAO.update(lh) != 0;
        if (check) {
            int index = getIndexByMaLH(lh.getMathuonghieu());
            if (index != -1) {
                this.listLH.set(index, lh);
            }
        }
        return check;
    }

    public ArrayList<ThuongHieuDTO> search(String text) {
        text = text.toLowerCase();
        ArrayList<ThuongHieuDTO> result = new ArrayList<>();
        for (ThuongHieuDTO i : this.listLH) {
            if (Integer.toString(i.getMathuonghieu()).toLowerCase().contains(text) || 
                i.getTenthuonghieu().toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }

    public String[] getArrTenThuongHieu() {
        int size = listLH.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listLH.get(i).getTenthuonghieu();
        }
        return result;
    }

    public String getTenThuongHieu(int mathuonghieu) {
        int index = this.getIndexByMaLH(mathuonghieu);
        if (index != -1) {
            return this.listLH.get(index).getTenthuonghieu();
        }
        return null; // Hoặc ném ngoại lệ nếu cần
    }

    public boolean checkDup(String name) {
        name = name.toLowerCase();
        for (ThuongHieuDTO item : this.listLH) {
            if (item.getTenthuonghieu().toLowerCase().contains(name)) {
                return false; // Tồn tại tên trùng lặp
            }
        }
        return true; // Không tìm thấy tên trùng lặp
    }
}
