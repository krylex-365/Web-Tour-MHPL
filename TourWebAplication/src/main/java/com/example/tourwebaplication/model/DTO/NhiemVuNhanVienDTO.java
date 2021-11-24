/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DTO;

public class NhiemVuNhanVienDTO {

    private String MaNhanVien;
    private String MaDoan;
    private String TenNhiemVu;

    public NhiemVuNhanVienDTO() {
    }

    public NhiemVuNhanVienDTO(String MaNhanVien, String MaDoan, String TenNhiemVu) {
        this.MaNhanVien = MaNhanVien;
        this.MaDoan = MaDoan;
        this.TenNhiemVu = TenNhiemVu;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getMaDoan() {
        return MaDoan;
    }

    public void setMaDoan(String MaDoan) {
        this.MaDoan = MaDoan;
    }

    public String getTenNhiemVu() {
        return TenNhiemVu;
    }

    public void setTenNhiemVu(String TenNhiemVu) {
        this.TenNhiemVu = TenNhiemVu;
    }

    @Override
    public String toString() {
        return "NhiemVuNhanVienDTO{" + "MaNhanVien=" + MaNhanVien + ", MaDoan=" + MaDoan + ", TenNhiemVu=" + TenNhiemVu + '}';
    }
}
