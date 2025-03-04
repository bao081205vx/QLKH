
create database QLKH1;

use QLKH1;

BEGIN TRANSACTION;

SET NOCOUNT ON;

drop table ctkiemke;
CREATE TABLE ctkiemke (
  maphieukiemmke int NOT NULL, -- Mã phiếu kiểm kê
  masanpham int NOT NULL,      -- Mã sản phẩm
  soluong int NOT NULL,
  chenhlech int NOT NULL,
  ghichu nvarchar(255) NOT NULL,
  PRIMARY KEY (maphieukiemmke, masanpham)
);


drop table ctphieunhap;
CREATE TABLE ctphieunhap (
  maphieunhap int NOT NULL,
  maphienbansp int NOT NULL DEFAULT 0,
  soluong int NOT NULL DEFAULT 0,
  dongia int NOT NULL DEFAULT 0,
  hinhthucnhap tinyint NOT NULL DEFAULT 0,
  PRIMARY KEY (maphieunhap, maphienbansp)
);

INSERT INTO ctphieunhap (maphieunhap, maphienbansp, soluong, dongia, hinhthucnhap) VALUES
(1, 2, 6, 5000000, 0),
(1, 19, 2, 15000000, 0),
(2, 2, 7, 5000000, 0),
(2, 19, 5, 15000000, 0),
(3, 17, 12, 7000000, 0),
(4, 17, 5, 7000000, 0),
(5, 2, 2, 5000000, 0),
(8, 19, 5, 15000000, 0),
(9, 23, 7, 15500000, 0),
(10, 17, 1, 7000000, 1),
(11, 2, 1, 5000000, 1),
(12, 24, 3, 2000000, 0),
(12, 27, 3, 2000000, 0),
(12, 28, 4, 2500000, 0),
(12, 29, 5, 2500000, 0),
(13, 31, 15, 8000000, 0),
(13, 33, 15, 8000000, 0),
(13, 35, 15, 8000000, 0),
(13, 49, 30, 3200000, 0),
(14, 51, 12, 5000000, 0),
(14, 57, 7, 4000000, 0),
(14, 58, 6, 5000000, 0),
(15, 113, 13, 12000000, 0),
(15, 115, 7, 4500000, 0),
(16, 85, 10, 4100000, 0),
(16, 97, 20, 14000000, 0),
(17, 75, 9, 8200000, 0),
(18, 89, 5, 5200000, 0),
(18, 91, 5, 5200000, 0),
(19, 93, 3, 2500000, 0),
(20, 93, 5, 2600000, 0),
(21, 125, 5, 2500000, 0),
(23, 17, 2, 8000000, 0);

drop table ctphieuxuat;
CREATE TABLE ctphieuxuat (
  maphieuxuat int NOT NULL,
  maphienbansp int NOT NULL DEFAULT 0,
  soluong int NOT NULL DEFAULT 0,
  dongia int NOT NULL DEFAULT 0,
  PRIMARY KEY (maphieuxuat, maphienbansp)
);

INSERT INTO ctphieuxuat (maphieuxuat, maphienbansp, soluong, dongia) VALUES
(2, 19, 1, 16500000),
(3, 19, 3, 16500000),
(4, 17, 5, 7800000),
(5, 17, 4, 7800000),
(6, 2, 4, 5500000),
(7, 17, 1, 7800000),
(8, 17, 3, 7800000),
(9, 2, 4, 5500000),
(10, 17, 2, 7800000),
(11, 19, 3, 16500000),
(12, 2, 2, 5500000),
(12, 19, 3, 16500000),
(13, 2, 1, 5500000),
(14, 113, 3, 14000000),
(15, 115, 2, 5500000),
(16, 91, 2, 6400000),
(16, 113, 3, 14000000),
(17, 89, 5, 6400000),
(17, 91, 3, 6400000),
(18, 75, 3, 9500000),
(19, 23, 2, 17000000),
(19, 27, 1, 2890000),
(20, 31, 2, 9000000),
(21, 51, 3, 5790000),
(22, 35, 4, 9000000),
(23, 19, 1, 16500000),
(24, 49, 3, 4400000),
(25, 17, 2, 7800000);

drop table ctquyen;
CREATE TABLE ctquyen (
  manhomquyen int NOT NULL,
  machucnang nvarchar(50) NOT NULL,
  hanhdong nvarchar(50) NOT NULL,
  PRIMARY KEY (manhomquyen, machucnang, hanhdong)
);

INSERT INTO ctquyen (manhomquyen, machucnang, hanhdong) VALUES
(1, 'khachhang', 'create'),
(1, 'khachhang', 'delete'),
(1, 'khachhang', 'update'),
(1, 'khachhang', 'view'),
(1, 'khuvuckho', 'create'),
(1, 'khuvuckho', 'delete'),
(1, 'khuvuckho', 'update'),
(1, 'khuvuckho', 'view'),
(1, 'nhacungcap', 'create'),
(1, 'nhacungcap', 'delete'),
(1, 'nhacungcap', 'update'),
(1, 'nhacungcap', 'view'),
(1, 'nhanvien', 'create'),
(1, 'nhanvien', 'delete'),
(1, 'nhanvien', 'update'),
(1, 'nhanvien', 'view'),
(1, 'nhaphang', 'create'),
(1, 'nhaphang', 'delete'),
(1, 'nhaphang', 'update'),
(1, 'nhaphang', 'view'),
(1, 'nhomquyen', 'create'),
(1, 'nhomquyen', 'delete'),
(1, 'nhomquyen', 'update'),
(1, 'nhomquyen', 'view'),
(1, 'sanpham', 'create'),
(1, 'sanpham', 'delete'),
(1, 'sanpham', 'update'),
(1, 'sanpham', 'view'),
(1, 'taikhoan', 'create'),
(1, 'taikhoan', 'delete'),
(1, 'taikhoan', 'update'),
(1, 'taikhoan', 'view'),
(1, 'thongke', 'create'),
(1, 'thongke', 'delete'),
(1, 'thongke', 'update'),
(1, 'thongke', 'view'),
(1, 'thuoctinh', 'create'),
(1, 'thuoctinh', 'delete'),
(1, 'thuoctinh', 'update'),
(1, 'thuoctinh', 'view'),
(1, 'xuathang', 'create'),
(1, 'xuathang', 'delete'),
(1, 'xuathang', 'update'),
(1, 'xuathang', 'view'),
(2, 'khuvuckho', 'create'),
(2, 'khuvuckho', 'update'),
(2, 'khuvuckho', 'view'),
(2, 'nhacungcap', 'create'),
(2, 'nhacungcap', 'update'),
(2, 'nhacungcap', 'view'),
(2, 'nhaphang', 'create'),
(2, 'nhaphang', 'update'),
(2, 'nhaphang', 'view'),
(2, 'sanpham', 'create'),
(2, 'sanpham', 'update'),
(2, 'sanpham', 'view'),
(2, 'thuoctinh', 'create'),
(2, 'thuoctinh', 'delete'),
(2, 'thuoctinh', 'update'),
(2, 'thuoctinh', 'view'),
(3, 'khachhang', 'create'),
(3, 'khachhang', 'update'),
(3, 'khachhang', 'view'),
(3, 'sanpham', 'update'),
(3, 'sanpham', 'view'),
(3, 'xuathang', 'create'),
(3, 'xuathang', 'update'),
(3, 'xuathang', 'view'),
(4, 'donvitinh', 'view'),
(4, 'khuvuckho', 'view'),
(4, 'kiemke', 'view'),
(4, 'loaisanpham', 'view'),
(4, 'nhacungcap', 'view'),
(5, 'khachhang', 'view'),
(5, 'khuvuckho', 'view'),
(6, 'khuvuckho', 'view'),
(6, 'kiemke', 'view'),
(6, 'loaisanpham', 'view'),
(6, 'nhacungcap', 'view'),
(6, 'nhanvien', 'view'),
(7, 'loaisanpham', 'create'),
(7, 'nhanvien', 'create'),
(7, 'sanpham', 'create'),
(7, 'xuathang', 'create'),
(8, 'donvitinh', 'view'),
(9, 'khachhang', 'view'),
(9, 'khuvuckho', 'view'),
(10, 'khachhang', 'view'),
(10, 'khuvuckho', 'view'),
(10, 'nhanvien', 'view');

