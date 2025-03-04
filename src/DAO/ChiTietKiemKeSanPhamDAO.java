package DAO;

import DTO.ChiTietKiemKeSanPhamDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ChiTietKiemKeSanPhamDAO implements ChiTietInterface<ChiTietKiemKeSanPhamDTO> {
    
    public static ChiTietKiemKeSanPhamDAO getInstance() {
        return new ChiTietKiemKeSanPhamDAO();
    }

    @Override
    public int insert(ArrayList<ChiTietKiemKeSanPhamDTO> t) {
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
            Connection con = null;
            PreparedStatement pst = null;
            try {
                con = JDBCUtil.getConnection();
                String sql = "INSERT INTO ctkiemke_sanpham (maphieukiemke, maphienban, maimei, trangthai) VALUES (?, ?, ?, ?)";
                pst = con.prepareStatement(sql);
                pst.setInt(1, t.get(i).getMaphieukiemke());
                pst.setInt(2, t.get(i).getMaphienban());
                pst.setString(3, t.get(i).getMaimei());
                pst.setInt(4, t.get(i).getTrangthai());
                result += pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietKiemKeSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                // Đảm bảo đóng kết nối và PreparedStatement
                JDBCUtil.closeConnection(con);
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    @Override
    public int delete(String maphieu) {
        int result = 0;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "DELETE FROM ctkiemke_sanpham WHERE maphieukiemke = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, maphieu);
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietKiemKeSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đảm bảo đóng kết nối và PreparedStatement
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

//    @Override
//    public int update(ArrayList<ChiTietKiemKeSanPhamDTO> t, String pk) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public ArrayList<ChiTietKiemKeSanPhamDTO> selectAll(String t) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
}
