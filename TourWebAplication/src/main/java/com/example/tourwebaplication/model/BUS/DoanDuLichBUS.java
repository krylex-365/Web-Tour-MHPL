/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.BUS;

import com.example.tourwebaplication.model.DAO.DoanDuLichDAO;
import com.example.tourwebaplication.model.DAO.MaDuLieuCuoiDAO;
import com.example.tourwebaplication.model.DTO.DoanDuLichDTO;
import java.util.Date;
import java.util.ArrayList;
//import org.codehaus.groovy.runtime.DefaultGroovyMethods;

/**
 *
 * @author User
 */
public class DoanDuLichBUS {

    private DoanDuLichDAO doanDuLichDAO;
    private ArrayList<DoanDuLichDTO> doanDuLichDTOs;
    private MaDuLieuCuoiDAO maLast = new MaDuLieuCuoiDAO();
    private Utils utl = new Utils();

    public DoanDuLichBUS(DoanDuLichDAO doanDuLichDAO, ArrayList<DoanDuLichDTO> doanDuLichDTOs) {
        this.doanDuLichDAO = doanDuLichDAO;
        this.doanDuLichDTOs = doanDuLichDTOs;
    }

    public DoanDuLichBUS() {
        doanDuLichDAO = new DoanDuLichDAO();
        doanDuLichDTOs = doanDuLichDAO.getList();
    }

    public ArrayList<DoanDuLichDTO> getDoanDuLichDTOs() {
        return doanDuLichDAO.getList();
    }

    public ArrayList<DoanDuLichDTO> searchDoanDuLichByMaTour(String maTour) {
        ArrayList<DoanDuLichDTO> result = new ArrayList<DoanDuLichDTO>();
        for (DoanDuLichDTO a : doanDuLichDTOs) {
            if (a.getMaTour().equals(maTour)) {
                result.add(a);
                System.out.println(a);
            }
        }
        return result;
    }

    public ArrayList<DoanDuLichDTO> searchDoanDuLichByMaDoan(String maDoan) {
        ArrayList<DoanDuLichDTO> result = new ArrayList<DoanDuLichDTO>();
        for (DoanDuLichDTO a : doanDuLichDTOs) {
            if (a.getMaDoan().equals(maDoan)) {
                result.add(a);
            }
        }
        return result;
    }

//    public int soKhach(String maDoan) {
//        int count = 0;
//        for (DoanDuLichDTO dto : doanDuLichDTOs) {
//            if (dto.getMaDoan().equals(maDoan)) {
//                count++;
//            }
//        }
//        return count;
//    }
    public DoanDuLichDTO getDoanDuLichByMaTour(String maTour) {
        for (DoanDuLichDTO doanDuLichDTO : doanDuLichDTOs) {
            if (doanDuLichDTO.getMaTour().equals(maTour)) {
                return doanDuLichDTO;
            }
        }
        return null;
    }

    public int countDoanTrongTour(String maTour) {
        int count = 0;
        for (DoanDuLichDTO a : doanDuLichDTOs) {
            if (a.getMaTour().equals(maTour)) {
                count++;
            }
        }
        return count;
    }
    
    private int indexDoan(ArrayList<DoanDuLichDTO> doanDuLichDTOs, String maDoan) {
        for (int i = 0; i < doanDuLichDTOs.size(); i++) {
            if (maDoan.equals(doanDuLichDTOs.get(i).getMaDoan())) {
                return i;
            }
        }
        return -1;
    }

    public boolean themDoan(DoanDuLichDTO doanDuLichDTO/*, ArrayList<DoanDuLichDTO> doanDuLichDTOs*/) {
        for (DoanDuLichDTO doan : doanDuLichDTOs) {
            if (doan.getMaDoan().equals(doanDuLichDTO.getMaDoan())) {
                return false;
            }
        }
        if (doanDuLichDAO.insertDoan(doanDuLichDTO)) {
            doanDuLichDTOs.add(doanDuLichDTO);
            maLast.updateMaDoanDuLich(doanDuLichDTO.getMaDoan());
            System.out.println("Thêm thành công themDoanDuLichBUS");
            return true;
        }
        System.out.println("Thêm thất bại themDoanDuLichBUS");
        return false;
    }

    public boolean suaDoan(DoanDuLichDTO doanDuLichDTO, /*ArrayList<DoanDuLichDTO> doanDuLichDTOs,*/ String maTourHH) {
        int index = indexDoan(doanDuLichDTOs, doanDuLichDTO.getMaDoan());
        if (index == -1) {
            return false;
        }
        boolean checkTour;
        if (maTourHH.equals(doanDuLichDTO.getMaTour())){
            // NẾU KHÔNG SỬA TOUR
            checkTour = false;
        } else {
            // NẾU SỬA TOUR
            checkTour = true;
        }
        if (doanDuLichDAO.updateDoan(doanDuLichDTO, checkTour)) {
            doanDuLichDTOs.set(index, doanDuLichDTO);
            System.out.println("Sửa thành công suaDoanDuLichBUS");
            return true;
        }
        System.out.println("Sửa thất bại suaDoanDuLichBUS");
        return false;
    }

    public boolean xoaDoan(String maDoan/*, ArrayList<DoanDuLichDTO> doanDuLichDTOs*/) {
        if (doanDuLichDAO.deleteDoan(maDoan)) {
            //doanDuLichDTOs.remove(doanDuLichDTO);
            System.out.println("Xóa thành công suaDoanDuLichBUS");
            return true;
        }
        System.out.println("Xóa thất bại suaDoanDuLichBUS");
        return false;
    }

    public ArrayList<DoanDuLichDTO> searchDoanByDate(Date start,Date end){
        ArrayList<DoanDuLichDTO> arr = new ArrayList<>();
        System.out.println(start +"        " + end);
        for(DoanDuLichDTO a : doanDuLichDTOs){
            System.out.println(a);
            if(utl.stringToDate(a.getNgayKetThuc()).after(start)&&utl.stringToDate(a.getNgayKetThuc()).before(end)){
                System.out.println(a);
                arr.add(a);
            }
        }
        return arr;
    }

}