drop table ctsanpham;
CREATE TABLE ctsanpham (
  maimei nvarchar(255) NOT NULL, -- Mã imei của sản phẩm
  maphienbansp int NOT NULL,
  maphieunhap int NOT NULL,
  maphieuxuat int DEFAULT NULL,
  tinhtrang int NOT NULL,
  PRIMARY KEY (maimei)
);

INSERT INTO ctsanpham (maimei, maphienbansp, maphieunhap, maphieuxuat, tinhtrang) VALUES
('107725056444797', 57, 14, NULL, 1),
('107725056444798', 57, 14, NULL, 1),
('107725056444799', 57, 14, NULL, 1),
('107725056444800', 57, 14, NULL, 1),
('107725056444801', 57, 14, NULL, 1),
('107725056444802', 57, 14, NULL, 1),
('107725056444803', 57, 14, NULL, 1),
('111111111111111', 17, 23, 25, 0),
('111111111111112', 17, 23, 25, 0),
('128680626510768', 27, 12, NULL, 1),
('128680626510769', 27, 12, 19, 0),
('128680626510770', 27, 12, NULL, 1),
('191487469319798', 29, 12, NULL, 1),
('191487469319799', 29, 12, NULL, 1),
('191487469319800', 29, 12, NULL, 1),
('191487469319801', 29, 12, NULL, 1),
('191487469319802', 29, 12, NULL, 1),
('201865493271034', 19, 8, NULL, 1),
('201865493271035', 19, 8, 11, 0),
('201865493271036', 19, 8, 11, 0),
('201865493271037', 19, 8, 11, 0),
('201865493271038', 19, 8, 23, 0),
('209563810276493', 23, 9, NULL, 1),
('209563810276494', 23, 9, NULL, 1),
('209563810276495', 23, 9, 19, 0),
('209563810276496', 23, 9, 19, 0),
('209563810276497', 23, 9, NULL, 1),
('209563810276498', 23, 9, NULL, 1),
('209563810276499', 23, 9, NULL, 1),
('234307273503481', 33, 13, NULL, 1),
('234307273503482', 33, 13, NULL, 1),
('234307273503483', 33, 13, NULL, 1),
('234307273503484', 33, 13, NULL, 1),
('234307273503485', 33, 13, NULL, 1),
('234307273503486', 33, 13, NULL, 1),
('234307273503487', 33, 13, NULL, 1),
('234307273503488', 33, 13, NULL, 1),
('234307273503489', 33, 13, NULL, 1),
('234307273503490', 33, 13, NULL, 1),
('234307273503491', 33, 13, NULL, 1),
('234307273503492', 33, 13, NULL, 1),
('234307273503493', 33, 13, NULL, 1),
('234307273503494', 33, 13, NULL, 1),
('234307273503495', 33, 13, NULL, 1),
('237439786201794', 85, 16, NULL, 1),
('237439786201795', 85, 16, NULL, 1),
('237439786201796', 85, 16, NULL, 1),
('237439786201797', 85, 16, NULL, 1),
('237439786201798', 85, 16, NULL, 1),
('237439786201799', 85, 16, NULL, 1),
('237439786201800', 85, 16, NULL, 1),
('237439786201801', 85, 16, NULL, 1),
('237439786201802', 85, 16, NULL, 1),
('237439786201803', 85, 16, NULL, 1),
('248644019558673', 58, 14, NULL, 1),
('248644019558674', 58, 14, NULL, 1),
('248644019558675', 58, 14, NULL, 1),
('248644019558676', 58, 14, NULL, 1),
('248644019558677', 58, 14, NULL, 1),
('248644019558678', 58, 14, NULL, 1),
('267300933303009', 113, 15, NULL, 1),
('267300933303010', 113, 15, 14, 0),
('267300933303011', 113, 15, 14, 0),
('267300933303012', 113, 15, 14, 0),
('267300933303013', 113, 15, NULL, 1),
('267300933303014', 113, 15, NULL, 1),
('267300933303015', 113, 15, 16, 0),
('267300933303016', 113, 15, 16, 0),
('267300933303017', 113, 15, 16, 0),
('267300933303018', 113, 15, NULL, 1),
('267300933303019', 113, 15, NULL, 1),
('267300933303020', 113, 15, NULL, 1),
('267300933303021', 113, 15, NULL, 1),
('325645285296325', 125, 21, NULL, 1),
('325645285296326', 125, 21, NULL, 1),
('325645285296327', 125, 21, NULL, 1),
('325645285296328', 125, 21, NULL, 1),
('325645285296329', 125, 21, NULL, 1),
('354091067813468', 2, 5, NULL, 1),
('354091067813469', 2, 5, 6, 0),
('355663242747336', 17, 4, 4, 0),
('355663242747337', 17, 4, 4, 0),
('355663242747338', 17, 4, 4, 0),
('355663242747339', 17, 4, 4, 0),
('355663242747340', 17, 4, 4, 0),
('356285038690365', 93, 20, NULL, 1),
('356285038690366', 93, 20, NULL, 1),
('356285038690367', 93, 20, NULL, 1),
('356285038690368', 93, 20, NULL, 1),
('356285038690369', 93, 20, NULL, 1),
('356285077460984', 19, 1, 2, 0),
('356285077460985', 19, 1, 3, 0),
('356285077460989', 19, 2, 3, 0),
('356285077460990', 19, 2, 3, 0),
('356285077460991', 19, 2, 12, 0),
('356285077460992', 19, 2, 12, 0),
('356285077460993', 19, 2, 12, 0),
('356285088460123', 2, 2, 6, 0),
('356285088460124', 2, 2, 6, 0),
('356285088460125', 2, 2, 6, 0),
('356285088460126', 2, 2, NULL, 1),
('356285088460127', 2, 2, NULL, 1),
('356285088460128', 2, 2, 9, 0),
('356285088460129', 2, 2, 9, 0),
('356285088460876', 2, 1, 9, 0),
('356285088460877', 2, 1, 9, 0),
('356285088460878', 2, 1, NULL, 1),
('356285088460879', 2, 1, NULL, 1),
('356285088460880', 2, 1, 12, 0),
('356285088460881', 2, 1, 12, 0),
('356679247460989', 17, 3, 5, 0),
('356679247460990', 17, 3, 5, 0),
('356679247460991', 17, 3, 5, 0),
('356679247460992', 17, 3, NULL, 1),
('356679247460993', 17, 3, 5, 0),
('356679247460994', 17, 3, NULL, 1),
('356679247460995', 17, 3, 8, 0),
('356679247460996', 17, 3, 8, 0),
('356679247460997', 17, 3, 7, 0),
('356679247460998', 17, 3, 8, 0),
('356679247460999', 17, 3, NULL, 1),
('356679247461000', 17, 3, 10, 0),
('427856011841915', 24, 12, NULL, 1),
('427856011841916', 24, 12, NULL, 1),
('427856011841917', 24, 12, NULL, 1),
('493536926712616', 51, 14, NULL, 1),
('493536926712617', 51, 14, 21, 0),
('493536926712618', 51, 14, 21, 0),
('493536926712619', 51, 14, 21, 0),
('493536926712620', 51, 14, NULL, 1),
('493536926712621', 51, 14, NULL, 1),
('493536926712622', 51, 14, NULL, 1),
('493536926712623', 51, 14, NULL, 1),
('493536926712624', 51, 14, NULL, 1),
('493536926712625', 51, 14, NULL, 1),
('493536926712626', 51, 14, NULL, 1),
('493536926712627', 51, 14, NULL, 1),
('514897969548020', 28, 12, NULL, 1),
('514897969548021', 28, 12, NULL, 1),
('514897969548022', 28, 12, NULL, 1),
('514897969548023', 28, 12, NULL, 1),
('578559728952141', 35, 13, NULL, 1),
('578559728952142', 35, 13, NULL, 1),
('578559728952143', 35, 13, NULL, 1),
('578559728952144', 35, 13, 22, 0),
('578559728952145', 35, 13, 22, 0),
('578559728952146', 35, 13, 22, 0),
('578559728952147', 35, 13, 22, 0),
('578559728952148', 35, 13, NULL, 1),
('578559728952149', 35, 13, NULL, 1),
('578559728952150', 35, 13, NULL, 1),
('578559728952151', 35, 13, NULL, 1),
('578559728952152', 35, 13, NULL, 1),
('578559728952153', 35, 13, NULL, 1),
('578559728952154', 35, 13, NULL, 1),
('578559728952155', 35, 13, NULL, 1),
('630481155578246', 97, 16, NULL, 1),
('630481155578247', 97, 16, NULL, 1),
('630481155578248', 97, 16, NULL, 1),
('630481155578249', 97, 16, NULL, 1),
('630481155578250', 97, 16, NULL, 1),
('630481155578251', 97, 16, NULL, 1),
('630481155578252', 97, 16, NULL, 1),
('630481155578253', 97, 16, NULL, 1),
('630481155578254', 97, 16, NULL, 1),
('630481155578255', 97, 16, NULL, 1),
('630481155578256', 97, 16, NULL, 1),
('630481155578257', 97, 16, NULL, 1),
('630481155578258', 97, 16, NULL, 1),
('630481155578259', 97, 16, NULL, 1),
('630481155578260', 97, 16, NULL, 1),
('630481155578261', 97, 16, NULL, 1),
('630481155578262', 97, 16, NULL, 1),
('630481155578263', 97, 16, NULL, 1),
('630481155578264', 97, 16, NULL, 1),
('630481155578265', 97, 16, NULL, 1),
('663695386896779', 115, 15, NULL, 1),
('663695386896780', 115, 15, NULL, 1),
('663695386896781', 115, 15, NULL, 1),
('663695386896782', 115, 15, NULL, 1),
('663695386896783', 115, 15, NULL, 1),
('663695386896784', 115, 15, 15, 0),
('663695386896785', 115, 15, 15, 0),
('692900977366244', 93, 19, NULL, 1),
('692900977366245', 93, 19, NULL, 1),
('692900977366246', 93, 19, NULL, 1),
('692900977366247', 93, 19, NULL, 1),
('692900977366248', 93, 19, NULL, 1),
('692900977366249', 93, 19, NULL, 1),
('845223209939936', 91, 18, 17, 0),
('845223209939937', 91, 18, 17, 0),
('845223209939938', 91, 18, 17, 0),
('845223209939939', 91, 18, 16, 0),
('845223209939940', 91, 18, 16, 0),
('853057665280035', 31, 13, 20, 0),
('853057665280036', 31, 13, 20, 0),
('853057665280037', 31, 13, NULL, 1),
('853057665280038', 31, 13, NULL, 1),
('853057665280039', 31, 13, NULL, 1),
('853057665280040', 31, 13, NULL, 1),
('853057665280041', 31, 13, NULL, 1),
('853057665280042', 31, 13, NULL, 1),
('853057665280043', 31, 13, NULL, 1),
('853057665280044', 31, 13, NULL, 1),
('853057665280045', 31, 13, NULL, 1),
('853057665280046', 31, 13, NULL, 1),
('853057665280047', 31, 13, NULL, 1),
('853057665280048', 31, 13, NULL, 1),
('853057665280049', 31, 13, NULL, 1),
('876068039547345', 75, 17, 18, 0),
('876068039547346', 75, 17, 18, 0),
('876068039547347', 75, 17, 18, 0),
('876068039547348', 75, 17, NULL, 1),
('876068039547349', 75, 17, NULL, 1),
('876068039547350', 75, 17, NULL, 1),
('876068039547351', 75, 17, NULL, 1),
('876068039547352', 75, 17, NULL, 1),
('876068039547353', 75, 17, NULL, 1),
('912609172880156', 17, 10, 10, 0),
('919448001026640', 49, 13, NULL, 1),
('919448001026641', 49, 13, NULL, 1),
('919448001026642', 49, 13, NULL, 1),
('919448001026643', 49, 13, NULL, 1),
('919448001026644', 49, 13, NULL, 1),
('919448001026645', 49, 13, NULL, 1),
('919448001026646', 49, 13, 24, 0),
('919448001026647', 49, 13, 24, 0),
('919448001026648', 49, 13, 24, 0),
('919448001026649', 49, 13, NULL, 1),
('919448001026650', 49, 13, NULL, 1),
('919448001026651', 49, 13, NULL, 1),
('919448001026652', 49, 13, NULL, 1),
('919448001026653', 49, 13, NULL, 1),
('919448001026654', 49, 13, NULL, 1),
('919448001026655', 49, 13, NULL, 1),
('919448001026656', 49, 13, NULL, 1),
('919448001026657', 49, 13, NULL, 1),
('919448001026658', 49, 13, NULL, 1),
('919448001026659', 49, 13, NULL, 1),
('919448001026660', 49, 13, NULL, 1),
('919448001026661', 49, 13, NULL, 1),
('919448001026662', 49, 13, NULL, 1),
('919448001026663', 49, 13, NULL, 1),
('919448001026664', 49, 13, NULL, 1),
('919448001026665', 49, 13, NULL, 1),
('919448001026666', 49, 13, NULL, 1),
('919448001026667', 49, 13, NULL, 1),
('919448001026668', 49, 13, NULL, 1),
('919448001026669', 49, 13, NULL, 1),
('964768426041520', 89, 18, 17, 0),
('964768426041521', 89, 18, 17, 0),
('964768426041522', 89, 18, 17, 0),
('964768426041523', 89, 18, 17, 0),
('964768426041524', 89, 18, 17, 0),
('968080239661041', 2, 11, 13, 0);

