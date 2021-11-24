/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DAO;

import com.example.tourwebaplication.model.DTO.ChiTietDoanDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author minhk
 */
public class ChiTietDoanDAO {
    Connect conn;
    

    public ChiTietDoanDAO() {
        
    }
    
    public ArrayList<ChiTietDoanDTO> getList(){
        ArrayList<ChiTietDoanDTO> dsChiTietDoan = new ArrayList<ChiTietDoanDTO>();
        conn = new Connect();
        conn.getConnection();
        String query = "select * from ChiTietDoan where Status=1";
        try {
            conn.executeQuery(query);
            while (conn.rs.next()) {
                ChiTietDoanDTO ctd = new ChiTietDoanDTO();
                ctd.setMaDoan(conn.rs.getString(1));
                ctd.setMaKhachHang(conn.rs.getString(2));
                dsChiTietDoan.add(ctd);
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("ChiTietDoanDAO.getList.executeQuery error.");
        }
        try{
        conn.getConn().close();
        }catch (SQLException e){
            System.out.println("ChiTietDoanDAO.getList.close error.");
        }
        return dsChiTietDoan;
    }
    
}
