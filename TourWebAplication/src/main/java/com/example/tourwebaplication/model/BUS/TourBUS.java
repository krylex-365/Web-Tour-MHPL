package com.example.tourwebaplication.model.BUS;

import com.example.tourwebaplication.model.DAO.MaDuLieuCuoiDAO;
import com.example.tourwebaplication.model.DAO.TourDAO;
import com.example.tourwebaplication.model.DTO.DoanDuLichDTO;
import com.example.tourwebaplication.model.DTO.GiaTourDTO;
import com.example.tourwebaplication.model.DTO.TourDTO;

import javax.swing.*;
import java.util.ArrayList;

public class TourBUS {

    private ArrayList<TourDTO> tourDTOs;
    public static ArrayList<GiaTourDTO> giaTourDTOs;
    private TourDAO tourDAO;
    private GiaTourBUS giaTourBUS;
    private Utils utl = new Utils();
    private MaDuLieuCuoiDAO maLast = new MaDuLieuCuoiDAO();

    public TourBUS() {
        tourDAO = new TourDAO();
        giaTourBUS = new GiaTourBUS();
        this.tourDTOs = tourDAO.getList();
        this.giaTourDTOs = giaTourBUS.getGiaTourDTOs();
    }
    
    public String CapPhat(String init) {
        System.out.println("- cap 1");
        init = utl.initMaTour(init);
        System.out.println("- cap 2");
        return init;
    }
    
    public boolean themTour(String MaTour, String MaLoai, String TenTour, String DacDiem, 
            String ThanhTien, String TgBatDau, String TgKetThuc) {
        for (TourDTO tour : tourDTOs) {
            if (tour.getMaTour().equals(MaTour)) {
                JOptionPane.showMessageDialog(null, "Mã tour" + MaTour + " đã tồn tại!");
                return false;
            }
        }
        TourDTO newTour = new TourDTO(MaTour,MaLoai,TenTour,DacDiem);
        if (tourDAO.insertTour(newTour)) {
            if (giaTourBUS.themGiaTourByTour(MaTour, ThanhTien, TgBatDau, TgKetThuc)) {
                tourDTOs.add(newTour);
                System.out.println("Thêm thành công themTourBUS");
                maLast.updateMaTour(MaTour);
                return true;
            }
        }
        System.out.println("Thêm thất bại themTourBUS");
        return false;
    }

    public boolean suaTour(String maTour, String tenTour, String dacDiem, String maLoaiHH, 
            String maLoai, String maGiaHH, String maGia) {
        int indexTour = indexTour(maTour);
        if (indexTour == -1) {
            return false;
        }
        TourDTO tourDTO = tourDTO = new TourDTO(maTour, maLoai, tenTour, dacDiem);
        boolean checkLH;
        if (maLoaiHH.equals(maLoai)){
            // NẾU KHÔNG SỬA LOẠI HÌNH
            checkLH = false;
        } else {
            // NẾU SỬA LOẠI HÌNH
            checkLH = true;
        }
        if (tourDAO.updateTour(tourDTO, checkLH)) {
            tourDTOs.set(indexTour, tourDTO);
            if (maGiaHH.equals(maGia)){
                return true;
            }
            if (giaTourBUS.suaHienHanh(maGia, maTour)) {
                System.out.println("Sửa thành công suaTourBUS");
                return true;
            }
        }
        System.out.println("Sửa thất bại suaTourBUS");
        return false;
    }
    
    public boolean xoaTour(String maTour){
        DoanDuLichDTO doanDuLichDTO = new DoanDuLichBUS().getDoanDuLichByMaTour(maTour);
        if(doanDuLichDTO == null) {
            if(tourDAO.deleteTour(maTour)){
                tourDTOs.remove(indexTour(maTour));
                new DiaDiemThamQuanBUS().xoaDiaDiemThamQuanByMaTour(maTour);
                new GiaTourBUS().xoaGiaTourByMaTour(maTour);
                System.out.println("Xóa thành công xoaTourBUS");
                return true;
            }
        }
        System.out.println("Xóa thất bại xoaTourBUS");
        return false;
    }

    private int indexTour(String maTour) {
        for (int i = 0; i < tourDTOs.size(); i++) {
            if (maTour.equals(tourDTOs.get(i).getMaTour())) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<TourDTO> getTourDTOS() {
        return tourDTOs;
    }

    public void setTourDTOS(ArrayList<TourDTO> tourDTOS) {
        this.tourDTOs = tourDTOS;
    }

    public ArrayList<GiaTourDTO> getGiaTourDTOs() {
        return giaTourDTOs;
    }

    public void setGiaTourDTOs(ArrayList<GiaTourDTO> giaTourDTOs) {
        this.giaTourDTOs = giaTourDTOs;
    }
    
    
}
