/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DTO;

public class ChiTietDoanDTO {

    private String MaDoan;
    private String MaKhachHang;

    public ChiTietDoanDTO() {
    }

    public ChiTietDoanDTO(String MaDoan, String MaKhachHang) {
        this.MaDoan = MaDoan;
        this.MaKhachHang = MaKhachHang;
    }

    public String getMaDoan() {
        return MaDoan;
    }

    public void setMaDoan(String MaDoan) {
        this.MaDoan = MaDoan;
    }

    public String getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(String MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }

    @Override
    public String toString() {
        return "ChiTietDoanDTO{" + "MaDoan=" + MaDoan + ", MaKhachHang=" + MaKhachHang + '}';
    }
}
