use master;
go
drop database TOURDULICH;
go
create database TOURDULICH;
go
use TOURDULICH;
go

create table Account(
	TaiKhoan nvarchar(30) primary key,
	MatKhau nvarchar(30),
	Status int default 1
);

create table LoaiHinhTour(
	MaLoai nvarchar(20) primary key,
	TenLoai nvarchar(50),
    Status int default 1
);

create table Tour(
	MaTour nvarchar(20) primary key,
	MaLoai nvarchar(20) not null,
	TenTour nvarchar(50),
	DacDiem nvarchar(100),
    Status int default 1
);

create table GiaTour(
	MaGia nvarchar(20) primary key,
	MaTour nvarchar(20) not null,
	ThanhTien nvarchar(20),
	TgBatDau date,
	TgKetThuc date,
	HienHanh int,
    Status int default 1
);

create table DiaDiemThamQuan(
	MaTour nvarchar(20) not null,
	MaDiaDiem nvarchar(20) not null,
	ThuTu int not null,
    Status int default 1,
	constraint Pk_DiaDiemThamQuan primary key (MaTour, MaDiaDiem, ThuTu)
);

create table DiaDiem(
	MaDiaDiem nvarchar(20) primary key,
	TenDiaDiem nvarchar(50),
    Status int default 1
);

create table DoanDuLich(
	MaDoan nvarchar(20) primary key,
	MaTour nvarchar(20) not null,
	TenDoan nvarchar(50),
	GiaTour nvarchar(20),
	NgayKhoiHanh date,
	NgayKetThuc date,
	ChiTietNoiDung nvarchar(100),
    Status int default 1
);

create table ChiTietDoan(
	MaDoan nvarchar(20) not null,
	MaKhachHang nvarchar(20) not null,
    Status int default 1,
	constraint Pk_ChiTietDoan primary key (MaDoan, MaKhachHang)
);

create table KhachHang(
	MaKhachHang nvarchar(20) primary key,
	TenKhachHang nvarchar(50),
	GioiTinh nvarchar(10),
	NgaySinh date,
	Cmnd nvarchar(20),
	Sdt nvarchar(20),
	Mail nvarchar(30),
	DiaChi nvarchar(50),
	QuocTich nvarchar(30),
	Status int default 1
);

create table ChiPhi(
	MaChiPhi nvarchar(20) primary key,
	MaDoan nvarchar(20) not null,
	MaLoaiChiPhi nvarchar(20) not null,
	SoTien nvarchar(20),
	GhiChu nvarchar(100),
    Status int default 1
);

create table LoaiChiPhi(
	MaLoaiChiPhi nvarchar(20) primary key,
	TenLoai nvarchar(50),
    Status int default 1
);

create table NhiemVuNhanVien(
	MaNhanVien nvarchar(20) not null,
	MaDoan nvarchar(20) not null,
	TenNhiemVu nvarchar(50),
    Status int default 1,
	constraint Pk_NhiemVuNhanVien primary key (MaNhanVien, MaDoan)
);

create table NhanVien(
	MaNhanVien nvarchar(20) primary key,
	TenNhanVien nvarchar(50),
	GioiTinh nvarchar(10),
	NgaySinh date,
	Sdt nvarchar(20),
	DiaChi nvarchar(50),
    Status int default 1
);

create table MaDuLieuCuoi(
	MaLoai nvarchar(20), 
	MaTour nvarchar(20), 
	MaGia nvarchar(20), 
	MaDiaDiem nvarchar(20), 
	MaDoan nvarchar(20),
	MaKhachHang nvarchar(20), 
	MaChiPhi nvarchar(20), 
	MaLoaiChiPhi nvarchar(20), 
	MaNhanVien nvarchar(20)
);
go

alter table NhiemVuNhanVien add constraint Fk_NhiemVuNhanVien_NhanVien
foreign key (MaNhanVien) references NhanVien(MaNhanVien);
alter table NhiemVuNhanVien add constraint Fk_NhiemVuNhanVien_DoanDuLich
foreign key (MaDoan) references DoanDuLich(MaDoan);

alter table ChiPhi add constraint Fk_ChiPhi_DoanDuLich
foreign key (MaDoan) references DoanDuLich(MaDoan);
alter table ChiPhi add constraint Fk_ChiPhi_LoaiChiPhi
foreign key (MaLoaiChiPhi) references LoaiChiPhi(MaLoaiChiPhi);

alter table ChiTietDoan add constraint Fk_ChiTietDoan_DoanDuLich
foreign key (MaDoan) references DoanDuLich(MaDoan);
alter table ChiTietDoan add constraint Fk_ChiTietDoan_KhachHang
foreign key (MaKhachHang) references KhachHang(MaKhachHang);

alter table DoanDuLich add constraint Fk_DoanDuLich_Tour
foreign key (MaTour) references Tour(MaTour);

alter table DiaDiemThamQuan add constraint Fk_DiaDiemThamQuan_Tour
foreign key (MaTour) references Tour(MaTour);
alter table DiaDiemThamQuan add constraint Fk_DiaDiemThamQuan_DiaDiem
foreign key (MaDiaDiem) references DiaDiem(MaDiaDiem);

alter table GiaTour add constraint Fk_GiaTour_Tour
foreign key (MaTour) references Tour(MaTour);

alter table Tour add constraint Fk_Tour_LoaiHinhTour
foreign key (MaLoai) references LoaiHinhTour(MaLoai);

