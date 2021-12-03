/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.tourwebaplication.model.BUS;

import com.example.tourwebaplication.model.DAO.NhiemVuNhanVienDAO;
import com.example.tourwebaplication.model.DTO.NhiemVuNhanVienDTO;

import java.util.ArrayList;

/**
 *
 * @author minhk
 */
public class NhiemVuNhanVienBUS {
    public NhiemVuNhanVienDAO nhiemVuNhanVienDAO;
    
    public NhiemVuNhanVienBUS(){
        nhiemVuNhanVienDAO = new NhiemVuNhanVienDAO();
    }
    
    public boolean add(String maNhanVien, String maDoan, String nhiemVuNhanVien, ArrayList<NhiemVuNhanVienDTO> nhiemVuNhanVienDTOs){
        for(NhiemVuNhanVienDTO a : nhiemVuNhanVienDTOs){
            if(a.getMaDoan().equals(maDoan)&&a.getMaNhanVien().equals(maNhanVien)){
                return false;
            }
        }
        if(nhiemVuNhanVienDAO.Add(maNhanVien, maDoan, nhiemVuNhanVien)){
            nhiemVuNhanVienDTOs.add(new NhiemVuNhanVienDTO(maNhanVien,maDoan,nhiemVuNhanVien));
            return true;
        }
        return false;
    }
    
    public boolean delete(String maNhanVien, String maDoan,ArrayList<NhiemVuNhanVienDTO> nhiemVuNhanVienDTOs){
        if(nhiemVuNhanVienDAO.delete(maNhanVien, maDoan)){
            for(int i = 0; i < nhiemVuNhanVienDTOs.size();i++){
                if(nhiemVuNhanVienDTOs.get(i).getMaDoan().equals(maDoan)&&nhiemVuNhanVienDTOs.get(i).getMaNhanVien().equals(maNhanVien)){
                    nhiemVuNhanVienDTOs.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean update(String maNhanVien, String maDoan, String nhiemVuNhanVien,ArrayList<NhiemVuNhanVienDTO> nhiemVuNhanVienDTOs){
        if(nhiemVuNhanVienDAO.update(maNhanVien, maDoan, nhiemVuNhanVien)){
            for(NhiemVuNhanVienDTO a : nhiemVuNhanVienDTOs){
                if(a.getMaDoan().equals(maDoan)&&a.getMaNhanVien().equals(maNhanVien)){
                    a.setTenNhiemVu(nhiemVuNhanVien);
                    return true;
                }
            }
        }
        return false;
    }
}
