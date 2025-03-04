package DAO;

import DTO.ThuocTinhSanPham.ThuongHieuDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThuongHieuDAO implements DAOinterface<ThuongHieuDTO> {
    private static final Logger LOGGER = Logger.getLogger(ThuongHieuDAO.class.getName());
    private static ThuongHieuDAO instance = new ThuongHieuDAO();

    private ThuongHieuDAO() {
        // Constructor private để ngăn việc tạo nhiều instance
    }

    public static ThuongHieuDAO getInstance() {
        return instance;
    }

    @Override
    public int insert(ThuongHieuDTO t) {
        int result = 0;
        String sql = "INSERT INTO thuonghieu (tenthuonghieu) VALUES (?)";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, t.getTenthuonghieu());
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error inserting ThuongHieuDTO", ex);
        }
        return result;
    }

    @Override
    public int update(ThuongHieuDTO t) {
        int result = 0;
        String sql = "UPDATE thuonghieu SET tenthuonghieu = ? WHERE mathuonghieu = ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, t.getTenthuonghieu());
            pst.setInt(2, t.getMathuonghieu());
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error updating ThuongHieuDTO", ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        String sql = "UPDATE thuonghieu SET trangthai = 0 WHERE mathuonghieu = ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, t);
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error deleting ThuongHieuDTO", ex);
        }
        return result;
    }

    @Override
    public ArrayList<ThuongHieuDTO> selectAll() {
        ArrayList<ThuongHieuDTO> result = new ArrayList<>();
        String sql = "SELECT * FROM thuonghieu WHERE trangthai = 1";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                int mathuonghieu = rs.getInt("mathuonghieu");
                String tenthuonghieu = rs.getString("tenthuonghieu");
                result.add(new ThuongHieuDTO(mathuonghieu, tenthuonghieu));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error selecting all ThuongHieuDTO", ex);
        }
        return result;
    }

    @Override
    public ThuongHieuDTO selectById(String t) {
        ThuongHieuDTO result = null;
        String sql = "SELECT * FROM thuonghieu WHERE mathuonghieu = ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, t);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int mathuonghieu = rs.getInt("mathuonghieu");
                    String tenthuonghieu = rs.getString("tenthuonghieu");
                    result = new ThuongHieuDTO(mathuonghieu, tenthuonghieu);
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error selecting ThuongHieuDTO by ID", ex);
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        String sql = "SELECT SCOPE_IDENTITY() AS CurrentIdentity";
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
