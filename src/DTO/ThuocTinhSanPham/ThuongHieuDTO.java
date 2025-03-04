package DTO.ThuocTinhSanPham;

public class ThuongHieuDTO {
    private int mathuonghieu;
    private String tenthuonghieu;

    public ThuongHieuDTO(int mathuonghieu, String tenthuonghieu) {
        this.mathuonghieu = mathuonghieu;
        this.tenthuonghieu = tenthuonghieu;
    }

    public int getMathuonghieu() {
        return mathuonghieu;
    }

    public void setMathuonghieu(int mathuonghieu) {
        this.mathuonghieu = mathuonghieu;
    }

    public String getTenthuonghieu() {
        return tenthuonghieu;
    }

    public void setTenthuonghieu(String tenthuonghieu) {
        this.tenthuonghieu = tenthuonghieu;
    }



    @Override
    public String toString() {
        return "ThuongHieuDTO{" + "mathuonghieu=" + mathuonghieu + ", tenthuonghieu='" + tenthuonghieu + '\'' + '}';
    }
}
