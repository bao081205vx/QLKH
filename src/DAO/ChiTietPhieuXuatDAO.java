package DAO;

import DTO.ChiTietPhieuDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ChiTietPhieuXuatDAO implements ChiTietInterface<ChiTietPhieuDTO> {

    public static ChiTietPhieuXuatDAO getInstance() {
        return new ChiTietPhieuXuatDAO();
    }

    @Override
    public int insert(ArrayList<ChiTietPhieuDTO> t) {
        int result = 0;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "INSERT INTO ctphieuxuat (maphieuxuat, maphienbansp, soluong, dongia) VALUES (?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            for (ChiTietPhieuDTO item : t) {
                pst.setInt(1, item.getMaphieu());
                pst.setInt(2, item.getMaphienbansp());
                pst.setInt(3, item.getSoluong());
                int soluong = -item.getSoluong(); // Tính số lượng cần cập nhật
                int change = PhienBanSanPhamDAO.getInstance().updateSoLuongTon(item.getMaphienbansp(), soluong);
                pst.setInt(4, item.getDongia());
                result += pst.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public int reset(ArrayList<ChiTietPhieuDTO> t) {
        int result = 0;
        for (ChiTietPhieuDTO item : t) {
            // Cập nhật số lượng tồn kho
            PhienBanSanPhamDAO.getInstance().updateSoLuongTon(item.getMaphienbansp(), item.getSoluong());
            // Xóa phiếu xuất
            delete(String.valueOf(item.getMaphieu()));
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
            String sql = "DELETE FROM ctphieuxuat WHERE maphieuxuat = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public int update(ArrayList<ChiTietPhieuDTO> t, String pk) {
        int result = this.delete(pk);
        if (result != 0) {
            result = this.insert(t);
        }
        return result;
    }

    @Override
    public ArrayList<ChiTietPhieuDTO> selectAll(String t) {
        ArrayList<ChiTietPhieuDTO> result = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctphieuxuat WHERE maphieuxuat = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, t);
            rs = pst.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("maphieuxuat");
                int maphienbansp = rs.getInt("maphienbansp");
                int dongia = rs.getInt("dongia");
                int soluong = rs.getInt("soluong");
                ChiTietPhieuDTO ctphieu = new ChiTietPhieuDTO(maphieu, maphienbansp, soluong, dongia);
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
