package DAO;

import DTO.ChiTietQuyenDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ChiTietQuyenDAO implements ChiTietInterface<ChiTietQuyenDTO> {

    public static ChiTietQuyenDAO getInstance() {
        return new ChiTietQuyenDAO();
    }

    @Override
    public int delete(String t) {
        int result = 0;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "DELETE FROM ctquyen WHERE manhomquyen = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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

    @Override
    public int insert(ArrayList<ChiTietQuyenDTO> t) {
        int result = 0;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "INSERT INTO ctquyen (manhomquyen, machucnang, hanhdong) VALUES (?, ?, ?)";
            pst = con.prepareStatement(sql);
            for (ChiTietQuyenDTO item : t) {
                pst.setInt(1, item.getManhomquyen());
                pst.setString(2, item.getMachucnang());
                pst.setString(3, item.getHanhdong());
                result += pst.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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

    @Override
    public ArrayList<ChiTietQuyenDTO> selectAll(String t) {
        ArrayList<ChiTietQuyenDTO> result = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctquyen WHERE manhomquyen = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, t);
            rs = pst.executeQuery();
            while (rs.next()) {
                int manhomquyen = rs.getInt("manhomquyen");
                String machucnang = rs.getString("machucnang");
                String hanhdong = rs.getString("hanhdong");
                ChiTietQuyenDTO dvt = new ChiTietQuyenDTO(manhomquyen, machucnang, hanhdong);
                result.add(dvt);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            JDBCUtil.closeConnection(con);
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public int update(ArrayList<ChiTietQuyenDTO> t, String pk) {
        int result = this.delete(pk);
        if (result != 0) {
            result = this.insert(t);
        }
        return result;
    }
}

