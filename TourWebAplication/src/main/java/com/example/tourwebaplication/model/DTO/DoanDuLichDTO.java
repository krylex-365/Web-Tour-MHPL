/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DTO;

public class DoanDuLichDTO {

    private String MaDoan;
    private String MaTour;
    private String TenDoan;
    private String GiaTour;
    private String NgayKhoiHanh;
    private String NgayKetThuc;
    private String ChiTietNoiDung;

    public DoanDuLichDTO() {
    }

    public DoanDuLichDTO(String MaDoan, String MaTour, String TenDoan, String GiaTout, String NgayKhoiHanh, String NgayKetThuc, String ChiTietNoiDung) {
        this.MaDoan = MaDoan;
        this.MaTour = MaTour;
        this.TenDoan = TenDoan;
        this.GiaTour = GiaTout;
        this.NgayKhoiHanh = NgayKhoiHanh;
        this.NgayKetThuc = NgayKetThuc;
        this.ChiTietNoiDung = ChiTietNoiDung;
    }

    public String getMaDoan() {
        return MaDoan;
    }

    public void setMaDoan(String MaDoan) {
        this.MaDoan = MaDoan;
    }

    public String getMaTour() {
        return MaTour;
    }

    public void setMaTour(String MaTour) {
        this.MaTour = MaTour;
    }

    public String getTenDoan() {
        return TenDoan;
    }

    public void setTenDoan(String TenDoan) {
        this.TenDoan = TenDoan;
    }

    public String getGiaTour() {
        return GiaTour;
    }

    public void setGiaTour(String GiaTour) {
        this.GiaTour = GiaTour;
    }

    public String getNgayKhoiHanh() {
        return NgayKhoiHanh;
    }

    public void setNgayKhoiHanh(String NgayKhoiHanh) {
        this.NgayKhoiHanh = NgayKhoiHanh;
    }

    public String getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(String NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    public String getChiTietNoiDung() {
        return ChiTietNoiDung;
    }

    public void setChiTietNoiDung(String ChiTietNoiDung) {
        this.ChiTietNoiDung = ChiTietNoiDung;
    }

    @Override
    public String toString() {
        return "DoanDuLichDTO{" + "MaDoan=" + MaDoan + ", MaTour=" + MaTour + ", TenDoan=" + TenDoan + ", GiaTour=" + GiaTour + ", NgayKhoiHanh=" + NgayKhoiHanh + ", NgayKetThuc=" + NgayKetThuc + ", ChiTietNoiDung=" + ChiTietNoiDung + '}';
    }
}
