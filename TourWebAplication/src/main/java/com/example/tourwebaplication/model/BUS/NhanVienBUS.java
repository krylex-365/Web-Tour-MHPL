/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.tourwebaplication.model.BUS;

import com.example.tourwebaplication.model.DAO.MaDuLieuCuoiDAO;
import com.example.tourwebaplication.model.DAO.NhanVienDAO;
import com.example.tourwebaplication.model.DTO.NhanVienDTO;

import java.util.ArrayList;

/**
 *
 * @author minhk
 */
public class NhanVienBUS {
    private NhanVienDAO nhanVienDAO;
    private ArrayList<NhanVienDTO> nhanVienDTOs;
    private MaDuLieuCuoiDAO maLast;
    
    public NhanVienBUS(){
        nhanVienDAO = new NhanVienDAO();
        maLast = new MaDuLieuCuoiDAO();
        nhanVienDTOs = nhanVienDAO.getList();
    }

    public ArrayList<NhanVienDTO> getNhanVienDTOs() {
        return nhanVienDTOs;
    }
    
    
    public boolean add(NhanVienDTO nhanVien){
        for(NhanVienDTO a  : nhanVienDTOs)
            if(a.getMaNhanVien().equals(nhanVien.getMaNhanVien()))return false;
        System.out.println(nhanVien);
        if(nhanVienDAO.add(nhanVien)){
            // NHỚ SỬA LẠI
            maLast.updateMaNhanVien(nhanVien.getMaNhanVien());
            nhanVienDTOs.add(nhanVien); 
            return true;
        }
        return false;
    }
    
    public boolean delete(String maNhanVien){
        
        if(nhanVienDAO.delete(maNhanVien)){
            for(int i = 0; i < nhanVienDTOs.size();i++)
            if(nhanVienDTOs.get(i).getMaNhanVien().equals(maNhanVien))nhanVienDTOs.remove(i);
            return true;
        }
        return false;
    }
    
    public boolean update(NhanVienDTO nhanVien){
        if(nhanVienDAO.update(nhanVien)){
            for(NhanVienDTO a: nhanVienDTOs){
                if(a.getMaNhanVien().equals(nhanVien.getMaNhanVien())){
                    //a.setMaKhachHang(maKhachHang);
                    a.setTenNhanVien(nhanVien.getTenNhanVien());
                    a.setDiaChi(nhanVien.getDiaChi());
                    a.setGioiTinh(nhanVien.getGioiTinh());
                    a.setSDT(nhanVien.getSDT());
                    a.setNgaySinh(nhanVien.getNgaySinh());
                }                        
            }
            return true;
        }
        return false;
    }
    
    public ArrayList<NhanVienDTO> searchNhanVienByMaNhanVien(String maNhanVien){
        ArrayList<NhanVienDTO> result = new ArrayList<>();
         for(NhanVienDTO a : nhanVienDTOs){
            if(a.getMaNhanVien().equals(maNhanVien))result.add(a);
        }
         return result;
    }
}