drop table danhmucchucnang;
CREATE TABLE danhmucchucnang (
  machucnang nvarchar(50) NOT NULL,
  tenchucnang nvarchar(255) NOT NULL,
  trangthai int NOT NULL,
  PRIMARY KEY (machucnang)
);

INSERT INTO danhmucchucnang (machucnang, tenchucnang, trangthai) VALUES
('khachhang', N'Quản lý khách hàng', 0),
('khuvuckho', N'Quản lý khu vực kho', 0),
('nhacungcap', N'Quản lý nhà cung cấp', 0),
('nhanvien', N'Quản lý nhân viên', 0),
('nhaphang', N'Quản lý nhập hàng', 0),
('nhomquyen', N'Quản lý nhóm quyền', 0),
('sanpham', N'Quản lý sản phẩm', 0),
('taikhoan', N'Quản lý tài khoản', 0),
('thongke', N'Quản lý thống kê', 0),
('thuoctinh', N'Quản lý thuộc tính', 0),
('xuathang', N'Quản lý xuất hàng', 0);

drop table dungluongram;
CREATE TABLE dungluongram (
  madlram int IDENTITY(1,1) NOT NULL,
  kichthuocram int DEFAULT NULL,
  trangthai tinyint DEFAULT 1,
  PRIMARY KEY (madlram)
);
SELECT IDENT_CURRENT('dungluongram');
SET IDENTITY_INSERT dungluongram ON;
-- Chèn dữ liệu vào bảng dungluongram
INSERT INTO dungluongram (madlram, kichthuocram, trangthai) VALUES
(1, 3, 1),
(2, 2, 1),
(3, 4, 1),
(4, 6, 1),
(5, 8, 1),
(6, 12, 1);
SET IDENTITY_INSERT dungluongram OFF;

