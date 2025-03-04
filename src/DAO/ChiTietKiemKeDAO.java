package DAO;

import DTO.ChiTietKiemKeDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ChiTietKiemKeDAO implements ChiTietInterface<ChiTietKiemKeDTO> {
    
    public static ChiTietKiemKeDAO getInstance(){
        return new ChiTietKiemKeDAO();
    }

    @Override
    public int insert(ArrayList<ChiTietKiemKeDTO> t) {
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = JDBCUtil.getConnection();
                String sql = "INSERT INTO ctkiemke (maphieukiemke, maphienban, soluong, chenhlech, ghichu) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, t.get(i).getMaphieukiemke());
                pst.setInt(2, t.get(i).getMaphienban());
                pst.setInt(3, t.get(i).getSoluong());
                pst.setInt(4, t.get(i).getChenhlech());
                pst.setString(5, t.get(i).getGhichu());
                result = pst.executeUpdate();
                JDBCUtil.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietKiemKeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public int delete(String maphieu) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM ctkiemke WHERE maphieukiemke = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, maphieu);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietKiemKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    

    @Override
    public ArrayList<ChiTietKiemKeDTO> selectAll(String t) {
        ArrayList<ChiTietKiemKeDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctkiemke WHERE maphieukiemke = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maphieukiemke = rs.getInt("maphieukiemke");
                int maphienban = rs.getInt("maphienban");
                int soluong = rs.getInt("soluong");
                int chenhlech = rs.getInt("chenhlech");
                String ghichu = rs.getString("ghichu");
                ChiTietKiemKeDTO ctphieu = new ChiTietKiemKeDTO(maphieukiemke, maphienban, soluong, chenhlech, ghichu);
                result.add(ctphieu);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
}
