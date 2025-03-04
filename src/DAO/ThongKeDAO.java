
package DAO;

import DTO.ThongKe.ThongKeDoanhThuDTO;
import DTO.ThongKe.ThongKeKhachHangDTO;
import DTO.ThongKe.ThongKeNhaCungCapDTO;
import DTO.ThongKe.ThongKeTheoThangDTO;
import DTO.ThongKe.ThongKeTonKhoDTO;
import DTO.ThongKe.ThongKeTungNgayTrongThangDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ThongKeDAO {

    public static ThongKeDAO getInstance() {
        return new ThongKeDAO();
    }

    public static HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> getThongKeTonKho(String text, Date timeStart, Date timeEnd) {
        HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> result = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeEnd.getTime());
        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                         WITH nhap AS (
                           SELECT maphienbansp, SUM(soluong) AS sl_nhap
                           FROM ctphieunhap
                           JOIN phieunhap ON phieunhap.maphieunhap = ctphieunhap.maphieunhap
                           WHERE thoigian BETWEEN ? AND ?
                           GROUP BY maphienbansp
                         ),
                         xuat AS (
                           SELECT maphienbansp, SUM(soluong) AS sl_xuat
                           FROM ctphieuxuat
                           JOIN phieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat
                           WHERE thoigian BETWEEN ? AND ?
                           GROUP BY maphienbansp
                         ),
                         nhap_dau AS (
                           SELECT ctphieunhap.maphienbansp, SUM(ctphieunhap.soluong) AS sl_nhap_dau
                           FROM phieunhap
                           JOIN ctphieunhap ON phieunhap.maphieunhap = ctphieunhap.maphieunhap
                           WHERE phieunhap.thoigian < ?
                           GROUP BY ctphieunhap.maphienbansp
                         ),
                         xuat_dau AS (
                           SELECT ctphieuxuat.maphienbansp, SUM(ctphieuxuat.soluong) AS sl_xuat_dau
                           FROM phieuxuat
                           JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat
                           WHERE phieuxuat.thoigian < ?
                           GROUP BY ctphieuxuat.maphienbansp
                         ),
                         dau_ky AS (
                           SELECT
                             phienbansanpham.maphienbansp,
                             COALESCE(nhap_dau.sl_nhap_dau, 0) - COALESCE(xuat_dau.sl_xuat_dau, 0) AS soluongdauky
                           FROM phienbansanpham
                           LEFT JOIN nhap_dau ON phienbansanpham.maphienbansp = nhap_dau.maphienbansp
                           LEFT JOIN xuat_dau ON phienbansanpham.maphienbansp = xuat_dau.maphienbansp
                         ),
                         temp_table AS (
                         SELECT sanpham.masp, phienbansanpham.maphienbansp, sanpham.tensp, dau_ky.soluongdauky, COALESCE(nhap.sl_nhap, 0) AS soluongnhap, COALESCE(xuat.sl_xuat, 0)  AS soluongxuat, (dau_ky.soluongdauky + COALESCE(nhap.sl_nhap, 0) - COALESCE(xuat.sl_xuat, 0)) AS soluongcuoiky, kichthuocram, kichthuocrom, tenmau
                         FROM dau_ky
                         LEFT JOIN nhap ON dau_ky.maphienbansp = nhap.maphienbansp
                         LEFT JOIN xuat ON dau_ky.maphienbansp = xuat.maphienbansp
                         JOIN phienbansanpham ON phienbansanpham.maphienbansp = dau_ky.maphienbansp
                         JOIN sanpham ON phienbansanpham.masp = sanpham.masp
                         JOIN dungluongram ON phienbansanpham.ram = dungluongram.madlram
                         JOIN dungluongrom ON phienbansanpham.rom = dungluongrom.madlrom
                         JOIN mausac ON phienbansanpham.mausac = mausac.mamau
                         )
                         SELECT * FROM temp_table
                         WHERE tensp LIKE ? OR masp LIKE ?
                         ORDER BY masp;""";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(2, new Timestamp(calendar.getTimeInMillis()));
            pst.setTimestamp(3, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(4, new Timestamp(calendar.getTimeInMillis()));
            pst.setTimestamp(5, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(6, new Timestamp(timeStart.getTime()));
            pst.setString(7, "%" + text + "%");
            pst.setString(8, "%" + text + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int masp = rs.getInt("masp");
                int maphienbansp = rs.getInt("maphienbansp");
                String tensp = rs.getString("tensp");
                int soluongdauky = rs.getInt("soluongdauky");
                int soluongnhap = rs.getInt("soluongnhap");
                int soluongxuat = rs.getInt("soluongxuat");
                int soluongcuoiky = rs.getInt("soluongcuoiky");
                int ram = rs.getInt("kichthuocram");
                int rom = rs.getInt("kichthuocrom");
                String mausac = rs.getString("tenmau");
                ThongKeTonKhoDTO p = new ThongKeTonKhoDTO(masp, maphienbansp, tensp, ram, rom, mausac, soluongdauky, soluongnhap, soluongxuat, soluongcuoiky);
                result.computeIfAbsent(masp, k -> new ArrayList<>()).add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<ThongKeKhachHangDTO> getThongKeKhachHang(String string, Date date, Date date0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static ArrayList<ThongKeNhaCungCapDTO> getThongKeNCC(String string, Date date, Date date0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<ThongKeDoanhThuDTO> getDoanhThuTheoTungNam(int year_start, int year_end) {
        ArrayList<ThongKeDoanhThuDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sqlSetStartYear = "SET @start_year = ?;";
            String sqlSetEndYear = "SET @end_year = ?;";
            String sqlSelect = """
                     WITH RECURSIVE years(year) AS (
                       SELECT @start_year
                       UNION ALL
                       SELECT year + 1
                       FROM years
                       WHERE year < @end_year
                     )
                     SELECT 
                       years.year AS nam,
                       COALESCE(SUM(ctphieunhap.dongia), 0) AS chiphi, 
                       COALESCE(SUM(ctphieuxuat.dongia), 0) AS doanhthu
                     FROM years
                     LEFT JOIN phieuxuat ON YEAR(phieuxuat.thoigian) = years.year
                     LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat
                     LEFT JOIN ctsanpham ON ctsanpham.maphieuxuat = ctphieuxuat.maphieuxuat AND ctsanpham.maphienbansp = ctphieuxuat.maphienbansp
                     LEFT JOIN ctphieunhap ON ctsanpham.maphieunhap = ctphieunhap.maphieunhap AND ctsanpham.maphienbansp = ctphieunhap.maphienbansp
                     GROUP BY years.year
                     ORDER BY years.year;""";
            PreparedStatement pstStartYear = con.prepareStatement(sqlSetStartYear);
            PreparedStatement pstEndYear = con.prepareStatement(sqlSetEndYear);
            PreparedStatement pstSelect = con.prepareStatement(sqlSelect);

            pstStartYear.setInt(1, year_start);
            pstEndYear.setInt(1, year_end);

            pstStartYear.execute();
            pstEndYear.execute();

            ResultSet rs = pstSelect.executeQuery();
            while (rs.next()) {
                int thoigian = rs.getInt("thang");
                Long von = rs.getLong("von");
                Long doanhthu = rs.getLong("doanhthu");
                Long loinhuan = rs.getLong("loinhuan"); // hoặc giá trị mặc định
                ThongKeDoanhThuDTO thongKeDoanhThu = new ThongKeDoanhThuDTO(thoigian, von, doanhthu, loinhuan);
                result.add(thongKeDoanhThu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ThongKeDoanhThuDTO> getDoanhThuTheoTungThang(int year, int month_start, int month_end) {
        ArrayList<ThongKeDoanhThuDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sqlSetYear = "SET @year = ?;";
            String sqlSetStartMonth = "SET @start_month = ?;";
            String sqlSetEndMonth = "SET @end_month = ?;";
            String sqlSelect = """
                     WITH RECURSIVE months(month) AS (
                       SELECT @start_month
                       UNION ALL
                       SELECT month + 1
                       FROM months
                       WHERE month < @end_month
                     )
                     SELECT 
                       months.month AS thang,
                       COALESCE(SUM(ctphieunhap.dongia), 0) AS chiphi, 
                       COALESCE(SUM(ctphieuxuat.dongia), 0) AS doanhthu
                     FROM months
                     LEFT JOIN phieuxuat ON YEAR(phieuxuat.thoigian) = @year AND MONTH(phieuxuat.thoigian) = months.month
                     LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat
                     LEFT JOIN ctsanpham ON ctsanpham.maphieuxuat = ctphieuxuat.maphieuxuat AND ctsanpham.maphienbansp = ctphieuxuat.maphienbansp
                     LEFT JOIN ctphieunhap ON ctsanpham.maphieunhap = ctphieunhap.maphieunhap AND ctsanpham.maphienbansp = ctphieunhap.maphienbansp
                     GROUP BY months.month
                     ORDER BY months.month;""";
            PreparedStatement pstSetYear = con.prepareStatement(sqlSetYear);
            PreparedStatement pstSetStartMonth = con.prepareStatement(sqlSetStartMonth);
            PreparedStatement pstSetEndMonth = con.prepareStatement(sqlSetEndMonth);
            PreparedStatement pstSelect = con.prepareStatement(sqlSelect);

            pstSetYear.setInt(1, year);
            pstSetStartMonth.setInt(2, month_start);
            pstSetEndMonth.setInt(3, month_end);

            pstSetYear.execute();
            pstSetStartMonth.execute();
            pstSetEndMonth.execute();

            ResultSet rs = pstSelect.executeQuery();
            while (rs.next()) {
                int thoigian = rs.getInt("thang");
                Long doanhthu = rs.getLong("doanhthu");
                Long chiphi = rs.getLong("chiphi");
                ThongKeDoanhThuDTO thongKeDoanhThu = new ThongKeDoanhThuDTO(thoigian, doanhthu, chiphi);
                result.add(thongKeDoanhThu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ThongKeDoanhThuDTO> getDoanhThuTheoTungNgayTrongThang(int year, int month, int day_start, int day_end) {
        ArrayList<ThongKeDoanhThuDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sqlSetYear = "SET @year = ?;";
            String sqlSetMonth = "SET @month = ?;";
            String sqlSetStartDay = "SET @start_day = ?;";
            String sqlSetEndDay = "SET @end_day = ?;";
            String sqlSelect = """
                     WITH RECURSIVE days(day) AS (
                       SELECT @start_day
                       UNION ALL
                       SELECT day + 1
                       FROM days
                       WHERE day < @end_day
                     )
                     SELECT 
                       days.day AS ngay,
                       COALESCE(SUM(ctphieunhap.dongia), 0) AS chiphi, 
                       COALESCE(SUM(ctphieuxuat.dongia), 0) AS doanhthu
                     FROM days
                     LEFT JOIN phieuxuat ON YEAR(phieuxuat.thoigian) = @year AND MONTH(phieuxuat.thoigian) = @month AND DAY(phieuxuat.thoigian) = days.day
                     LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat
                     LEFT JOIN ctsanpham ON ctsanpham.maphieuxuat = ctphieuxuat.maphieuxuat AND ctsanpham.maphienbansp = ctphieuxuat.maphienbansp
                     LEFT JOIN ctphieunhap ON ctsanpham.maphieunhap = ctphieunhap.maphieunhap AND ctsanpham.maphienbansp = ctphieunhap.maphienbansp
                     GROUP BY days.day
                     ORDER BY days.day;""";
            PreparedStatement pstSetYear = con.prepareStatement(sqlSetYear);
            PreparedStatement pstSetMonth = con.prepareStatement(sqlSetMonth);
            PreparedStatement pstSetStartDay = con.prepareStatement(sqlSetStartDay);
            PreparedStatement pstSetEndDay = con.prepareStatement(sqlSetEndDay);
            PreparedStatement pstSelect = con.prepareStatement(sqlSelect);

            pstSetYear.setInt(1, year);
            pstSetMonth.setInt(2, month);
            pstSetStartDay.setInt(3, day_start);
            pstSetEndDay.setInt(4, day_end);

            pstSetYear.execute();
            pstSetMonth.execute();
            pstSetStartDay.execute();
            pstSetEndDay.execute();

            ResultSet rs = pstSelect.executeQuery();
            while (rs.next()) {
                int thoigian = rs.getInt("ngay");
                Long doanhthu = rs.getLong("doanhthu");
                Long chiphi = rs.getLong("chiphi");
                ThongKeDoanhThuDTO thongKeDoanhThu = new ThongKeDoanhThuDTO(thoigian, doanhthu, chiphi);
                result.add(thongKeDoanhThu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public HashMap<Integer, ArrayList<ThongKeKhachHangDTO>> getDoanhSoKhachHang(Date startDate, Date endDate) {
        HashMap<Integer, ArrayList<ThongKeKhachHangDTO>> result = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(endDate.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                         SELECT khachhang.makh, tenkh, SUM(ctphieuxuat.soluong) AS tongsoluong, SUM(ctphieuxuat.dongia) AS tonggiatri
                         FROM khachhang
                         LEFT JOIN phieuxuat ON khachhang.makh = phieuxuat.makh
                         LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat
                         WHERE phieuxuat.thoigian BETWEEN ? AND ?
                         GROUP BY khachhang.makh, tenkh
                         ORDER BY tonggiatri DESC;""";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(startDate.getTime()));
            pst.setTimestamp(2, new Timestamp(calendar.getTimeInMillis()));

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int makh = rs.getInt("makh");
                String tenkh = rs.getString("tenkh");
                int tongsoluong = rs.getInt("tongsoluong");
                long tonggiatri = rs.getLong("tonggiatri");

                ThongKeKhachHangDTO thongKeKhachHang = new ThongKeKhachHangDTO(makh, tenkh, tongsoluong, tonggiatri);
                result.computeIfAbsent(makh, k -> new ArrayList<>()).add(thongKeKhachHang);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public HashMap<Integer, ArrayList<ThongKeNhaCungCapDTO>> getTongNhapNhaCungCap(Date startDate, Date endDate) {
        HashMap<Integer, ArrayList<ThongKeNhaCungCapDTO>> result = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(endDate.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                         SELECT nhacungcap.mancc, tenncc, SUM(ctphieunhap.soluong) AS tongsoluong, SUM(ctphieunhap.dongia) AS tonggiatri
                         FROM nhacungcap
                         LEFT JOIN phieunhap ON nhacungcap.mancc = phieunhap.mancc
                         LEFT JOIN ctphieunhap ON phieunhap.maphieunhap = ctphieunhap.maphieunhap
                         WHERE phieunhap.thoigian BETWEEN ? AND ?
                         GROUP BY nhacungcap.mancc, tenncc
                         ORDER BY tonggiatri DESC;""";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(startDate.getTime()));
            pst.setTimestamp(2, new Timestamp(calendar.getTimeInMillis()));

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int mancc = rs.getInt("mancc");
                String tenncc = rs.getString("tenncc");
                int tongsoluong = rs.getInt("tongsoluong");
                long tonggiatri = rs.getLong("tonggiatri");

                ThongKeNhaCungCapDTO thongKeNhaCungCap = new ThongKeNhaCungCapDTO(mancc, tenncc, tongsoluong, tonggiatri);
                result.computeIfAbsent(mancc, k -> new ArrayList<>()).add(thongKeNhaCungCap);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<ThongKeSanPhamDTO> getDoanhSoSanPham(Date startDate, Date endDate) {
        ArrayList<ThongKeSanPhamDTO> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(endDate.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                         SELECT sanpham.masp, tensp, SUM(ctphieuxuat.soluong) AS tongsoluong, SUM(ctphieuxuat.dongia) AS tonggiatri
                         FROM sanpham
                         LEFT JOIN ctphieuxuat ON sanpham.masp = ctphieuxuat.masp
                         LEFT JOIN phieuxuat ON ctphieuxuat.maphieuxuat = phieuxuat.maphieuxuat
                         WHERE phieuxuat.thoigian BETWEEN ? AND ?
                         GROUP BY sanpham.masp, tensp
                         ORDER BY tonggiatri DESC;""";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(startDate.getTime()));
            pst.setTimestamp(2, new Timestamp(calendar.getTimeInMillis()));

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int masp = rs.getInt("masp");
                String tensp = rs.getString("tensp");
                int tongsoluong = rs.getInt("tongsoluong");
                long tonggiatri = rs.getLong("tonggiatri");

                ThongKeSanPhamDTO thongKeSanPham = new ThongKeSanPhamDTO(masp, tensp, tongsoluong, tonggiatri);
                result.add(thongKeSanPham);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<ThongKeSanPhamDTO> getDoanhSoLoaiSanPham(Date startDate, Date endDate) {
        ArrayList<ThongKeSanPhamDTO> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(endDate.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                         SELECT loaisanpham.maloai, tenloai, SUM(ctphieuxuat.soluong) AS tongsoluong, SUM(ctphieuxuat.dongia) AS tonggiatri
                         FROM loaisanpham
                         LEFT JOIN sanpham ON loaisanpham.maloai = sanpham.maloai
                         LEFT JOIN ctphieuxuat ON sanpham.masp = ctphieuxuat.masp
                         LEFT JOIN phieuxuat ON ctphieuxuat.maphieuxuat = phieuxuat.maphieuxuat
                         WHERE phieuxuat.thoigian BETWEEN ? AND ?
                         GROUP BY loaisanpham.maloai, tenloai
                         ORDER BY tonggiatri DESC;""";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(startDate.getTime()));
            pst.setTimestamp(2, new Timestamp(calendar.getTimeInMillis()));

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maloai = rs.getInt("maloai");
                String tenloai = rs.getString("tenloai");
                int tongsoluong = rs.getInt("tongsoluong");
                long tonggiatri = rs.getLong("tonggiatri");

                ThongKeSanPhamDTO thongKeSanPham = new ThongKeSanPhamDTO(maloai, tenloai, tongsoluong, tonggiatri);
                result.add(thongKeSanPham);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ThongKeTheoThangDTO> getThongKeTheoThang(int nam) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKeTungNgayTrongThang(int thang, int nam) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKeTuNgayDenNgay(String start, String end) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKe7NgayGanNhat() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static class ThongKeSanPhamDTO {

        public ThongKeSanPhamDTO() {
        }

        private ThongKeSanPhamDTO(int maloai, String tenloai, int tongsoluong, long tonggiatri) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

    
    
}

