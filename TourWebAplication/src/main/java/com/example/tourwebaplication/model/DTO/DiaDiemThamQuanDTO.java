/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DTO;


public class DiaDiemThamQuanDTO {
    private String MaTour;
    private String MaDiaDiem;
    private int ThuTu;

    public DiaDiemThamQuanDTO() {
    }

    public DiaDiemThamQuanDTO(String MaTour, String MaDiaDiem, int ThuTu) {
        this.MaTour = MaTour;
        this.MaDiaDiem = MaDiaDiem;
        this.ThuTu = ThuTu;
    }

    public String getMaTour() {
        return MaTour;
    }

    public void setMaTour(String MaTour) {
        this.MaTour = MaTour;
    }

    public String getMaDiaDiem() {
        return MaDiaDiem;
    }

    public void setMaDiaDiem(String MaDiaDiem) {
        this.MaDiaDiem = MaDiaDiem;
    }

    public int getThuTu() {
        return ThuTu;
    }

    public void setThuTu(int ThuTu) {
        this.ThuTu = ThuTu;
    }
    
    @Override
    public String toString() {
        return "DiaDiemThamQuanDTO{" + "MaTour=" + MaTour + ", MaDiaDiem=" + MaDiaDiem + ", ThuTu=" + ThuTu + '}';
    }
}
