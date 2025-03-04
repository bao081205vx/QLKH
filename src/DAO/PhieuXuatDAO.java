package DAO;

import DTO.ChiTietPhieuDTO;
import DTO.ChiTietSanPhamDTO;
import DTO.PhieuXuatDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PhieuXuatDAO implements DAOinterface<PhieuXuatDTO> {

    public static PhieuXuatDAO getInstance(){
        return new PhieuXuatDAO();
    }

    @Override
    public int insert(PhieuXuatDTO t) {
        int result = 0;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "INSERT INTO phieuxuat (maphieuxuat, tongtien, nguoitaophieuxuat, makh, trangthai) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMaphieu());
            pst.setDouble(2, t.getTongTien()); // Chuyển từ int sang double
            pst.setInt(3, t.getManguoitao());
            pst.setInt(4, t.getMakh());
            pst.setInt(5, t.getTrangthai());
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }

    @Override
    public int update(PhieuXuatDTO t) {
        int result = 0;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "UPDATE phieuxuat SET thoigian = ?, tongtien = ?, trangthai = ? WHERE maphieuxuat = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, t.getThoigiantao());
            pst.setDouble(2, t.getTongTien()); // Chuyển từ long sang double
            pst.setInt(3, t.getTrangthai());
            pst.setInt(4, t.getMaphieu());
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "UPDATE phieuxuat SET trangthai = 0 WHERE maphieuxuat = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }

    @Override
    public ArrayList<PhieuXuatDTO> selectAll() {
        ArrayList<PhieuXuatDTO> result = new ArrayList<>();
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM phieuxuat ORDER BY maphieuxuat DESC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int maphieu = rs.getInt("maphieuxuat");
                Timestamp thoigiantao = rs.getTimestamp("thoigian");
                int makh = rs.getInt("makh");
                int nguoitao = rs.getInt("nguoitaophieuxuat");
                double tongtien = rs.getDouble("tongtien"); // Chuyển từ long sang double
                int trangthai = rs.getInt("trangthai");
                PhieuXuatDTO phieuxuat = new PhieuXuatDTO(makh, maphieu, nguoitao, thoigiantao, tongtien, trangthai);
                result.add(phieuxuat);
            }
        } catch (SQLException e) {
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }

    @Override
    public PhieuXuatDTO selectById(String t) {
        PhieuXuatDTO result = null;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM phieuxuat WHERE maphieuxuat = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                int maphieu = rs.getInt("maphieuxuat");
                Timestamp thoigiantao = rs.getTimestamp("thoigian");
                int makh = rs.getInt("makh");
                int nguoitao = rs.getInt("nguoitaophieuxuat");
                double tongtien = rs.getDouble("tongtien"); // Chuyển từ long sang double
                int trangthai = rs.getInt("trangthai");
                result = new PhieuXuatDTO(makh, maphieu, nguoitao, thoigiantao, tongtien, trangthai);
            }
        } catch (SQLException e) {
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }

    public PhieuXuatDTO cancel(int phieu) {
        PhieuXuatDTO result = null;
        try {
            ArrayList<ChiTietSanPhamDTO> chitietsanpham = ChiTietSanPhamDAO.getInstance().selectAllByMaPhieuXuat(phieu);
            ArrayList<ChiTietPhieuDTO> chitietphieu = ChiTietPhieuXuatDAO.getInstance().selectAll(phieu + "");
            ChiTietPhieuXuatDAO.getInstance().reset(chitietphieu);
            for (ChiTietSanPhamDTO chiTietSanPhamDTO : chitietsanpham) {
                ChiTietSanPhamDAO.getInstance().reset(chiTietSanPhamDTO);
            }
            deletePhieu(phieu);
        } catch (Exception e) {
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public int deletePhieu(int t) {
        int result = 0;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "DELETE FROM phieuxuat WHERE maphieuxuat = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t);
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }

    public ArrayList<PhieuXuatDTO> selectAllofKH(int makh) {
        ArrayList<PhieuXuatDTO> result = new ArrayList<>();
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM phieuxuat WHERE makh = ? ORDER BY maphieuxuat DESC";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, makh);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                int maphieu = rs.getInt("maphieuxuat");
                Timestamp thoigiantao = rs.getTimestamp("thoigian");
                int kh = rs.getInt("makh");
                int nguoitao = rs.getInt("nguoitaophieuxuat");
                double tongtien = rs.getDouble("tongtien"); // Chuyển từ long sang double
                int trangthai = rs.getInt("trangthai");
                PhieuXuatDTO phieuxuat = new PhieuXuatDTO(kh, maphieu, nguoitao, thoigiantao, tongtien, trangthai);
                result.add(phieuxuat);
            }
        } catch (SQLException e) {
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT IDENT_CURRENT('phieuxuat') AS CurrentIdentity";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getInt("CurrentIdentity");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }
}
