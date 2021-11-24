/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.BUS;

import com.example.tourwebaplication.model.DAO.LoaiHinhTourDAO;
import com.example.tourwebaplication.model.DAO.MaDuLieuCuoiDAO;
import com.example.tourwebaplication.model.DTO.LoaiHinhTourDTO;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class LoaiHinhTourBUS {

    private ArrayList<LoaiHinhTourDTO> loaiHinhTourDTOs;
    private LoaiHinhTourDAO loaiHinhTourDAO;
    private Utils utl = new Utils();
    private MaDuLieuCuoiDAO maLast = new MaDuLieuCuoiDAO();

    public LoaiHinhTourBUS() {
        this.loaiHinhTourDAO = new LoaiHinhTourDAO();
        this.loaiHinhTourDTOs = loaiHinhTourDAO.getList();
    }

    public ArrayList<LoaiHinhTourDTO> getLoaiHinhTourDTOs() {
        return loaiHinhTourDTOs;
    }

    public void setLoaiHinhTourDTOs(ArrayList<LoaiHinhTourDTO> loaiHinhTourDTOs) {
        this.loaiHinhTourDTOs = loaiHinhTourDTOs;
    }

//    public String CapPhat(String init) {
//        System.out.println("- cap 1");
//        init = utl.initMaLoai(init);
//        System.out.println("- cap 2");
//        return init;
//    }

    private int indexLoaiHinh(String maLoai) {
        for (int i = 0; i < loaiHinhTourDTOs.size(); i++) {
            if (maLoai.equals(loaiHinhTourDTOs.get(i).getMaLoai())) {
                return i;
            }
        }
        return -1;
    }

    public boolean themLoaiHinhTour(String maLoai, String tenLoai) {
        if (loaiHinhTourDAO.insertLoaiHinh(maLoai, tenLoai)) {
            LoaiHinhTourDTO newLoaiHinh = new LoaiHinhTourDTO(maLoai, tenLoai);
            loaiHinhTourDTOs.add(newLoaiHinh);
            maLast.updateMaLoai(maLoai);
            System.out.println("Thêm thành công themLoaiHinhTourBUS");
            return true;
        }
        System.out.println("Thêm thất bại themLoaiHinhTourBUS");
        return false;
    }

    public boolean suaLoaiHinhTour(String maLoai, String tenLoai) {
        int indexMaLH = indexLoaiHinh(maLoai);
        if (indexMaLH == -1) {
            return false;
        }
        LoaiHinhTourDTO loaihinhDTO = new LoaiHinhTourDTO(maLoai, tenLoai);
        if (loaiHinhTourDAO.updateLoaiHinh(loaihinhDTO)) {
            loaiHinhTourDTOs.set(indexMaLH, loaihinhDTO);
            System.out.println("Sửa thành công suaLoaiHinhTourBUS");
            return true;
        }
        System.out.println("Sửa thất bại suaLoaiHinhTourBUS");
        return false;
    }

    public boolean xoaLoaiHinhTour(String maLoai) {
        if (loaiHinhTourDAO.deleteLoaiHinh(maLoai)) {
            loaiHinhTourDTOs.remove(indexLoaiHinh(maLoai));
            System.out.println("Xóa thành công xoaLoaiHinhTourBUS");
            return true;
        }
        System.out.println("Xóa thất bại xoaLoaiHinhTourBUS");
        return false;
    }
    
    public ArrayList<LoaiHinhTourDTO> searchLoaiHinhByMaLH(String maLoaiHinh) {
        ArrayList<LoaiHinhTourDTO> result = new ArrayList<>();
        for (LoaiHinhTourDTO a : loaiHinhTourDTOs) {
            if (a.getMaLoai().equals(maLoaiHinh)) {
                result.add(a);
            }
        }
        return result;
    }
}