drop table dungluongrom;
CREATE TABLE dungluongrom (
  madlrom int IDENTITY(1,1) NOT NULL,
  kichthuocrom int DEFAULT NULL,
  trangthai tinyint DEFAULT 1,
  PRIMARY KEY (madlrom)
);

SET IDENTITY_INSERT dungluongrom ON;
INSERT INTO dungluongrom (madlrom, kichthuocrom, trangthai) VALUES
(1, 32, 1),
(2, 64, 1),
(3, 128, 1),
(4, 256, 1),
(5, 512, 1),
(6, 1024, 1);
SET IDENTITY_INSERT dungluongrom OFF;

drop table hedieuhanh;
CREATE TABLE hedieuhanh (
  mahedieuhanh int IDENTITY(1,1) PRIMARY KEY,
  tenhedieuhanh nvarchar(255) NOT NULL,
  trangthai tinyint DEFAULT NULL
);

SET IDENTITY_INSERT hedieuhanh ON;
INSERT INTO hedieuhanh (mahedieuhanh, tenhedieuhanh, trangthai) VALUES
(1, 'Android', 1),
(2, 'IOS', 1),
(3, '', 0);
SET IDENTITY_INSERT hedieuhanh OFF;

drop table khachhang;
CREATE TABLE khachhang (
  makh int  NOT NULL,
  tenkhachhang nvarchar(255) NOT NULL,
  diachi nvarchar(255) NOT NULL,
  sdt nvarchar(255) NOT NULL,
  trangthai int NOT NULL,
  ngaythamgia datetime NOT NULL DEFAULT GETDATE(),
  PRIMARY KEY (makh)
);

INSERT INTO khachhang (makh, tenkhachhang, diachi, sdt, trangthai, ngaythamgia) VALUES
(1, N'Nguyễn Văn A', N'Gia Đức, Ân Đức, Hoài Ân, Bình Định', '0387913347', 1, '2023-04-19 09:52:29'),
(2, N'Trần Nhất Nhất', N'205 Trần Hưng Đạo, Phường 10, Quận 5, Thành phố Hồ Chí Minh', '0123456789', 1, '2023-04-19 09:52:29'),
(3, N'Hoàng Gia Bo', N'Khoa Trường, Hoài Ân, Bình Định', '0987654321', 1, '2023-04-19 09:52:29'),
(4, N'Hồ Minh Hưng', N'Khoa Trường, Hoài Ân, Bình Định', '0867987456', 1, '2023-04-19 09:52:29'),
(29, N'Nguyễn Thị Minh Anh', N'123 Phố Huế, Quận Hai Bà Trưng, Hà Nội', '0935123456', 1, '2023-04-30 17:59:57'),
(30, N'Trần Đức Minh', N'789 Đường Lê Hồng Phong, Thành phố Đà Nẵng', '0983456789', 1, '2023-04-30 18:08:12'),
(31, N'Lê Hải Yến', N'456 Tôn Thất Thuyết, Quận 4, Thành phố Hồ Chí Minh', '0977234567', 1, '2023-04-30 18:08:47'),
(32, N'Phạm Thanh Hằng', N'102 Lê Duẩn, Thành phố Hải Phòng', '0965876543', 1, '2023-04-30 18:12:59'),
(33, N'Hoàng Đức Anh', N'321 Lý Thường Kiệt, Thành phố Cần Thơ', '0946789012', 1, '2023-04-30 18:13:47'),
(34, N'Ngô Thanh Tùng', N'987 Trần Hưng Đạo, Quận 1, Thành phố Hồ Chí Minh', '0912345678', 1, '2023-04-30 18:14:12'),
(35, N'Võ Thị Kim Ngân', N'555 Nguyễn Văn Linh, Quận Nam Từ Liêm, Hà Nội', '0916789123', 1, '2023-04-30 18:15:11'),
(36, N'Đỗ Văn Tú', N'777 Hùng Vương, Thành phố Huế', '0982345678', 1, '2023-04-30 18:15:56'),
(37, N'Lý Thanh Trúc', N'888 Nguyễn Thái Học, Quận Ba Đình, Hà Nội', '0982123456', 1, '2023-04-30 18:16:22'),
(38, N'Bùi Văn Hoàng', N'222 Đường 2/4, Thành phố Nha Trang', '0933789012', 1, '2023-04-30 18:16:53'),
(39, N'Lê Văn Thành', N'23 Đường 3 Tháng 2, Quận 10, TP. Hồ Chí Minh', '0933456789', 1, '2023-04-30 18:17:46'),
(40, N'Nguyễn Thị Lan Anh', N'456 Lê Lợi, Quận 1, TP. Hà Nội', '0965123456', 1, '2023-04-30 18:18:10'),
(41, N'Phạm Thị Mai', N'234 Lê Hồng Phong, Quận 5, TP. Hồ Chí Minh', '0946789012', 1, '2023-04-30 18:18:34'),
(42, N'Hoàng Văn Nam', N' 567 Phố Huế, Quận Hai Bà Trưng, Hà Nội', '0912345678', 1, '2023-04-30 18:19:16');


