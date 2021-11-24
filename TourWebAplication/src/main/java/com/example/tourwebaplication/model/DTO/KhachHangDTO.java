/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DTO;

public class KhachHangDTO {

    private String MaKhachHang;
    private String TenKhachHang;
    private String GioiTinh;
    private String NgaySinh;
    private String CMND;
    private String SDT;
    private String Mail;
    private String DiaChi;
    private String QuocTich;

    public KhachHangDTO() {
    }

    public KhachHangDTO(String MaKhachHang, String TenKhachHang, String GioiTinh, String NgaySinh, String CMND, String SDT, String Mail, String DiaChi, String QuocTich) {
        this.MaKhachHang = MaKhachHang;
        this.TenKhachHang = TenKhachHang;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.CMND = CMND;
        this.SDT = SDT;
        this.Mail = Mail;
        this.DiaChi = DiaChi;
        this.QuocTich = QuocTich;
    }

    public String getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(String MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String TenKhachHang) {
        this.TenKhachHang = TenKhachHang;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String Mail) {
        this.Mail = Mail;
    }

    public String getQuocTich() {
        return QuocTich;
    }

    public void setQuocTich(String QuocTich) {
        this.QuocTich = QuocTich;
    }

    @Override
    public String toString() {
        return "KhachHangDTO{" + "MaKhachHang=" + MaKhachHang + ", TenKhachHang=" + TenKhachHang + ", CMND=" + CMND + ", DiaChi=" + DiaChi + ", GioiTinh=" + GioiTinh + ", SDT=" + SDT + ", Mail=" + Mail + ", QuocTich=" + QuocTich + '}';
    }
}
