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
    
    public boolean Add(String maDoan,String maKhachHang){
        conn = new Connect();
        conn.getConnection();
        System.out.println(maDoan + " " + maKhachHang);
        String query = " IF EXISTS (SELECT * FROM ChiTietDoan WHERE MaDoan='"+maDoan+"' AND MaKhachHang='"+maKhachHang+"' ) "+
        " BEGIN "+
        " UPDATE ChiTietDoan SET Status=1 WHERE MaDoan='"+maDoan+"' AND MaKhachHang='"+maKhachHang+"'"+
        " END "+
        " ELSE "+
        " BEGIN "+
        " INSERT INTO ChiTietDoan (MaDoan,MaKhachHang) VALUES ('"+maDoan+"','"+maKhachHang+"') "+
        " END";
        
        try{
            if(conn.executeUpdate(query)){
                conn.getConn().close();
                return true;
            }
            return false;
        }catch (SQLException e){
            System.out.println("ChiTietDoanDAO.add.fail error.");
        }
        return false;
    }
    
    public boolean Delete(String maDoan,String maKhachHang){
        conn = new Connect();
        conn.getConnection();
        String query = "update ChiTietDoan " +
                        "set Status=0 " +"where MaDoan='"+maDoan+"'"+"and MaKhachHang='"+maKhachHang+"'";
        if(conn.executeUpdate(query)){
            System.out.println("ChiTietDoanDAO delete success.");
            return true;
        }
        return false;
    }
    
    
    
}