drop table khuvuckho;
CREATE TABLE khuvuckho (
  makhuvuc int  NOT NULL,
  tenkhuvuc nvarchar(255) NOT NULL,
  ghichu nvarchar(255) NOT NULL,
  trangthai int NOT NULL,
  PRIMARY KEY (makhuvuc)
);

INSERT INTO khuvuckho (makhuvuc, tenkhuvuc, ghichu, trangthai) VALUES
(1, N'Khu vực A', 'Apple', 1),
(2, N'Khu vực B', 'Xiaomi', 1),
(3, N'Khu vực C', 'Samsung', 1),
(4, N'Khu vực D', 'Realme', 1),
(5, N'Khu vực E', 'Oppo', 1);

drop table mausac;
CREATE TABLE mausac (
  mamau int NOT NULL,
  tenmau nvarchar(50) NOT NULL DEFAULT '0',
  trangthai tinyint DEFAULT 1,
  PRIMARY KEY (mamau),
  UNIQUE (tenmau)
);

INSERT INTO mausac (mamau, tenmau, trangthai) VALUES
(1, N'Xanh', 1),
(2, N'Đỏ', 1),
(3, N'Vàng', 1),
(4, N'Bạc', 1),
(5, N'Hồng', 1),
(6, N'Đen', 1),
(7, N'Xanh ngọc', 1),
(8, N'Tím', 1),
(9, N'Xanh dương', 1),
(10, N'Xanh lá', 1),
(11, N'Cam', 1),
(12, N'Xám', 1),
(13, N'Trắng', 1);


drop table nhacungcap;
CREATE TABLE nhacungcap (
  manhacungcap int  NOT NULL,
  tennhacungcap nvarchar(255) NOT NULL,
  diachi nvarchar(255) NOT NULL,
  email nvarchar(255) NOT NULL,
  sdt nvarchar(255) NOT NULL,
  trangthai int NOT NULL DEFAULT 1,
  PRIMARY KEY (manhacungcap)
);

INSERT INTO nhacungcap (manhacungcap, tennhacungcap, diachi, email, sdt, trangthai) VALUES
(1, N'Công Ty TNHH Thế Giới Di Động', N' Phòng 6.5, Tầng6, Tòa Nhà E. Town 2, 364 Cộng Hòa, P. 13, Q. Tân Bình, Tp. Hồ Chí Minh', 'lienhe@thegioididong.com', '02835100100', 1),
(2, N'Công ty Vivo Việt Nam', N'Số 79 đường số 6, Hưng Phước 4, Phú Mỹ Hưng, quận 7, TPHCM', 'contact@paviet.vn', '19009477', 1),
(3, N'Công Ty TNHH Bao La', N'8/38 Đinh Bô Lĩnh, P.24, Q. Bình Thạnh, Tp. Hồ Chí Minh', 'contact@baola.vn', '02835119060', 1),
(4, N'Công Ty Nokia', N'Phòng 703, Tầng7, Tòa Nhà Metropolitan, 235 Đồng Khởi, P. Bến Nghé, Q. 1, Tp. Hồ Chí Minh (TPHCM)', 'chau.nguyen@nokia.com', '02838236894', 1),
(5, N'Hệ Thống Phân Phối Chính Hãng Xiaomi', N'261 Lê Lợi, P. Lê Lợi, Q. Ngô Quyền, Tp. Hải Phòng', 'info@mihome.vn', '0365888866', 1),
(6, N'Công Ty Samsung Việt Nam', N'Tòa nhà tài chính Bitexco, 2 Hải Triều, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh', 'contact@samsung.vn', '0988788456', 1),
(7, N'Công ty Oppo Việt Nam', N'27 Đ. Nguyễn Trung Trực, Phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh', 'oppovietnam@oppo.vn', '0456345234', 1);


drop table nhanvien;
CREATE TABLE nhanvien (
  manv int  NOT NULL,
  hoten nvarchar(255) NOT NULL,
  gioitinh int NOT NULL,
  ngaysinh date NOT NULL,
  sdt nvarchar(50) NOT NULL,
  email nvarchar(255) NOT NULL,
  trangthai int NOT NULL,
  PRIMARY KEY (manv)
);
INSERT INTO nhanvien (manv, hoten, gioitinh, ngaysinh, sdt, email, trangthai) VALUES
(1, N'Trần Nhật Sinh', 1, '2003-12-20', '0387913347', 'transinh085@gmail.com', 1),
(2, N'Nguyễn Văn Mạnh', 1, '2023-04-01', '0364276451', 'manhnv@gmail.com', 1),
(3, N'Đỗ Nam Công Chính', 1, '2003-04-11', '0123456789', 'chinchin@gmail.com', 1),
(4, N'Đinh Ngọc Ân', 1, '2003-04-03', '0123456789', 'ngocan@gmail.com', 1),
(5, N'Vũ Trung Hiếu', 1, '2023-05-06', '0123456789', 'hieu@gmail.com', 1);


drop table nhomquyen;
CREATE TABLE nhomquyen (
  manhomquyen int  NOT NULL,
  tennhomquyen nvarchar(255) NOT NULL,
  trangthai int NOT NULL,
  PRIMARY KEY (manhomquyen)
);

INSERT INTO nhomquyen (manhomquyen, tennhomquyen, trangthai) VALUES
(1, N'Quản lý kho', 1),
(2, N'Nhân viên nhập hàng', 1),
(3, 'Nhân viên xuất hàng', 1),
(4, N'Thủ kho', 0),
(5, N'Nhân viên kiểm kho', 0),
(6, N'tesst', 0),
(7, N'', 0),
(8, N'ok', 0),
(9, N'ok', 0),
(10, N'abc', 1);


