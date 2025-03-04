package DAO;

import DTO.ChiTietSanPhamDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChiTietSanPhamDAO implements DAOinterface<ChiTietSanPhamDTO> {

    // Singleton pattern
    private static final ChiTietSanPhamDAO instance = new ChiTietSanPhamDAO();
    
    private ChiTietSanPhamDAO() {
        // Private constructor to enforce singleton pattern
    }
    
    public static ChiTietSanPhamDAO getInstance() {
        return instance;
    }

    @Override
    public int insert(ChiTietSanPhamDTO t) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement("INSERT INTO ctsanpham (maimei, maphienbansp, maphieunhap, tinhtrang) VALUES (?, ?, ?, ?)")) {
            pst.setString(1, t.getImei());
            pst.setInt(2, t.getMaphienbansp());
            pst.setInt(3, t.getMaphieunhap());
            pst.setInt(4, t.getTinhtrang());
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(ChiTietSanPhamDTO t) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement("UPDATE ctsanpham SET maphienbansp = ?, maphieunhap = ?, maphieuxuat = ?, tinhtrang = ? WHERE maimei = ?")) {
            pst.setInt(1, t.getMaphienbansp());
            pst.setInt(2, t.getMaphieunhap());
            pst.setInt(3, t.getMaphieuxuat());
            pst.setInt(4, t.getTinhtrang());
            pst.setString(5, t.getImei());
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String imei) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement("UPDATE ctsanpham SET tinhtrang = 0 WHERE maimei = ?")) {
            pst.setString(1, imei);
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<ChiTietSanPhamDTO> selectAll() {
        ArrayList<ChiTietSanPhamDTO> result = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT * FROM ctsanpham");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                String imei = rs.getString("maimei");
                int maphienban = rs.getInt("maphienbansp");
                int maphieunhap = rs.getInt("maphieunhap");
                int maphieuxuat = rs.getInt("maphieuxuat");
                int tinhtrang = rs.getInt("tinhtrang");
                ChiTietSanPhamDTO ct = new ChiTietSanPhamDTO(imei, maphienban, maphieunhap, maphieuxuat, tinhtrang);
                result.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ChiTietSanPhamDTO selectById(String imei) {
        ChiTietSanPhamDTO result = null;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT * FROM ctsanpham WHERE maimei = ?")) {
            pst.setString(1, imei);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String imeiResult = rs.getString("maimei");
                    int maphienban = rs.getInt("maphienbansp");
                    int maphieunhap = rs.getInt("maphieunhap");
                    int maphieuxuat = rs.getInt("maphieuxuat");
                    int tinhtrang = rs.getInt("tinhtrang");
                    result = new ChiTietSanPhamDTO(imeiResult, maphienban, maphieunhap, maphieuxuat, tinhtrang);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    // Custom method, not part of DAOinterface
    public ArrayList<ChiTietSanPhamDTO> selectByPb(int maphienban) {
        ArrayList<ChiTietSanPhamDTO> result = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT * FROM ctsanpham WHERE maphienbansp = ?")) {
            pst.setInt(1, maphienban);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String imei = rs.getString("maimei");
                    int maphieunhap = rs.getInt("maphieunhap");
                    int maphieuxuat = rs.getInt("maphieuxuat");
                    int tinhtrang = rs.getInt("tinhtrang");
                    ChiTietSanPhamDTO ct = new ChiTietSanPhamDTO(imei, maphienban, maphieunhap, maphieuxuat, tinhtrang);
                    result.add(ct);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT IDENT_CURRENT('ctsanpham') AS LastID");
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                result = rs.getInt("LastID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ChiTietSanPhamDTO> selectAllByMaPhieuXuat(int maphieu) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void updateXuat(ChiTietSanPhamDTO chiTietSanPhamDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<ChiTietSanPhamDTO> selectAllByMaPhieuNhap(int maphieunhap) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
