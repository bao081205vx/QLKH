
package DTO;

import java.sql.Timestamp;


public class PhieuNhapDTO extends PhieuDTO{
    private int manhacungcap;

    public PhieuNhapDTO(int manhacungcap) {
        this.manhacungcap = manhacungcap;
    }

    public PhieuNhapDTO(int manhacungcap, int maphieu, int manguoitao, Timestamp thoigiantao, long tongTien, int trangthai) {
        super(maphieu, manguoitao, thoigiantao, tongTien, trangthai);
        this.manhacungcap = manhacungcap;
    }

    public PhieuNhapDTO(int mancc, int maphieu, int nguoitao, Timestamp thoigiantao, double tongtien, int trangthai) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getManhacungcap() {
        return manhacungcap;
    }

    public void setManhacungcap(int manhacungcap) {
        this.manhacungcap = manhacungcap;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.manhacungcap;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PhieuNhapDTO other = (PhieuNhapDTO) obj;
        return this.manhacungcap == other.manhacungcap;
    }

    @Override
    public String toString() {
        return "PhieuNhapDTO{" + "manhacungcap=" + manhacungcap +'}'+super.toString();
    }
}
