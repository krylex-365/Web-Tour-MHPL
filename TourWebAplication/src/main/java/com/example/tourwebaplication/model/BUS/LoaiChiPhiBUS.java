/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.BUS;

import com.example.tourwebaplication.model.DAO.LoaiChiPhiDAO;
import com.example.tourwebaplication.model.DAO.MaDuLieuCuoiDAO;
import com.example.tourwebaplication.model.DTO.DiaDiemThamQuanDTO;
import com.example.tourwebaplication.model.DTO.LoaiChiPhiDTO;

import java.util.ArrayList;

/**
 *
 * @author minhk
 */
public class LoaiChiPhiBUS
{
    private LoaiChiPhiDAO loaiChiPhiDAO;
    private Utils utl = new Utils();
    private MaDuLieuCuoiDAO maLast = new MaDuLieuCuoiDAO();

    public LoaiChiPhiBUS(LoaiChiPhiDAO loaiChiPhiDAO)
    {
        this.loaiChiPhiDAO = loaiChiPhiDAO;
    }

    public LoaiChiPhiBUS()
    {
        loaiChiPhiDAO = new LoaiChiPhiDAO();
    }

    public String searchTenLoaiChiPhiByMaLoaiChiPhi(String maLoaiChiPhi, ArrayList<LoaiChiPhiDTO> loaiChiPhiDTOs)
    {
        String result = "Lối !!";
        for (LoaiChiPhiDTO a : loaiChiPhiDTOs)
        {
            if (maLoaiChiPhi.equals(a.getMaLoaiChiPhi()))
            {
                result = a.getTenLoai();
            }
        }
        return result;
    }

    public ArrayList<LoaiChiPhiDTO> searchListLoaiChiPhiByMaLoaiChiPhi(String maLoaiChiPhi, ArrayList<LoaiChiPhiDTO> loaiChiPhiDTOs)
    {
        ArrayList<LoaiChiPhiDTO> result = new ArrayList<>();
        for (LoaiChiPhiDTO a : loaiChiPhiDTOs)
        {
            if (maLoaiChiPhi.equals(a.getMaLoaiChiPhi()))
            {
                result.add(a);
            }
        }
        return result;
    }

    public boolean themLoaiChiPhi(String maLoaiChiPhi, String tenLoaiChiPhi, ArrayList<LoaiChiPhiDTO> loaiChiPhiDTOs)
    {
        LoaiChiPhiDTO loaiChiPhiDTO = new LoaiChiPhiDTO(maLoaiChiPhi, tenLoaiChiPhi);
        if (loaiChiPhiDAO.insertLoaiChiPhi(loaiChiPhiDTO))
        {
            loaiChiPhiDTOs.add(loaiChiPhiDTO);
            maLast.updateMaLoaiChiPhi(maLoaiChiPhi);
            System.out.println("Thêm thành công themLoaiChiPhi");
            return true;
        }
        System.out.println("Thêm thất bại themLoaiChiPhi");
        return false;
    }

    public boolean suaLoaiChiPhi(String maLoaiChiPhi, String tenLoaiChiPhi, ArrayList<LoaiChiPhiDTO> loaiChiPhiDTOs)
    {
        LoaiChiPhiDTO loaiChiPhiDTO = searchLoaiChiPhiByMaLoaiChiPhi(maLoaiChiPhi, loaiChiPhiDTOs);
        if (loaiChiPhiDTO != null)
        {
            if (loaiChiPhiDAO.updateMaLoaiChiPhi(maLoaiChiPhi, tenLoaiChiPhi))
            {
                loaiChiPhiDTO.setTenLoai(tenLoaiChiPhi);
                System.out.println("Sửa thành công suaLoaiChiPhi");
                return true;
            }
        }
        System.out.println("Sửa thất bại suaLoaiChiPhi - Loai Chi Phí chưa có");
        return false;
    }

    public boolean xoaLoaiChiPhi(String maLoaiChiPhi, ArrayList<LoaiChiPhiDTO> loaiChiPhiDTOs)
    {
        LoaiChiPhiDTO loaiChiPhiDTO = searchLoaiChiPhiByMaLoaiChiPhi(maLoaiChiPhi, loaiChiPhiDTOs);
        DiaDiemThamQuanDTO diaDiemThamQuanDTO = new DiaDiemThamQuanBUS()
                .searchDiaDiemThamQuanByMaDiaDiem(maLoaiChiPhi);
        if (loaiChiPhiDTO != null && diaDiemThamQuanDTO == null)
        {
            if (loaiChiPhiDAO.deleteLoaiChiPhi(maLoaiChiPhi))
            {
                loaiChiPhiDTOs.remove(loaiChiPhiDTO);
                System.out.println("Xóa thành công xoaLoaiChiPhi");
                return true;
            }
        }
        System.out.println("Xóa thất bại xoaLoaiChiPhi");
        return false;
    }

    private LoaiChiPhiDTO searchLoaiChiPhiByMaLoaiChiPhi(String maLoaiChiPhi, ArrayList<LoaiChiPhiDTO> loaiChiPhiDTOs)
    {
        for (LoaiChiPhiDTO loaiChiPhiDTO : loaiChiPhiDTOs)
        {
            if (loaiChiPhiDTO.getMaLoaiChiPhi().equals(maLoaiChiPhi))
            {
                return loaiChiPhiDTO;
            }
        }
        return null;
    }

}
