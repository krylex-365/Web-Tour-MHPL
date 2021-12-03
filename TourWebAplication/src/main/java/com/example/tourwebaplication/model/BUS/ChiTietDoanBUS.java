/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.tourwebaplication.model.BUS;

import com.example.tourwebaplication.model.DAO.ChiTietDoanDAO;
import com.example.tourwebaplication.model.DTO.ChiTietDoanDTO;

import java.util.ArrayList;

/**
 *
 * @author minhk
 */
public class ChiTietDoanBUS {
    private ChiTietDoanDAO chiTietDoanDAO;
    
    public ChiTietDoanBUS(){
        chiTietDoanDAO = new ChiTietDoanDAO();
    }

    public ArrayList<ChiTietDoanDTO> getList(){
        return chiTietDoanDAO.getList();
    }
    
    public int peopleCountByMaDoan(String maDoan,ArrayList<ChiTietDoanDTO> chiTietDoanDTOs){
        int result = 0;
        for(ChiTietDoanDTO a : chiTietDoanDTOs){
            if(a.getMaDoan().equals(maDoan))result++;
        }
        return result;
    }
    
    public boolean add(String maDoan, String maKhachHang,ArrayList<ChiTietDoanDTO> chiTietDoanDTOs){
        for(ChiTietDoanDTO a : chiTietDoanDTOs){
            if(a.getMaDoan().equals(maDoan)&&a.getMaKhachHang().equals(maKhachHang)){
                return false;
            }
        }
        if(chiTietDoanDAO.Add(maDoan, maKhachHang)){
            chiTietDoanDTOs.add(new ChiTietDoanDTO(maDoan,maKhachHang));
            return true;
        }
        return false;
    }
    
    public boolean delete(String maDoan, String maKhachHang,ArrayList<ChiTietDoanDTO> chiTietDoanDTOs){
        if(chiTietDoanDAO.Delete(maDoan, maKhachHang)){
            for(int i = 0; i < chiTietDoanDTOs.size(); i++){
                if(chiTietDoanDTOs.get(i).getMaDoan().equals(maDoan)&&chiTietDoanDTOs.get(i).getMaKhachHang().equals(maKhachHang)){
                    chiTietDoanDTOs.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
}
