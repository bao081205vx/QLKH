package DAO;

import DTO.ThuocTinhSanPham.HeDieuHanhDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.JDBCUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HeDieuHanhDAO implements DAOinterface<HeDieuHanhDTO> {

    private static final Logger LOGGER = Logger.getLogger(HeDieuHanhDAO.class.getName());

    public static HeDieuHanhDAO getInstance() {
        return new HeDieuHanhDAO();
    }

    @Override
    public int insert(HeDieuHanhDTO t) {
        int result = 0;
        String sql = "INSERT INTO hedieuhanh (tenhedieuhanh, trangthai) VALUES (?, ?)";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
             
            pst.setString(1, t.getTenhdh());
            pst.setInt(2, 1); // Trang thái mặc định là 1
            result = pst.executeUpdate();
            
            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    t.setMahdh(rs.getInt(1)); // Cập nhật ID tự động sinh vào đối tượng DTO
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error inserting HeDieuHanhDTO", ex);
        }
        return result;
    }

    @Override
    public int update(HeDieuHanhDTO t) {
        int result = 0;
        String sql = "UPDATE hedieuhanh SET tenhedieuhanh = ? WHERE mahedieuhanh = ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
             
            pst.setString(1, t.getTenhdh());
            pst.setInt(2, t.getMahdh());
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error updating HeDieuHanhDTO", ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        String sql = "UPDATE hedieuhanh SET trangthai = 0 WHERE mahedieuhanh = ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
             
            pst.setString(1, t);
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error deleting HeDieuHanhDTO", ex);
        }
        return result;
    }

    @Override
    public ArrayList<HeDieuHanhDTO> selectAll() {
        ArrayList<HeDieuHanhDTO> result = new ArrayList<>();
        String sql = "SELECT * FROM hedieuhanh WHERE trangthai = 1";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
             
            while (rs.next()) {
                int mahdh = rs.getInt("mahedieuhanh");
                String tenhdh = rs.getString("tenhedieuhanh");
                HeDieuHanhDTO hdh = new HeDieuHanhDTO(mahdh, tenhdh);
                result.add(hdh);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error selecting all HeDieuHanhDTO", e);
        }
        return result;
    }

    @Override
    public HeDieuHanhDTO selectById(String t) {
        HeDieuHanhDTO result = null;
        String sql = "SELECT * FROM hedieuhanh WHERE mahedieuhanh = ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
             
            pst.setString(1, t);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int mahdh = rs.getInt("mahedieuhanh");
                    String tenhdh = rs.getString("tenhedieuhanh");
                    result = new HeDieuHanhDTO(mahdh, tenhdh);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error selecting HeDieuHanhDTO by ID", e);
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        String sql = "SELECT IDENT_CURRENT('hedieuhanh') AS CurrentIdentity";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
             
            if (rs.next()) {
                result = rs.getInt("CurrentIdentity");
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error getting auto-increment value", ex);
        }
        return result;
    }
}
