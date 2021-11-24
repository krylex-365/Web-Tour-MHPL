/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DTO;

/**
 *
 * @author minhk
 */
public class GiaTourDTO {

    private String MaGia;
    private String MaTour;
    private String ThanhTien;
    private String TgBatDau;
    private String TgKetThuc;
    private int HienHanh;

    public GiaTourDTO() {
    }

    public GiaTourDTO(String MaGia, String MaTour, String ThanhTien, String TgBatDau, String TgKetThuc, int HienHanh) {
        this.MaGia = MaGia;
        this.MaTour = MaTour;
        this.ThanhTien = ThanhTien;
        this.TgBatDau = TgBatDau;
        this.TgKetThuc = TgKetThuc;
        this.HienHanh = HienHanh;
    }

    public String getMaGia() {
        return MaGia;
    }

    public void setMaGia(String MaGia) {
        this.MaGia = MaGia;
    }

    public String getMaTour() {
        return MaTour;
    }

    public void setMaTour(String MaTour) {
        this.MaTour = MaTour;
    }

    public String getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(String ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public String getTgBatDau() {
        return TgBatDau;
    }

    public void setTgBatDau(String TgBatDau) {
        this.TgBatDau = TgBatDau;
    }

    public String getTgKetThuc() {
        return TgKetThuc;
    }

    public void setTgKetThuc(String TgKetThuc) {
        this.TgKetThuc = TgKetThuc;
    }

    public int getHienHanh() {
        return HienHanh;
    }

    public void setHienHanh(int HienHanh) {
        this.HienHanh = HienHanh;
    }

    @Override
    public String toString() {
        return "GiaTourDTO{" + "maGia=" + MaGia + ", maTour=" + MaTour + ", thanhTien=" + ThanhTien + ", thoiGianBatDau=" + TgBatDau + ", thoiGianKetThuc=" + TgKetThuc + '}';
    }
}
