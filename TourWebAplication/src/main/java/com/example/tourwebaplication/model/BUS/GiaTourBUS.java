package com.example.tourwebaplication.model.BUS;

import com.example.tourwebaplication.model.DAO.GiaTourDAO;
import com.example.tourwebaplication.model.DAO.MaDuLieuCuoiDAO;
import com.example.tourwebaplication.model.DTO.GiaTourDTO;

import java.util.ArrayList;

public class GiaTourBUS {

    private ArrayList<GiaTourDTO> giaTourDTOs;
    private GiaTourDAO giaTourDAO;
    private Utils utl = new Utils();
    private MaDuLieuCuoiDAO maLast = new MaDuLieuCuoiDAO();

    public GiaTourBUS() {
        this.giaTourDAO = new GiaTourDAO();
        this.giaTourDTOs = giaTourDAO.getList();
    }

    public String CapPhat(String init) {
        System.out.println("- cap 1");
        init = utl.initMaGia(init);
        System.out.println("- cap 2");
        return init;
    }

    private int indexTour(String maGia) {
        for (int i = 0; i < giaTourDTOs.size(); i++) {
            if (maGia.equals(giaTourDTOs.get(i).getMaGia())) {
                return i;
            }
        }
        return -1;
    }

    public boolean themGiaTour(String MaGia, String MaTour, String ThanhTien, String TgBatDau, String TgKetThuc){
        GiaTourDTO giaTourDTO = new GiaTourDTO(MaGia, MaTour, ThanhTien, TgBatDau, TgKetThuc, 0);
        if (giaTourDAO.insertGiaTourByTour(giaTourDTO)) {
            TourBUS.giaTourDTOs.add(giaTourDTO);
            giaTourDTOs.add(giaTourDTO);
            System.out.println("Thêm thành công GiaTourBUS");
            maLast.updateMaGia(MaGia);
            return true;
        }
        System.out.println("Thêm thất bại GiaTourBUS");
        return false;
    }

    public boolean suaGiaTour(String MaTour, String MaGia, String ThanhTien, String TgBatDau, String TgKetThuc, int HienHanh){
        int i = indexTour(MaGia);
        GiaTourDTO giaTourDTO = new GiaTourDTO(MaGia, MaTour, ThanhTien, TgBatDau, TgKetThuc, HienHanh);
        if (giaTourDAO.updateGiaTour(giaTourDTO)) {
            TourBUS.giaTourDTOs.set(i, giaTourDTO);
            giaTourDTOs.set(i, giaTourDTO);
            System.out.println("Sửa thành công GiaTourBUS");
            return true;
        }
        System.out.println("Sửa thất bại GiaTourBUS");
        return false;
    }
    
    public boolean xoaGiaTour(String MaTour, String MaGia, String ThanhTien, String TgBatDau, String TgKetThuc, int HienHanh){
        GiaTourDTO giaTourDTO = new GiaTourDTO(MaGia, MaTour, ThanhTien, TgBatDau, TgKetThuc, HienHanh);
        if (giaTourDAO.deleteGiaTour(MaTour, MaGia)) {
            TourBUS.giaTourDTOs.remove(giaTourDTO);
            giaTourDTOs.remove(giaTourDTO);
            System.out.println("Xóa thành công GiaTourBUS");
            return true;
        }
        System.out.println("Xóa thất bại GiaTourBUS");
        return false;
    }
    
    public boolean themGiaTourByTour(String MaTour, String ThanhTien, String TgBatDau, String TgKetThuc) {
//        int newMaGia = giaTourDTOs.size() + 1 ;
        String MaGia = null;
        MaGia = CapPhat(MaGia);
        GiaTourDTO giaTourDTO = new GiaTourDTO(MaGia, MaTour, ThanhTien, TgBatDau, TgKetThuc, 1);
        if (giaTourDAO.insertGiaTourByTour(giaTourDTO)) {
            giaTourDTOs.add(giaTourDTO);
            System.out.println("Thêm thành công themGiaTourByTour");
            maLast.updateMaGia(MaGia);
            return true;
        }
        System.out.println("Thêm thất bại themGiaTourByTour");
        return false;
    }

    public boolean suaHienHanh(String maGia, String maTour) {
//        if (!giaTourDAO.updateHienHanhByTour(maGia, maTour)) {
//            return false;
//        }
//        setHienHanh(maGia, maTour);
//        return true;
        if (giaTourDAO.updateHienHanhByTour(maGia, maTour)) {
            setHienHanh(maGia, maTour);
            System.out.println("Sửa Hiện hành thành công suaHienHanhBUS");
            return true;
        }
        System.out.println("Sửa Hiện hành thất bại suaHienHanhBUS");
        return false;
    }

    private boolean setHienHanh(String maGia, String maTour) {
        for (int i = 0; i < giaTourDTOs.size(); i++) {
            GiaTourDTO giaTour = giaTourDTOs.get(i);
            if (giaTour.getMaGia().equals(maGia) && giaTour.getMaTour().equals(maTour)) {
                giaTour.setHienHanh(1);
            } else if (giaTour.getMaTour().equals(maTour)) {
                giaTour.setHienHanh(0);
            }
        }
        return true;
    }
    
    public boolean xoaGiaTourByMaTour(String maTour) {
        if(giaTourDAO.deleteGiaTourByTour(maTour)) {
            for (GiaTourDTO giaTourDTO : giaTourDTOs) {
                if (giaTourDTO.getMaTour().equals(maTour)) {
                    giaTourDTOs.remove(giaTourDTO);
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<GiaTourDTO> getGiaTourDTOs() {
        return giaTourDTOs;
    }

    public void setGiaTourDTOs(ArrayList<GiaTourDTO> giaTourDTOs) {
        this.giaTourDTOs = giaTourDTOs;
    }

    public GiaTourDAO getGiaTourDAO() {
        return giaTourDAO;
    }

    public void setGiaTourDAO(GiaTourDAO giaTourDAO) {
        this.giaTourDAO = giaTourDAO;
    }

}
