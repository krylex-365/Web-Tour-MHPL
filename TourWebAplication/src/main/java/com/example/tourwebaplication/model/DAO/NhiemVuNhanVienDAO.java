/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DAO;

import com.example.tourwebaplication.model.DTO.NhiemVuNhanVienDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author minhk
 */
public class NhiemVuNhanVienDAO {
    Connect conn;
    

    public NhiemVuNhanVienDAO() {
        
    }
    
    public ArrayList<NhiemVuNhanVienDTO> getList(){
        ArrayList<NhiemVuNhanVienDTO> dsNhiemVuNhanVien = new ArrayList<NhiemVuNhanVienDTO>();
        conn = new Connect();
        conn.getConnection();
        String query = "select * from NhiemVuNhanVien where Status=1";
        try {
            conn.executeQuery(query);
            while (conn.rs.next()) {
                NhiemVuNhanVienDTO nvnv = new NhiemVuNhanVienDTO();
                nvnv.setMaNhanVien(conn.rs.getString(1));
                nvnv.setMaDoan(conn.rs.getString(2));
                nvnv.setTenNhiemVu(conn.rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("ChiPhiDao.getList.executeQuery error.");
        }
        try{
        conn.getConn().close();
        }catch (SQLException e){
            System.out.println("ChiPhiDao.getList.close error.");
        }
        return dsNhiemVuNhanVien;
    }
    
}
