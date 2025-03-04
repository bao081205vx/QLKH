package DAO;

import DTO.PhieuKiemKeDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PhieuKiemKeDAO implements DAOinterface<PhieuKiemKeDTO>{
    
    public static PhieuKiemKeDAO getInstance(){
        return new PhieuKiemKeDAO();
    }

    @Override
    public int insert(PhieuKiemKeDTO t) {
        int result = 0;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "INSERT INTO phieukiemke (maphieu, nguoitaophieukiemke) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMaphieukiemke());
            pst.setInt(2, t.getNguoitao());
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuKiemKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }

    @Override
    public int update(PhieuKiemKeDTO t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(String maphieu) {
        int result = 0;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "DELETE FROM phieukiemke WHERE maphieu = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, maphieu);
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuKiemKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }

    @Override
    public ArrayList<PhieuKiemKeDTO> selectAll() {
        ArrayList<PhieuKiemKeDTO> result = new ArrayList<>();
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM phieukiemke ORDER BY maphieu DESC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("maphieu");
                Timestamp thoigiantao = rs.getTimestamp("thoigian");
                int manguoitao = rs.getInt("nguoitaophieukiemke");
                PhieuKiemKeDTO phieukiemke = new PhieuKiemKeDTO(maphieu, manguoitao, thoigiantao);
                result.add(phieukiemke);
            }
        } catch (SQLException e) {
            Logger.getLogger(PhieuKiemKeDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }

    @Override
    public PhieuKiemKeDTO selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT IDENT_CURRENT('phieukiemke') AS CurrentIdentity";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getInt("CurrentIdentity");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuKiemKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }
}
