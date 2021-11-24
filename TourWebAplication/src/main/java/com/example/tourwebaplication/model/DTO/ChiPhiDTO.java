/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DTO;


public class ChiPhiDTO {
   private String MaChiPhi;
   private String MaDoan;
   private String MaLoaiChiPhi;
   private String SoTien;
   private String GhiChu;

    public ChiPhiDTO() {
    }

    public ChiPhiDTO(String MaChiPhi, String MaDoan, String MaLoaiChiPhi, String SoTien, String GhiChu) {
        this.MaChiPhi = MaChiPhi;
        this.MaDoan = MaDoan;
        this.MaLoaiChiPhi = MaLoaiChiPhi;
        this.SoTien = SoTien;
        this.GhiChu = GhiChu;
    }

    public String getMaChiPhi() {
        return MaChiPhi;
    }

    public void setMaChiPhi(String MaChiPhi) {
        this.MaChiPhi = MaChiPhi;
    }

    public String getMaDoan() {
        return MaDoan;
    }

    public void setMaDoan(String MaDoan) {
        this.MaDoan = MaDoan;
    }

    public String getMaLoaiChiPhi() {
        return MaLoaiChiPhi;
    }

    public void setMaLoaiChiPhi(String MaLoaiChiPhi) {
        this.MaLoaiChiPhi = MaLoaiChiPhi;
    }

    public String getSoTien() {
        return SoTien;
    }

    public void setSoTien(String SoTien) {
        this.SoTien = SoTien;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
   
   @Override
    public String toString() {
        return "ChiPhiDTO{" + "MaChiPhi=" + MaChiPhi + ", MaDoan=" + MaDoan + ", MaLoaiChiPhi=" + MaLoaiChiPhi + ", SoTien=" + SoTien + ", GhiChu=" + GhiChu + '}';
    }
}
