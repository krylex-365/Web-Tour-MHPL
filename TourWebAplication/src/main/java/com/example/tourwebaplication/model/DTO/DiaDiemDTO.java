/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DTO;

public class DiaDiemDTO {

    private String MaDiaDiem;
    private String TenDiaDiem;

    public DiaDiemDTO() {
    }

    public DiaDiemDTO(String MaDiaDiem, String TenDiaDiem) {
        this.MaDiaDiem = MaDiaDiem;
        this.TenDiaDiem = TenDiaDiem;
    }

    public String getMaDiaDiem() {
        return MaDiaDiem;
    }

    public void setMaDiaDiem(String MaDiaDiem) {
        this.MaDiaDiem = MaDiaDiem;
    }

    public String getTenDiaDiem() {
        return TenDiaDiem;
    }

    public void setTenDiaDiem(String TenDiaDiem) {
        this.TenDiaDiem = TenDiaDiem;
    }

    @Override
    public String toString() {
        return "DiaDiemDTO{" + "MaDiaDiem=" + MaDiaDiem + ", TenDiaDiem=" + TenDiaDiem + '}';
    }
}
