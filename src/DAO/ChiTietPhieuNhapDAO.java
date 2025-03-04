package DAO;

import DTO.ChiTietPhieuNhapDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ChiTietPhieuNhapDAO implements ChiTietInterface<ChiTietPhieuNhapDTO> {

    public static ChiTietPhieuNhapDAO getInstance() {
        return new ChiTietPhieuNhapDAO();
    }

    @Override
    public int insert(ArrayList<ChiTietPhieuNhapDTO> t) {
        int result = 0;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "INSERT INTO ctphieunhap (maphieunhap, maphienbansp, soluong, dongia, hinhthucnhap) VALUES (?, ?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            for (ChiTietPhieuNhapDTO item : t) {
                pst.setInt(1, item.getMaphieu());
                pst.setInt(2, item.getMaphienbansp());
                pst.setInt(3, item.getSoluong());
                pst.setInt(4, item.getDongia());
                pst.setInt(5, item.getPhuongthucnnhap());
                result += pst.executeUpdate();
                // Cập nhật số lượng tồn kho sau khi thêm
                PhienBanSanPhamDAO.getInstance().updateSoLuongTon(item.getMaphienbansp(), item.getSoluong());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(con);
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "DELETE FROM ctphieunhap WHERE maphieunhap = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(con);
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public int update(ArrayList<ChiTietPhieuNhapDTO> t, String pk) {
        int result = this.delete(pk);
        if (result != 0) {
            result = this.insert(t);
        }
        return result;
    }

    @Override
    public ArrayList<ChiTietPhieuNhapDTO> selectAll(String t) {
        ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctphieunhap WHERE maphieunhap = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, t);
            rs = pst.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("maphieunhap");
                int maphienbansp = rs.getInt("maphienbansp");
                int dongia = rs.getInt("dongia");
                int soluong = rs.getInt("soluong");
                int phuongthucnhap = rs.getInt("hinhthucnhap");
                ChiTietPhieuNhapDTO ctphieu = new ChiTietPhieuNhapDTO(phuongthucnhap, maphieu, maphienbansp, soluong, dongia);
                result.add(ctphieu);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            JDBCUtil.closeConnection(con);
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
