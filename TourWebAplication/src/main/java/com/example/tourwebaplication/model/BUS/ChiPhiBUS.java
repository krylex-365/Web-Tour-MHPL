/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.BUS;

import com.example.tourwebaplication.model.DAO.ChiPhiDAO;
import com.example.tourwebaplication.model.DAO.MaDuLieuCuoiDAO;
import com.example.tourwebaplication.model.DTO.ChiPhiDTO;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class ChiPhiBUS {
    private ChiPhiDAO chiPhiDAO;
    private Utils utl = new Utils();
    private MaDuLieuCuoiDAO maLast = new MaDuLieuCuoiDAO();

    public ChiPhiBUS() {
        chiPhiDAO = new ChiPhiDAO();
    }
    
    public ChiPhiBUS(ChiPhiDAO chiPhiDAO) {
        this.chiPhiDAO = chiPhiDAO;
    }
    
    private int indexChiPhi(ArrayList<ChiPhiDTO> chiPhiDTOs, String maChiPhi) {
        for (int i = 0; i < chiPhiDTOs.size(); i++) {
            if (maChiPhi.equals(chiPhiDTOs.get(i).getMaChiPhi())) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean themChiPhi(ChiPhiDTO chiPhiDTO/*, ArrayList<ChiPhiDTO> chiPhiDTOs*/){
        if (chiPhiDAO.insertChiPhi(chiPhiDTO)) {
            //chiPhiDTOs.add(chiPhiDTO);
            maLast.updateMaChiPhi(chiPhiDTO.getMaChiPhi());
            System.out.println("Them ChiPhiBUS thanh cong");
            return true;
        }
        System.out.println("Them ChiPhiBUS that bai");
        return false;
    }
    
    public boolean suaChiPhi(ChiPhiDTO chiPhiDTO/*, ArrayList<ChiPhiDTO> chiPhiDTOs*//*, String maLoaiCPHH*/){
        //int index = indexChiPhi(chiPhiDTOs, chiPhiDTO.getMaChiPhi());
//        if (index == -1) {
//            return false;
//        }
        /*boolean checkLoaiCP;
        if (maLoaiCPHH.equals(chiPhiDTO.getMaLoaiChiPhi())){
            // NẾU KHÔNG SỬA LOẠI CP
            checkLoaiCP = false;
        } else {
            // NẾU SỬA LOẠI CP
            checkLoaiCP = true;
        }*/
        if (chiPhiDAO.updateChiPhi(chiPhiDTO/*, checkLoaiCP*/)) {
            //chiPhiDTOs.set(index, chiPhiDTO);
            System.out.println("Sửa thành công ChiPhiBUS");
            return true;
        }
        System.out.println("Sửa thất bại ChiPhiBUS");
        return false;
    }
    
    public boolean xoaChiPhi(String maChiPhi){
        if (chiPhiDAO.deleteChiPhi(maChiPhi)) {
           // chiPhiDTOs.remove(chiPhiDTO);
            System.out.println("Xóa thành công ChiPhiBUS");
            return true;
        }
        System.out.println("Xóa thất bại ChiPhiBUS");
        return false;
    }

    public ArrayList<ChiPhiDTO> getList(){
        return chiPhiDAO.getList();
    }
}
