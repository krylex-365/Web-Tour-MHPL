/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DTO;

public class LoaiHinhTourDTO {
    private String MaLoai;
    private String TenLoai;

    public LoaiHinhTourDTO() {
    }

    public LoaiHinhTourDTO(String MaLoai, String TenLoai) {
        this.MaLoai = MaLoai;
        this.TenLoai = TenLoai;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String MaLoai) {
        this.MaLoai = MaLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }
    
    @Override
    public String toString() {
        return "LoaiHinhTourDTO{" + "MaLoai=" + MaLoai + ", TenLoai=" + TenLoai + '}';
    }
}
