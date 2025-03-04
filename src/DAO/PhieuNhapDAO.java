package DAO;

import DTO.ChiTietPhieuNhapDTO;
import DTO.ChiTietSanPhamDTO;
import DTO.PhieuNhapDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhieuNhapDAO implements DAOinterface<PhieuNhapDTO> {

    public static PhieuNhapDAO getInstance() {
        return new PhieuNhapDAO();
    }
    
    @Override
    public int insert(PhieuNhapDTO t) {
        int result = 0;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "INSERT INTO phieunhap (maphieunhap, thoigian, manhacungcap, nguoitao, tongtien) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMaphieu());
            pst.setTimestamp(2, t.getThoigiantao());
            pst.setInt(3, t.getManhacungcap());
            pst.setInt(4, t.getManguoitao());
            pst.setDouble(5, t.getTongTien());
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }

    @Override
    public int update(PhieuNhapDTO t) {
        int result = 0;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "UPDATE phieunhap SET thoigian = ?, manhacungcap = ?, tongtien = ?, trangthai = ? WHERE maphieunhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, t.getThoigiantao());
            pst.setInt(2, t.getManhacungcap());
            pst.setDouble(3, t.getTongTien()); // Chuyển từ long sang double
            pst.setInt(4, t.getTrangthai());
            pst.setInt(5, t.getMaphieu());
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            String sql = "UPDATE phieunhap SET trangthai = 0 WHERE maphieunhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }

    @Override
    public ArrayList<PhieuNhapDTO> selectAll() {
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM phieunhap ORDER BY maphieunhap DESC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("maphieunhap");
                Timestamp thoigiantao = rs.getTimestamp("thoigian");
                int mancc = rs.getInt("manhacungcap");
                int nguoitao = rs.getInt("nguoitao");
                double tongtien = rs.getDouble("tongtien"); // Chuyển từ long sang double
                int trangthai = rs.getInt("trangthai");
                PhieuNhapDTO phieunhap = new PhieuNhapDTO(mancc, maphieu, nguoitao, thoigiantao, tongtien, trangthai);
                result.add(phieunhap);
            }
        } catch (SQLException e) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }

    @Override
    public PhieuNhapDTO selectById(String t) {
        PhieuNhapDTO result = null;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM phieunhap WHERE maphieunhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int maphieu = rs.getInt("maphieunhap");
                Timestamp thoigiantao = rs.getTimestamp("thoigian");
                int mancc = rs.getInt("manhacungcap");
                int nguoitao = rs.getInt("nguoitao");
                double tongtien = rs.getDouble("tongtien"); // Chuyển từ long sang double
                int trangthai = rs.getInt("trangthai");
                result = new PhieuNhapDTO(mancc, maphieu, nguoitao, thoigiantao, tongtien, trangthai);
            }
        } catch (SQLException e) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }

    public ArrayList<PhieuNhapDTO> statistical(double min, double max) {
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM phieunhap WHERE tongtien BETWEEN ? AND ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDouble(1, min);
            pst.setDouble(2, max);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("maphieunhap");
                Timestamp thoigiantao = rs.getTimestamp("thoigian");
                int mancc = rs.getInt("manhacungcap");
                int nguoitao = rs.getInt("nguoitao");
                double tongtien = rs.getDouble("tongtien"); // Chuyển từ long sang double
                int trangthai = rs.getInt("trangthai");
                PhieuNhapDTO phieunhap = new PhieuNhapDTO(mancc, maphieu, nguoitao, thoigiantao, tongtien, trangthai);
                result.add(phieunhap);
            }
        } catch (SQLException e) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }

    public boolean checkCancelPn(int maphieu) {
        ArrayList<ChiTietSanPhamDTO> result = new ArrayList<>();
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctsanpham WHERE maphieunhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maphieu);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String imei = rs.getString("maimei");
                int macauhinh = rs.getInt("maphienbansp");
                int maphieunhap = rs.getInt("maphieunhap");
                int maphieuxuat = rs.getInt("maphieuxuat");
                int tinhtrang = rs.getInt("tinhtrang");
                ChiTietSanPhamDTO ct = new ChiTietSanPhamDTO(imei, macauhinh, maphieunhap, maphieuxuat, tinhtrang);
                result.add(ct);
            }
        } catch (SQLException e) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        for (ChiTietSanPhamDTO chiTietSanPhamDTO : result) {
            if (chiTietSanPhamDTO.getMaphieuxuat() != 0) {
                return false;
            }
        }
        return true;
    }

    public int cancelPhieuNhap(int maphieu) {
        int result = 0;
        try {
            ChiTietSanPhamDAO.getInstance().deletePn(maphieu);
            ArrayList<ChiTietPhieuNhapDTO> arrCt = ChiTietPhieuNhapDAO.getInstance().selectAll(Integer.toString(maphieu));
            for (ChiTietPhieuNhapDTO chiTietPhieuNhapDTO : arrCt) {
                PhienBanSanPhamDAO.getInstance().updateSoLuongTon(chiTietPhieuNhapDTO.getMaphienbansp(), -(chiTietPhieuNhapDTO.getSoluong()));
            }
            ChiTietPhieuNhapDAO.getInstance().delete(Integer.toString(maphieu));
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM phieunhap WHERE maphieunhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maphieu);
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connection con = null;
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
            String sql = "SELECT IDENT_CURRENT('phieunhap') AS CurrentIdentity";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getInt("CurrentIdentity");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }
}