insert into LoaiHinhTour
values ('LH000001', 'Du lich', 1),
('LH000002', 'Tham quan', 1);

insert into Tour
values ('TR000001', 'LH000001', 'Nha Trang - Da Nang', 'Bai bien dep', 1),
('TR000002', 'LH000002', 'Hue - Vinh Ha Long', 'Canh quan dep', 1),
('TR000003', 'LH000001', 'Ha Tien - Phu Quoc', 'Khu nghi duong', 1);

insert into DiaDiem
values ('DD000001', 'Sai Gon', 1),
('DD000002', 'Nha Trang', 1),
('DD000003', 'Phu Quoc', 1),
('DD000004', 'Vinh Ha Long', 1),
('DD000005', 'Da Nang', 1),
('DD000006', 'Hue', 1),
('DD000007', 'Ha Tien', 1);

insert into DiaDiemThamQuan
values ('TR000001', 'DD000002', 1, 1),
('TR000001', 'DD000005', 2, 1),
('TR000002', 'DD000006', 1, 1),
('TR000002', 'DD000004', 2, 1),
('TR000003', 'DD000007', 1, 1),
('TR000003', 'DD000003', 2, 1);

insert into GiaTour
values ('GT000001', 'TR000001', '2500', '2021-09-01', '2021-12-31', 1, 1),
('GT000002', 'TR000002', '2000', '2022-01-01', '2022-04-30', 1, 1),
('GT000003', 'TR000003', '2200', '2022-05-01', '2021-08-31', 1, 1);

insert into DoanDuLich
values ('DL000001', 'TR000001', 'Tour 1 Doan 1', '2500', '2021-10-13', '2021-10-27', 'Vui ve than thien',1),
('DL000002', 'TR000001', 'Tour 1 Doan 2', '2500', '2021-11-14', '2021-11-28', 'Vui ve than thien',1),
('DL000003', 'TR000002', 'Tour 2 Doan 1', '2000', '2021-01-13', '2021-01-14', 'Vui ve than thien',1);

insert into NhanVien
values ('NV000001','Nguyen A','1','1972-11-17','0909090909','123/ABC',1),
('NV000002','Nguyen B','1','1980-07-20','0909090909','456/DEF',1),
('NV000003','Nguyen C','0','1974-12-04','0909090909','789/GHI',1),
('NV000004','Nguyen D','1','1976-02-21','0909090909','123/JKL',1),
('NV000005','Nguyen E','0','1969-06-09','0909090909','456/MNO',1);

insert into NhiemVuNhanVien
values ('NV000001','DL000001','Huong dan vien',1),
('NV000002','DL000001','Tai xe',1),
('NV000003','DL000001','Huong dan vien',1),
('NV000004','DL000002','Huong dan vien',1),
('NV000005','DL000002','Huong dan vien',1),
('NV000001','DL000002','Tai xe',1),
('NV000002','DL000003','Huong dan vien',1),
('NV000003','DL000003','Tai xe',1),
('NV000004','DL000003','Huong dan vien',1),
('NV000005','DL000003','Huong dan vien',1);

insert into KhachHang
values ('KH000001','Tran A','0','1972-11-17','123456789','0909090909','A@gmail.com','123/ABC','Viet Nam',1),
('KH000002','Tran B','0','1976-09-14','789456123','0909070706','B@gmail.com','147/DKE','Dai Loan',1),
('KH000003','Tran C','1','1984-04-01','456123789','0909050709','C@gmail.com','268/PMC','Nhat Ban',1),
('KH000004','Tran D','0','1993-12-24','123789456','0909020304','D@gmail.com','349/RUQK','Thai Lan',1),
('KH000005','Tran E','1','1988-01-07','789123456','0909010608','E@gmail.com','674/APLE','Trung Quoc',1);

insert into ChiTietDoan
values ('DL000001','KH000002',1),
('DL000003','KH000004',1),
('DL000002','KH000001',1),
('DL000002','KH000003',1),
('DL000001','KH000005',1);

insert into LoaiChiPhi
values ('LP000001','An uong',1),
('LP000002','Di lai',1),
('LP000003','Khach san',1),
('LP000004','Hoat dong',1),
('LP000005','Qua luu niem',1);

insert into ChiPhi
values ('CP000001','DL000001','LP000001','400','chi phi an uong',1),
('CP000002','DL000001','LP000002','600','chi phi phuong tien di chuyen',1),
('CP000003','DL000001','LP000003','800','chi phi khach san',1),
('CP000004','DL000002','LP000001','300','chi phi an uong',1),
('CP000005','DL000002','LP000002','500','chi phi di lai',1),
('CP000006','DL000002','LP000003','600','chi phi khach san',1),
('CP000007','DL000002','LP000004','300','chi phi hoat dong',1),
('CP000008','DL000002','LP000005','300','chi phi qua luu niem',1),
('CP000009','DL000003','LP000001','300','chi phi an uong',1),
('CP000010','DL000003','LP000002','400','chi phi di lai',1),
('CP000011','DL000003','LP000003','600','chi phi khach san',1),
('CP000012','DL000003','LP000005','200','chi phi qua luu niem',1);

insert into Account
values ('admin','admin',1);

insert into MaDuLieuCuoi
values ('LH000002','TR000003','GT000003','DD000007','DL000003','KH000005','CP000012','LP000005','NV000005');