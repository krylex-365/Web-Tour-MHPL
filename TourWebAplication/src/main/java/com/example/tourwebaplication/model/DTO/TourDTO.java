/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DTO;

public class TourDTO {

    private String MaTour;
    private String MaLoai;
    private String TenTour;
    private String DacDiem;

    public TourDTO() {
    }

    public TourDTO(String MaTour, String MaLoai, String TenTour, String DacDiem) {
        this.MaTour = MaTour;
        this.MaLoai = MaLoai;
        this.TenTour = TenTour;
        this.DacDiem = DacDiem;
    }

    public String getMaTour() {
        return MaTour;
    }

    public void setMaTour(String MaTour) {
        this.MaTour = MaTour;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String MaLoai) {
        this.MaLoai = MaLoai;
    }

    public String getTenTour() {
        return TenTour;
    }

    public void setTenTour(String TenTour) {
        this.TenTour = TenTour;
    }

    public String getDacDiem() {
        return DacDiem;
    }

    public void setDacDiem(String DacDiem) {
        this.DacDiem = DacDiem;
    }

    @Override
    public String toString() {
        return "TourDTO{" + "MaTour=" + MaTour + ", MaLoai=" + MaLoai + ", tenTour=" + TenTour + ", dacDiem=" + DacDiem + '}';
    }
}