drop table phienbansanpham;
CREATE TABLE phienbansanpham (
  maphienbansp int NOT NULL,
  masp int DEFAULT NULL,
  rom int DEFAULT NULL,
  ram int DEFAULT 0,
  mausac int DEFAULT NULL,
  gianhap int DEFAULT NULL,
  giaxuat int DEFAULT NULL,
  soluongton int DEFAULT 0,
  trangthai tinyint NOT NULL DEFAULT 1,
  PRIMARY KEY (maphienbansp),
  FOREIGN KEY (masp) REFERENCES sanpham(masp) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO phienbansanpham (maphienbansp, masp, rom, ram, mausac, gianhap, giaxuat, soluongton, trangthai) VALUES
(2, 1, 1, 1, 1, 5000000, 5500000, 5, 1),
(15, 1, 3, 4, 1, 6000000, 6500000, 0, 1),
(17, 2, 3, 5, 1, 7000000, 7800000, 3, 1),
(19, 3, 3, 3, 5, 15000000, 16500000, 1, 1),
(21, 3, 3, 3, 6, 15000000, 16500000, 0, 1),
(23, 3, 3, 3, 1, 15500000, 17000000, 5, 1),
(24, 4, 1, 1, 1, 2000000, 2890000, 3, 1),
(25, 4, 1, 1, 1, 2000000, 2890000, 0, 0),
(26, 4, 1, 1, 1, 2000000, 2890000, 0, 0),
(27, 4, 1, 1, 6, 2000000, 2890000, 2, 1),
(28, 4, 2, 1, 6, 2500000, 3390000, 4, 1),
(29, 4, 2, 1, 1, 2500000, 3390000, 5, 1),
(31, 5, 3, 5, 8, 8000000, 9000000, 13, 1),
(33, 5, 3, 5, 6, 8000000, 9000000, 15, 1),
(35, 5, 3, 5, 1, 8000000, 9000000, 11, 1),
(37, 5, 4, 5, 1, 9000000, 10000000, 0, 1),
(39, 5, 4, 5, 6, 9000000, 10000000, 0, 1),
(41, 5, 4, 5, 8, 9000000, 10000000, 0, 1),
(43, 6, 3, 3, 9, 3000000, 4100000, 0, 1),
(45, 6, 3, 3, 6, 3000000, 4100000, 0, 1),
(47, 6, 3, 4, 6, 3200000, 4400000, 0, 1),
(49, 6, 3, 4, 9, 3200000, 4400000, 27, 1),
(51, 7, 3, 5, 9, 5000000, 5790000, 9, 1),
(53, 7, 3, 5, 10, 5000000, 5790000, 0, 1),
(55, 7, 3, 3, 9, 4000000, 4890000, 0, 1),
(57, 7, 3, 3, 10, 4000000, 4890000, 7, 1),
(58, 7, 3, 5, 12, 5000000, 5790000, 6, 1),
(59, 7, 3, 3, 12, 4000000, 4890000, 0, 1),
(61, 8, 2, 3, 9, 2000000, 2990000, 0, 1),
(63, 8, 2, 3, 10, 2000000, 2990000, 0, 1),
(65, 8, 2, 3, 12, 2000000, 2990000, 0, 1),
(67, 8, 3, 3, 9, 2200000, 3290000, 0, 1),
(69, 8, 3, 3, 10, 2200000, 3290000, 0, 1),
(71, 8, 3, 3, 12, 2200000, 3290000, 0, 1),
(73, 9, 4, 5, 10, 8200000, 9500000, 0, 1),
(75, 9, 4, 5, 9, 8200000, 9500000, 6, 1),
(77, 10, 3, 3, 11, 3600000, 4700000, 0, 1),
(79, 10, 3, 3, 6, 3600000, 4700000, 0, 1),
(81, 10, 3, 3, 9, 3600000, 4700000, 0, 1),
(83, 10, 3, 4, 11, 4100000, 5200000, 0, 1),
(85, 10, 3, 4, 6, 4100000, 5200000, 10, 1),
(87, 10, 3, 4, 9, 4100000, 5200000, 0, 1),
(89, 11, 4, 5, 6, 5200000, 6400000, 0, 1),
(91, 11, 4, 5, 13, 5200000, 6400000, 0, 1),
(93, 12, 2, 3, 13, 2500000, 3000000, 11, 1),
(95, 12, 2, 3, 9, 2500000, 3000000, 0, 1),
(97, 13, 3, 5, 13, 14000000, 16000000, 20, 1),
(99, 13, 3, 5, 10, 14000000, 16000000, 0, 1),
(101, 13, 3, 5, 4, 14000000, 16000000, 0, 1),
(103, 13, 3, 5, 6, 14000000, 16000000, 0, 1),
(105, 13, 4, 5, 13, 16000000, 18000000, 0, 1),
(107, 13, 4, 5, 10, 16000000, 18000000, 0, 1),
(109, 13, 4, 5, 4, 16000000, 18000000, 0, 1),
(111, 13, 4, 5, 6, 16000000, 18000000, 0, 1),
(113, 14, 4, 5, 1, 12000000, 14000000, 7, 1),
(115, 15, 3, 5, 4, 4500000, 5500000, 5, 1),
(117, 15, 3, 5, 6, 4500000, 5500000, 0, 1),
(118, 1, 1, 1, 1, 5000000, 5500000, 0, 0),
(119, 2, 3, 5, 1, 7000000, 7800000, 0, 0),
(120, 1, 1, 1, 1, 5000000, 5500000, 0, 0),
(121, 1, 1, 1, 1, 5000000, 5500000, 0, 0),
(122, 1, 1, 1, 1, 5000000, 5500000, 0, 0),
(123, 1, 3, 4, 2, 6000000, 6500000, 0, 0),
(125, 17, 1, 1, 1, 2500000, 3000000, 5, 1);


drop table phieubaohanh;
CREATE TABLE phieubaohanh (
  maphieubaohanh int  NOT NULL,
  maimei nvarchar(255) NOT NULL,
  lydo nvarchar(50) NOT NULL,
  thoigian datetime NOT NULL DEFAULT GETDATE(),
  thoigiantra datetime DEFAULT NULL,
  PRIMARY KEY (maphieubaohanh)
);


drop table phieudoi;
CREATE TABLE phieudoi (
  maphieudoi tinyint NOT NULL DEFAULT 0,
  maimei nvarchar(255) NOT NULL,
  lydo nvarchar(255) NOT NULL,
  thoigian date DEFAULT GETDATE(),
  nguoitao int NOT NULL,
  PRIMARY KEY (maphieudoi),
  FOREIGN KEY (maimei) REFERENCES ctsanpham(maimei) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (nguoitao) REFERENCES taikhoan(manv) ON DELETE NO ACTION ON UPDATE NO ACTION
);


drop table phieukiemke;
CREATE TABLE phieukiemke (
  maphieu int NOT NULL,
  thoigian date NOT NULL DEFAULT GETDATE(),
  nguoitaophieukiemke int NOT NULL,
  PRIMARY KEY (maphieu),
  FOREIGN KEY (nguoitaophieukiemke) REFERENCES taikhoan(manv) ON DELETE NO ACTION ON UPDATE NO ACTION
);


drop table phieunhap;
CREATE TABLE phieunhap (
  maphieunhap int NOT NULL,
  thoigian datetime DEFAULT GETDATE(),
  manhacungcap int NOT NULL,
  nguoitao int NOT NULL,
  tongtien bigint NOT NULL DEFAULT 0,
  trangthai int NOT NULL DEFAULT 1,
  PRIMARY KEY (maphieunhap),
  FOREIGN KEY (manhacungcap) REFERENCES nhacungcap(manhacungcap) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (nguoitao) REFERENCES taikhoan(manv) ON DELETE NO ACTION ON UPDATE NO ACTION
);
INSERT INTO phieunhap (maphieunhap, thoigian, manhacungcap, nguoitao, tongtien, trangthai) VALUES
(1, '2023-04-01 17:34:12', 1, 1, 60000000, 1),
(2, '2023-04-03 17:42:17', 1, 1, 110000000, 1),
(3, '2023-04-04 18:07:58', 1, 1, 84000000, 1),
(4, '2023-04-04 18:19:51', 3, 4, 35000000, 1),
(5, '2023-04-06 08:18:01', 1, 1, 10000000, 1),
(8, '2023-04-07 20:33:58', 1, 1, 75000000, 1),
(9, '2023-04-07 01:09:27', 1, 1, 108500000, 1),
(10, '2023-04-07 08:42:52', 1, 1, 7000000, 1),
(11, '2023-04-10 00:22:13', 1, 1, 5000000, 1),
(12, '2023-04-13 00:52:47', 2, 1, 34500000, 1),
(13, '2023-04-13 00:56:04', 6, 1, 456000000, 1),
(14, '2023-04-14 00:57:07', 5, 1, 118000000, 1),
(15, '2023-04-15 00:59:02', 7, 1, 187500000, 1),
(16, '2023-04-16 00:59:46', 6, 1, 321000000, 1),
(17, '2023-04-17 01:00:30', 6, 1, 73800000, 1),
(18, '2023-04-19 01:01:25', 5, 1, 52000000, 1),
(19, '2023-04-20 01:02:22', 1, 1, 7500000, 1),
(20, '2023-05-09 12:09:23', 2, 1, 13000000, 1),
(21, '2023-05-10 08:17:32', 6, 1, 12500000, 1),
(23, '2023-05-10 08:25:11', 1, 1, 16000000, 1);

drop table phieutra;
CREATE TABLE phieutra (
  maphieutra int  NOT NULL,
  maimei nvarchar(255) NOT NULL,
  lydo nvarchar(255) NOT NULL,
  thoigian date DEFAULT GETDATE(),
  nguoitao int NOT NULL,
  PRIMARY KEY (maphieutra),
  FOREIGN KEY (maimei) REFERENCES ctsanpham(maimei) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (nguoitao) REFERENCES taikhoan(manv) ON DELETE NO ACTION ON UPDATE NO ACTION
);


drop table phieuxuat;
CREATE TABLE phieuxuat (
  maphieuxuat int  NOT NULL,
  thoigian datetime NOT NULL DEFAULT GETDATE(),
  tongtien bigint DEFAULT NULL,
  nguoitaophieuxuat int DEFAULT NULL,
  makh int DEFAULT NULL,
  trangthai int DEFAULT NULL,
  PRIMARY KEY (maphieuxuat),
  FOREIGN KEY (makh) REFERENCES khachhang(makh) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (nguoitaophieuxuat) REFERENCES taikhoan(manv) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO phieuxuat (maphieuxuat, thoigian, tongtien, nguoitaophieuxuat, makh, trangthai) VALUES
(2, '2023-04-04 09:56:35', 16500000, 1, 1, 1),
(3, '2023-04-04 03:18:23', 49500000, 1, 1, 1),
(4, '2023-04-05 03:19:31', 39000000, 1, 4, 1),
(5, '2023-04-06 18:30:26', 31200000, 1, 40, 1),
(6, '2023-04-06 01:01:48', 22000000, 1, 38, 1),
(7, '2023-04-06 12:39:44', 7800000, 1, 3, 1),
(8, '2023-04-08 12:40:04', 23400000, 1, 4, 1),
(9, '2023-04-09 12:40:32', 22000000, 1, 33, 1),
(10, '2023-04-11 12:40:50', 15600000, 1, 41, 1),
(11, '2023-04-11 12:42:33', 49500000, 1, 37, 1),
(12, '2023-04-12 02:31:45', 60500000, 1, 31, 1),
(13, '2023-04-13 00:23:02', 5500000, 1, 41, 1),
(14, '2023-04-30 01:52:18', 42000000, 1, 37, 1),
(15, '2023-05-01 01:57:39', 11000000, 1, 4, 1),
(16, '2023-05-02 01:58:16', 54800000, 1, 30, 1),
(17, '2023-05-03 01:59:44', 51200000, 1, 39, 1),
(18, '2023-05-04 02:00:13', 28500000, 1, 39, 1),
(19, '2023-05-05 02:01:28', 36890000, 1, 3, 1),
(20, '2023-05-06 02:06:24', 18000000, 1, 35, 1),
(21, '2023-05-07 10:08:49', 17370000, 1, 37, 1),
(22, '2023-05-08 22:56:21', 36000000, 1, 34, 1),
(23, '2023-05-09 22:57:23', 16500000, 1, 30, 1),
(24, '2023-05-10 02:55:35', 13200000, 1, 37, 1),
(25, '2023-05-10 08:28:18', 15600000, 1, 3, 1);



drop table sanpham;
--drop table sanpham;
CREATE TABLE sanpham (
    masp INT IDENTITY(1,1) PRIMARY KEY,
    tensp NVARCHAR(255) NOT NULL,
    hinhanh NVARCHAR(255),
    xuatxu INT,
    chipxuly NVARCHAR(255),
    dungluongpin INT,
    kichthuocman FLOAT,
    hedieuhanh INT,
    phienbanhdh INT,
    camerasau NVARCHAR(255),
    cameratruoc NVARCHAR(255),
    thoigianbaohanh INT,
    thuonghieu INT,
    khuvuckho INT,
    soluongton INT,
    trangthai BIT DEFAULT 1
);
SET IDENTITY_INSERT sanpham ON;
INSERT INTO sanpham (masp, tensp, hinhanh, xuatxu, chipxuly, dungluongpin, kichthuocman, hedieuhanh, phienbanhdh, camerasau, cameratruoc, thoigianbaohanh, thuonghieu, khuvuckho, soluongton, trangthai) VALUES
(1, N'Vivo Y22s', '92vivo-y22s-vang-thumb-600x600.jpg', 1, 'SnapDragon 680', 5000, 6.55, 1, 12, N'Chính 50 MP & Phụ 2 MP', '8 MP', 24, 1, 1, 5, 1),
(2, N'Samsung Galaxy A53 5G', '57samsung-galaxy-a53-cam-thumb-1-600x600.jpg', 1, 'Exynos 1280', 5000, 6.5, 1, 12, N'Chính 64 MP & Phụ 12 MP, 5 MP, 5 MP', '32 MP', 24, 3, 2, 3, 1),
(3, N'iPhone 13 mini', '997iphone-13-mini-pink-1-600x600.jpg', 1, ' Apple A15 Bionic', 2438, 5.4, 2, 15, N'2 camera 12 MP', ' 12 MP', 36, 1, 1, 6, 0),
(4, N'Vivo Y02s', '74vivo-y02s-thumb-1-2-3-600x600.jpg', 1, 'MediaTek Helio P35', 5000, 6.51, 1, 12, N'8 MP', ' 5 MP', 24, 10, 3, 14, 1),
(5, N'Samsung Galaxy A54 5G', '399samsung-galaxy-a54-5g-tim-thumb-600x600.jpg', 2, N' Exynos 1380 8 nhân', 5000, 6.4, 1, 12, N'Chính 50 MP & Phụ 12 MP, 5 MP', ' 32 MP', 24, 3, 3, 39, 1),
(6, N'Samsung Galaxy A13', '337samsung-galaxy-a14-black-thumb-600x600.jpg', 1, 'Exynos 850', 5000, 6.6, 1, 12, N'Chính 50 MP & Phụ 5 MP, 2 MP, 2 MP', '8 MP', 24, 3, 2, 27, 1),
(7, N'Xiaomi Redmi Note 12', '717xiaomi-redmi-note-12-4g-mono-den-600x600.jpg', 1, N' Snapdragon 685 8 nhân', 5000, 6.67, 1, 12, N'Chính 50 MP & Phụ 8 MP, 2 MP', '13 MP', 24, 2, 4, 22, 1),
(8, N'Xiaomi Redmi 12C', '437xiaomi-redmi-12c-grey-thumb-600x600.jpg', 1, 'MediaTek Helio G85', 5000, 6.71, 1, 12, N'Chính 50 MP & Phụ QVGA', '5 MP', 24, 1, 1, 0, 1),
(9, N'Samsung Galaxy S20 FE', '286samsung-galaxy-s20-fan-edition-xanh-la-thumbnew-600x600.jpeg', 1, N'Snapdragon 865', 4500, 6.5, 1, 12, N'Chính 12 MP & Phụ 12 MP, 8 MP', '32 MP', 24, 3, 4, 6, 1),
(10, N'Samsung Galaxy A23', '826samsung-galaxy-a23-cam-thumb-600x600.jpg', 1, 'Snapdragon 680', 5000, 6.6, 1, 12, N'Chính 50 MP & Phụ 5 MP, 2 MP, 2 MP', '8 MP', 24, 1, 1, 10, 1),
(11, N'Realme 10', '877realme-10-thumb-1-600x600.jpg', 1, 'MediaTek Helio G99', 5000, 6.4, 1, 12, N'Chính 50 MP & Phụ 2 MP', '16 MP', 24, 11, 1, 0, 1),
(12, N'Vivo Y21', '960vivo-y21-blue-01-600x600.jpg', 1, 'MediaTek Helio P35', 5000, 6.51, 1, 12, N'Chính 13 MP & Phụ 2 MP', '8 MP', 24, 10, 5, 11, 1),
(13, N'Samsung Galaxy S22+ 5G', '177Galaxy-S22-Ultra-Burgundy-600x600.jpg', 1, 'Snapdragon 8 Gen 1', 4500, 6.6, 1, 12, N'Chính 50 MP & Phụ 12 MP, 10 MP', '10 MP', 24, 3, 4, 20, 1),
(14, N'OPPO Reno6 Pro 5G', '203oppo-reno6-pro-grey-600x600.jpg', 1, 'Snapdragon 870 5G', 4500, 6.55, 1, 11, N'Chính 50 MP & Phụ 16 MP, 13 MP, 2 MP', '32 MP', 24, 3, 4, 7, 1),
(15, N' OPPO A95', '555oppo-a95-4g-bac-2-600x600.jpg', 1, 'Snapdragon 662', 5000, 6.43, 1, 11, N'Chính 48 MP & Phụ 2 MP, 2 MP', '16 MP', 24, 1, 1, 5, 1),
(17, N'Samsung Galaxy A53 5G S', '74319198933.jpg', 1, 'chip a', 5000, 5.6, 1, 12, 'msdf', 'dsfgfdg', 24, 1, 1, 5, 0);

SET IDENTITY_INSERT sanpham OFF;

--drop table taikhoan;
CREATE TABLE taikhoan (
  manv int NOT NULL,
  matkhau varchar(255) NOT NULL,
  manhomquyen int NOT NULL,
  tendangnhap varchar(50) NOT NULL DEFAULT '',
  trangthai int NOT NULL,
  otp varchar(50) DEFAULT NULL
);

-- Thêm ràng buộc khóa chính
ALTER TABLE taikhoan
ADD CONSTRAINT PK_taikhoan PRIMARY KEY (manv);

-- Thêm ràng buộc duy nhất
ALTER TABLE taikhoan
ADD CONSTRAINT UQ_tendangnhap UNIQUE (tendangnhap);

-- Thêm ràng buộc khóa ngoại cho manhomquyen
ALTER TABLE taikhoan
ADD CONSTRAINT FK_taikhoan_nhomquyen FOREIGN KEY (manhomquyen) REFERENCES nhomquyen (manhomquyen) ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Thêm ràng buộc khóa ngoại cho manv
ALTER TABLE taikhoan
ADD CONSTRAINT FK_taikhoan_nhanvien FOREIGN KEY (manv) REFERENCES nhanvien (manv) ON DELETE NO ACTION ON UPDATE NO ACTION;

INSERT INTO taikhoan (manv, matkhau, manhomquyen, tendangnhap, trangthai, otp) VALUES
(1, '$2a$12$6GSkiQ05XjTRvCW9MB6MNuf7hOJEbbeQx11Eb8oELil1OrCq6uBXm', 1, 'admin', 1, 'null'),
(2, '$2a$12$SAlAhcsudMzNEouyBaoHnOKR23ixdH0ZkcoyXUJ5gS/NFt.b4oqw6', 1, 'manhdev', 1, '451418'),
(3, '$2a$12$SAlAhcsudMzNEouyBaoHnOKR23ixdH0ZkcoyXUJ5gS/NFt.b4oqw6', 10, 'chinh', 1, NULL),
(4, '$2a$12$SAlAhcsudMzNEouyBaoHnOKR23ixdH0ZkcoyXUJ5gS/NFt.b4oqw6', 2, 'ngocan', 0, NULL),
(5, '$2a$12$SAlAhcsudMzNEouyBaoHnOKR23ixdH0ZkcoyXUJ5gS/NFt.b4oqw6', 3, 'hieunek', 0, NULL);


drop table thuonghieu;
CREATE TABLE thuonghieu (
  mathuonghieu INT IDENTITY(1,1) PRIMARY KEY,
  tenthuonghieu NVARCHAR(255) DEFAULT NULL,
  hinhanh NVARCHAR(255) DEFAULT NULL,
  mota NVARCHAR(255) DEFAULT NULL,
  trangthai TINYINT DEFAULT 1
);
SET IDENTITY_INSERT thuonghieu ON;
INSERT INTO thuonghieu (mathuonghieu, tenthuonghieu, trangthai) VALUES
(1, 'Apple', 1),
(2, 'Xiaomi', 1),
(3, 'Samsung', 1),
(4, N'Sữa tươi', 0),
(7, 'g', 0),
(8, '', 0),
(9, 'Oppo', 1),
(10, 'Vivo', 1),
(11, 'Realme', 1),
(12, 'Nokia', 1),
(13, 'Vsmart', 1),
(14, 'Apple', 0);
SET IDENTITY_INSERT thuonghieu OFF;

drop table xuatxu;
CREATE TABLE xuatxu (
  maxuatxu INT IDENTITY(1,1) PRIMARY KEY,
  tenxuatxu varchar(50) NOT NULL,
  trangthai tinyint DEFAULT 1
);

SET IDENTITY_INSERT xuatxu ON;
INSERT INTO xuatxu (maxuatxu, tenxuatxu, trangthai) VALUES
(1, N'Trung Quốc', 1),
(2, N'Hàn Quốc', 1),
(3, N'Việt Nam', 1),
(4, N'USA', 1);
SET IDENTITY_INSERT xuatxu OFF;



COMMIT TRANSACTION;
