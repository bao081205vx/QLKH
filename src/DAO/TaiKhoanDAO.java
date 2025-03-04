package DAO;

import DTO.TaiKhoanDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaiKhoanDAO implements DAOinterface<TaiKhoanDTO> {

    public static TaiKhoanDAO getInstance() {
        return new TaiKhoanDAO();
    }

    @Override
    public int insert(TaiKhoanDTO t) {
        int result = 0;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "INSERT INTO taikhoan (manv, tendangnhap, matkhau, manhomquyen, trangthai) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getManv());
            pst.setString(2, t.getUsername());
            pst.setString(3, t.getMatkhau());
            pst.setInt(4, t.getManhomquyen());
            pst.setInt(5, t.getTrangthai());
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }

    @Override
    public int update(TaiKhoanDTO t) {
        int result = 0;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "UPDATE taikhoan SET tendangnhap=?, trangthai=?, manhomquyen=? WHERE manv=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getUsername());
            pst.setInt(2, t.getTrangthai());
            pst.setInt(3, t.getManhomquyen());
            pst.setInt(4, t.getManv());
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }

    public void updatePass(String email, String password) {
        int result;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "UPDATE taikhoan SET matkhau=? FROM taikhoan tk JOIN nhanvien nv ON tk.manv=nv.manv WHERE nv.email=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, password);
            pst.setString(2, email);
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(con);
        }
    }

    public TaiKhoanDTO selectByEmail(String t) {
        TaiKhoanDTO tk = null;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan tk JOIN nhanvien nv ON tk.manv=nv.manv WHERE nv.email=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int manv = rs.getInt("manv");
                String tendangnhap = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                int trangthai = rs.getInt("trangthai");
                int manhomquyen = rs.getInt("manhomquyen");
                tk = new TaiKhoanDTO(manv, tendangnhap, matkhau, manhomquyen, trangthai);
            }
        } catch (SQLException e) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return tk;
    }

    public void sendOpt(String email, String opt) {
        int result;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "UPDATE taikhoan SET otp=? FROM taikhoan tk JOIN nhanvien nv ON tk.manv=nv.manv WHERE nv.email=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, opt);
            pst.setString(2, email);
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(con);
        }
    }

    public boolean checkOtp(String email, String otp) {
        boolean check = false;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan tk JOIN nhanvien nv ON tk.manv=nv.manv WHERE nv.email=? AND tk.otp=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, otp);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (SQLException e) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return check;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "UPDATE taikhoan SET trangthai=-1 WHERE manv=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(t));
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }

    @Override
    public ArrayList<TaiKhoanDTO> selectAll() {
        ArrayList<TaiKhoanDTO> result = new ArrayList<>();
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan WHERE trangthai = 0 OR trangthai = 1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int manv = rs.getInt("manv");
                String username = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                int manhomquyen = rs.getInt("manhomquyen");
                int trangthai = rs.getInt("trangthai");
                TaiKhoanDTO tk = new TaiKhoanDTO(manv, username, matkhau, manhomquyen, trangthai);
                result.add(tk);
            }
        } catch (SQLException e) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }

    @Override
    public TaiKhoanDTO selectById(String t) {
        TaiKhoanDTO result = null;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan WHERE manv=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int manv = rs.getInt("manv");
                String tendangnhap = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                int trangthai = rs.getInt("trangthai");
                int manhomquyen = rs.getInt("manhomquyen");
                result = new TaiKhoanDTO(manv, tendangnhap, matkhau, manhomquyen, trangthai);
            }
        } catch (SQLException e) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }

    public TaiKhoanDTO selectByUser(String t) {
        TaiKhoanDTO result = null;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan WHERE tendangnhap=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int manv = rs.getInt("manv");
                String tendangnhap = rs.getString("tendangnhap");
                String matkhau = rs.getString("matkhau");
                int trangthai = rs.getInt("trangthai");
                int manhomquyen = rs.getInt("manhomquyen");
                result = new TaiKhoanDTO(manv, tendangnhap, matkhau, manhomquyen, trangthai);
            }
        } catch (SQLException e) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
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
            String sql = "SELECT IDENT_CURRENT('taikhoan') AS CurrentID";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getInt("CurrentID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return result;
    }
}