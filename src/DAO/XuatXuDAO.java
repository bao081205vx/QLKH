package DAO;

import DTO.ThuocTinhSanPham.XuatXuDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XuatXuDAO implements DAOinterface<XuatXuDTO> {
    private static final Logger LOGGER = Logger.getLogger(XuatXuDAO.class.getName());

    public static XuatXuDAO getInstance() {
        return new XuatXuDAO();
    }

    @Override
    public int insert(XuatXuDTO t) {
        int result = 0;
        String sql = "INSERT INTO xuatxu (tenxuatxu, trangthai) VALUES (?, 1)";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, t.getTenxuatxu());
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error inserting XuatXuDTO", ex);
        }
        return result;
    }

    @Override
    public int update(XuatXuDTO t) {
        int result = 0;
        String sql = "UPDATE xuatxu SET tenxuatxu = ? WHERE maxuatxu = ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, t.getTenxuatxu());
            pst.setInt(2, t.getMaxuatxu());
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error updating XuatXuDTO", ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        String sql = "UPDATE xuatxu SET trangthai = 0 WHERE maxuatxu = ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, t);
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error deleting XuatXuDTO", ex);
        }
        return result;
    }

    @Override
    public ArrayList<XuatXuDTO> selectAll() {
        ArrayList<XuatXuDTO> result = new ArrayList<>();
        String sql = "SELECT * FROM xuatxu WHERE trangthai = 1";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                int maxuatxu = rs.getInt("maxuatxu");
                String tenxuatxu = rs.getString("tenxuatxu");
                result.add(new XuatXuDTO(maxuatxu, tenxuatxu));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error selecting all XuatXuDTO", ex);
        }
        return result;
    }

    @Override
    public XuatXuDTO selectById(String t) {
        XuatXuDTO result = null;
        String sql = "SELECT * FROM xuatxu WHERE maxuatxu = ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, t);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int maxuatxu = rs.getInt("maxuatxu");
                    String tenxuatxu = rs.getString("tenxuatxu");
                    result = new XuatXuDTO(maxuatxu, tenxuatxu);
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error selecting XuatXuDTO by ID", ex);
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        String sql = "SELECT IDENT_CURRENT('xuatxu') AS CurrentIdentity";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                result = rs.getInt("CurrentIdentity");
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error getting auto increment value", ex);
        }
        return result;
    }
}
