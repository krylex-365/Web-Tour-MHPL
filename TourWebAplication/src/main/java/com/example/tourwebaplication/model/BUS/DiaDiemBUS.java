/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.BUS;

import com.example.tourwebaplication.model.DAO.DiaDiemDAO;
import com.example.tourwebaplication.model.DAO.MaDuLieuCuoiDAO;
import com.example.tourwebaplication.model.DTO.DiaDiemDTO;
import com.example.tourwebaplication.model.DTO.DiaDiemThamQuanDTO;

import java.util.ArrayList;

/**
 *
 * @author minhk
 */
public class DiaDiemBUS {
    private DiaDiemDAO diaDiemDAO;
    private ArrayList<DiaDiemDTO> diaDiemDTOs;
    private Utils utl = new Utils();
    private MaDuLieuCuoiDAO maLast = new MaDuLieuCuoiDAO();

    public DiaDiemBUS(DiaDiemDAO diaDiemDAO, ArrayList<DiaDiemDTO> diaDiemDTOs) {
        this.diaDiemDAO = diaDiemDAO;
        this.diaDiemDTOs = diaDiemDTOs;
    }

    public DiaDiemBUS() {
        diaDiemDAO = new DiaDiemDAO();
        diaDiemDTOs = diaDiemDAO.getList();
    }

//    public String CapPhat(String init) {
//        System.out.println("- cap 1");
//        init = utl.initMaDiaDiem(init);
//        System.out.println("- cap 2");
//        return init;
//    }

    public DiaDiemDAO getDiaDiemDAO() {
        return diaDiemDAO;
    }

    public void setDiaDiemDAO(DiaDiemDAO diaDiemDAO) {
        this.diaDiemDAO = diaDiemDAO;
    }

    public ArrayList<DiaDiemDTO> getDiaDiemDTOs() {
        return diaDiemDTOs;
    }

    public void setDiaDiemDTOs(ArrayList<DiaDiemDTO> diaDiemDTOs) {
        this.diaDiemDTOs = diaDiemDTOs;
    }
    
    public String searchTenDiaDiemByMaDiaDiem(String maDiaDiem){
        String result="Lối !!";
        for(DiaDiemDTO a : diaDiemDTOs){
            if(maDiaDiem.equals(a.getMaDiaDiem()))result = a.getTenDiaDiem();
        }
        return result;
    }
    
    public ArrayList<DiaDiemDTO> searchListDiaDiemByMaDiaDiem(String maDiaDiem){
        ArrayList<DiaDiemDTO> result = new ArrayList<>();
        for(DiaDiemDTO a : diaDiemDTOs){
            if(maDiaDiem.equals(a.getMaDiaDiem()))result.add(a);
        }
        return result;
    }

    public boolean themDiaDiem(String maDiaDiem, String tenDiaDiem){
        DiaDiemDTO diaDiemDTO = new DiaDiemDTO(maDiaDiem, tenDiaDiem);
        if(diaDiemDAO.insertDiaDiem(diaDiemDTO)){
            diaDiemDTOs.add(diaDiemDTO);
            maLast.updateMaDiaDiem(maDiaDiem);
            System.out.println("Thêm thành công themDiaDiem");
            return true;
        }
        System.out.println("Thêm thất bại themDiaDiem");
        return false;
    }

    public boolean suaDiaDiem(String maDiaDiem, String tenDiaDiem){
        DiaDiemDTO diaDiemDTO = searchDiaDiemByMaDiaDiem(maDiaDiem);
        if(diaDiemDTO != null){
            if(diaDiemDAO.updateMaDiaDiem(maDiaDiem, tenDiaDiem)){
                diaDiemDTO.setTenDiaDiem(tenDiaDiem);
                System.out.println("Sửa thành công suaDiaDiem");
                return true;
            }
        }
        System.out.println("Sửa thất bại suaDiaDiem - Địa điểm chưa có");
        return false;
    }

    public boolean xoaDiaDiem(String maDiaDiem){
        DiaDiemDTO diaDiemDTO = searchDiaDiemByMaDiaDiem(maDiaDiem);
        DiaDiemThamQuanDTO diaDiemThamQuanDTO = new DiaDiemThamQuanBUS()
                .searchDiaDiemThamQuanByMaDiaDiem(maDiaDiem);
        if(diaDiemDTO != null && diaDiemThamQuanDTO == null){
            if(diaDiemDAO.deleteDiaDiem(maDiaDiem)){
                diaDiemDTOs.remove(diaDiemDTO);
                System.out.println("Xóa thành công xoaDiaDiem");
                return true;
            }
        }
        System.out.println("Xóa thất bại xoaDiaDiem - Địa điểm chưa có hoặc Địa điểm tham quan đang tham chiếu tới");
        return false;
    }

    private DiaDiemDTO searchDiaDiemByMaDiaDiem(String maDiaDiem) {
        for (DiaDiemDTO diaDiemDTO: diaDiemDTOs){
            if(diaDiemDTO.getMaDiaDiem().equals(maDiaDiem)) return diaDiemDTO;
        }
        return null;
    }

}