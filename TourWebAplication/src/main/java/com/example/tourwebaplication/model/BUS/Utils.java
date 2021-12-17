package com.example.tourwebaplication.model.BUS;

import com.example.tourwebaplication.model.DAO.MaDuLieuCuoiDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class Utils {

    public MaDuLieuCuoiDAO maDLCuoi = new MaDuLieuCuoiDAO();

    public Utils() {
        maDLCuoi = new MaDuLieuCuoiDAO();
    }

    ////////////////Tạo mã Tour
    public String initMaTour(String init) {
        maDLCuoi = new MaDuLieuCuoiDAO();
        System.out.println("- In initMatour");
        String temp = maDLCuoi.getMaTourLast();
        System.out.println(temp);
        String add = temp.substring(2, temp.length());
        int maTour = Integer.parseInt(add);
        maTour++;
        int totalzero = 6;
        add = String.valueOf(maTour);
        int cpzero = totalzero - add.length();
        init = "TR";
        for (int i = 0; i < cpzero; i++) {
            init += '0';
        }
        init += add;
        return init;
    }

    ////////////////Tạo mã giá
    public String initMaGia(String init) {
        String temp = maDLCuoi.getMaGiaLast();
        System.out.println(temp);
        String add = temp.substring(2, temp.length());
        int maGia = Integer.parseInt(add);
        maGia++;
        int totalzero = 6;
        add = String.valueOf(maGia);
        int cpzero = totalzero - add.length();
        init = "GT";
        for (int i = 0; i < cpzero; i++) {
            init += '0';
        }
        init += add;
        return init;
    }
    
    ////////////////Tạo mã loại hình
    public String initMaLoai() {
        String temp = maDLCuoi.getMaLoaiLast();
        System.out.println(temp);
        String add = temp.substring(2, temp.length());
        int maLoai = Integer.parseInt(add);
        maLoai++;
        int totalzero = 6;
        add = String.valueOf(maLoai);
        int cpzero = totalzero - add.length();
        String init = "LH";
        for (int i = 0; i < cpzero; i++) {
            init += '0';
        }
        init += add;
        return init;
    }

    ////////////////Tạo địa điểm
    public String initMaDiaDiem() {
        maDLCuoi = new MaDuLieuCuoiDAO();
        System.out.println("- In initDiaDiem");
        String temp = maDLCuoi.getMaDiaDiemLast();
        System.out.println(temp);
        String add = temp.substring(2, temp.length());
        int maDiaDiem = Integer.parseInt(add);
        maDiaDiem++;
        int totalzero = 6;
        add = String.valueOf(maDiaDiem);
        int cpzero = totalzero - add.length();
        String init = "DD";
        for (int i = 0; i < cpzero; i++) {
            init += '0';
        }
        init += add;
        return init;
    }
    
    ////////////////Tạo mã KhachHang
    public String initMaKhachHang() {
        String result = "KH";
        maDLCuoi = new MaDuLieuCuoiDAO();
        System.out.println("- In initMakhachhang");
        String temp = maDLCuoi.getMaKhachHangLast();
        System.out.println(temp);
        String add = temp.substring(2, temp.length());
        int maKhachHang = Integer.parseInt(add);
        maKhachHang++;
        int totalzero = 6;
        add = String.valueOf(maKhachHang);
        int cpzero = totalzero - add.length();
        for (int i = 0; i < cpzero; i++) {
            result += '0';
        }
        result += add;
        return result;
    }
    
    ////////////////Tạo mã nhân viên
    public String initMaNhanVien() {
        String result = "NV";
        maDLCuoi = new MaDuLieuCuoiDAO();
        System.out.println("- In initManhanvien");
        String temp = maDLCuoi.getMaNhanVienLast();
        System.out.println(temp);
        String add = temp.substring(2, temp.length());
        int maNhanVien = Integer.parseInt(add);
        maNhanVien++;
        int totalzero = 6;
        add = String.valueOf(maNhanVien);
        int cpzero = totalzero - add.length();
        for (int i = 0; i < cpzero; i++) {
            result += '0';
        }
        result += add;
        return result;
    }
    
    public String initMaDoanDuLich() {
        String result = "DL";
        maDLCuoi = new MaDuLieuCuoiDAO();
        System.out.println("- In initMaDoanDuLich");
        String temp = maDLCuoi.getMaDoanLast();
        System.out.println(temp);
        String add = temp.substring(2, temp.length());
        int maDoanDuLich = Integer.parseInt(add);
        maDoanDuLich++;
        int totalzero = 6;
        add = String.valueOf(maDoanDuLich);
        int cpzero = totalzero - add.length();
        for (int i = 0; i < cpzero; i++) {
            result += '0';
        }
        result += add;
        return result;
    }
    
     ////////////////Tạo Loai Chi Phí
    public String initMaLoaiChiPhi() {
        maDLCuoi = new MaDuLieuCuoiDAO();
        System.out.println("- In initLoaiChiPhi");
        String temp = maDLCuoi.getMaLoaiChiPhiLast();
        System.out.println(temp);
        String add = temp.substring(2, temp.length());
        int maLoaiChiPhi = Integer.parseInt(add);
        maLoaiChiPhi++;
        int totalzero = 6;
        add = String.valueOf(maLoaiChiPhi);
        int cpzero = totalzero - add.length();
        String init = "LP";
        for (int i = 0; i < cpzero; i++) {
            init += '0';
        }
        init += add;
        return init;
    }
    
     ////////////////Tạo Chi Phí
    public String initMaChiPhi() {
        maDLCuoi = new MaDuLieuCuoiDAO();
        System.out.println("- In initChiPhi");
        String temp = maDLCuoi.getMaChiPhiLast();
        System.out.println(temp);
        String add = temp.substring(2, temp.length());
        int maChiPhi = Integer.parseInt(add);
        maChiPhi++;
        int totalzero = 6;
        add = String.valueOf(maChiPhi);
        int cpzero = totalzero - add.length();
        String init = "CP";
        for (int i = 0; i < cpzero; i++) {
            init += '0';
        }
        init += add;
        return init;
    }

    ////////////////Lấy ngày hiện tại
    public String initDateNow() {
        final Date currentTime = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        System.out.println("GMT time: " + sdf.format(currentTime));
        String date = sdf.format(currentTime).toString();
        return date;
    }

    ////////////////Lấy ngày đầu tiên của tháng kế tiếp
    public String firstDayOfnextMonth(String ngay) {
        String thanglamviec = getMonth(ngay);
        String namlamviec = getYear(ngay);
        int temp1 = Integer.parseInt(thanglamviec);
        temp1++;
        thanglamviec = String.valueOf(temp1);
        if (thanglamviec.length() == 1) {
            thanglamviec = "0" + thanglamviec;
        }
        if (thanglamviec.equals("13")) {
            int temp2 = Integer.parseInt(namlamviec);
            temp2++;
            namlamviec = String.valueOf(temp2);
            thanglamviec = "01";
        }
        String thoigian = namlamviec + "-" + thanglamviec + "-01";
        return thoigian;
    }

    /////////////////Tính số ngày 
    public int totalDays(String thoigian) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String hientai = initDateNow();
        System.out.println("thoigian: " + thoigian + "\nhientai: " + hientai);
        int total = -1;
        try {
            Date date1 = myFormat.parse(thoigian);
            Date date2 = myFormat.parse(hientai);
            long diff = date2.getTime() - date1.getTime();
            System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            total = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return total;
    }

    /////////////////Tính số ngày với 2 tham số truyền vào
    public int totalDays2(String thoigian, String d) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String hientai = d;
        System.out.println("thoigian: " + thoigian + "\nhientai: " + hientai);
        int total = -1;
        try {
            Date date1 = myFormat.parse(thoigian);
            Date date2 = myFormat.parse(hientai);
            long diff = date2.getTime() - date1.getTime();
            System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            total = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return total;
    }

    ////////////////Tách năm
    public static String getYear(String name) {
        int pos = name.indexOf("-");
        //System.out.println (pos);
        if (pos == -1) {
            return null;
        }
        if (pos == name.length() - 1) {
            return null;
        }
        return name.substring(0, pos);
    }

    ////////////////Tách tháng
    public static String getMonth(String name) {
        int pos = name.indexOf("-");
        //System.out.println (pos);
        int pos1 = name.indexOf("-", pos + 1);
        //System.out.println (pos1);
        if (pos == -1) {
            return null;
        }
        if (pos == name.length() - 1) {
            return null;
        }
        return name.substring(pos + 1, pos1);
    }

    ////////////////Tách ngày
    public static String getDay(String name) {
        int pos = name.indexOf("-");
        //System.out.println (pos);
        int pos1 = name.indexOf("-", pos + 1);
        //System.out.println (pos1);
        if (pos == -1) {
            return null;
        }
        if (pos == name.length() - 1) {
            return null;
        }
        return name.substring(pos1 + 1, name.length());
    }

    ////////////////Thêm 0 vào ngày có length = 1
    public String them0(String num) {
        if (num.length() >= 2) {
            return num;
        } else {
            num = "0" + num;
            return num;
        }
    }

    ////////////////String to date
    public Date stringToDate(String strDate){
        try{
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = myFormat.parse(strDate);
            return date;
        }catch(ParseException e){
            return new Date(0, 0, 0);
        }
    }

    ////////////////Tháng -> ngày cuối cùng của tháng
    public int lastDayOfMonth(String date) {
        int monthcheck = Integer.parseInt(getMonth(date));
        int year = Integer.parseInt(getYear(date));
        switch (monthcheck) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 30;
        }
    }

}

class main {

    public static void main(String[] args) {
        Utils ut = new Utils();
        System.out.println(ut.totalDays("2020-06-02"));

        DiaDiemBUS diaDiemBUS = new DiaDiemBUS();
//        String maDD = diaDiemBUS.CapPhat(new MaDuLieuCuoiDAO().getMaDiaDiemLast());
//        diaDiemBUS.themDiaDiem(maDD, "dsfsdgfds");
        diaDiemBUS.xoaDiaDiem("DD000002");
    }
}
