package DAO;

import DTO.ThuocTinhSanPham.DungLuongRomDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.JDBCUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DungLuongRomDAO implements DAOinterface<DungLuongRomDTO> {

    public static DungLuongRomDAO getInstance() {
        return new DungLuongRomDAO();
    }

    @Override
    public int insert(DungLuongRomDTO t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO dungluongrom (madlrom, kichthuocrom, trangthai) VALUES (?, ?, 1)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMadungluongrom());
            pst.setInt(2, t.getDungluongrom());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(DungLuongRomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(DungLuongRomDTO t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE dungluongrom SET kichthuocrom = ? WHERE madlrom = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getDungluongrom());
            pst.setInt(2, t.getMadungluongrom());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(DungLuongRomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE dungluongrom SET trangthai = 0 WHERE madlrom = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(DungLuongRomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<DungLuongRomDTO> selectAll() {
        ArrayList<DungLuongRomDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM dungluongrom WHERE trangthai = 1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int marom = rs.getInt("madlrom");
                int kichthuocrom = rs.getInt("kichthuocrom");
                DungLuongRomDTO dlr = new DungLuongRomDTO(marom, kichthuocrom);
                result.add(dlr);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(DungLuongRomDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    @Override
    public DungLuongRomDTO selectById(String t) {
        DungLuongRomDTO result = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM dungluongrom WHERE madlrom = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int madlrom = rs.getInt("madlrom");
                int kichthuocrom = rs.getInt("kichthuocrom");
                result = new DungLuongRomDTO(madlrom, kichthuocrom);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(DungLuongRomDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT IDENT_CURRENT('dungluongrom') AS CurrentIdentity";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getInt("CurrentIdentity");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(DungLuongRomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
