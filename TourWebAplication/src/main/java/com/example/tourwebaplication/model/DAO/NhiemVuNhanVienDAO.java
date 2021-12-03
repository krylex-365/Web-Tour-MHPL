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
                dsNhiemVuNhanVien.add(nvnv);
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("NhienVuNhanVienDao.getList.executeQuery error.");
        }
        try{
        conn.getConn().close();
        }catch (SQLException e){
            System.out.println("NhienVuNhanVienDao.getList.close error.");
        }
        return dsNhiemVuNhanVien;
    }
    
    public boolean Add(String maNhanVien,String maDoan,String tenNhiemVu){
        conn = new Connect();
        conn.getConnection();
        String query = " IF EXISTS (SELECT * FROM NhiemVuNhanVien WHERE MaNhanVien='"+maNhanVien+"' AND MaDoan='"+maDoan+"')"+
        " BEGIN "+
        " UPDATE NhiemVuNhanVien SET Status=1 WHERE MaNhanVien='"+maNhanVien+"' AND MaDoan='"+maDoan+"'" +
        " END "+
        " ELSE "+
        " BEGIN "+
        " INSERT INTO NhiemVuNhanVien (MaNhanVien,MaDoan,TenNhiemVu) VALUES ('"+maNhanVien+"','"+maDoan+"','"+tenNhiemVu+"') "+
        " END";
        
        try{
        if(conn.executeUpdate(query)){
            System.out.println("NhiemVuNhanVienDAO.add success.");
            conn.getConn().close();
            return true;
        }   
        }catch (SQLException e){
            System.out.println("NhiemVuNhanVienDAO.add.close error.");
            return false;
        }
        return false;
    }
    
    public boolean delete(String maNhanVien,String maDoan){
        conn = new Connect();
        conn.getConnection();
        String query = "update NhiemVuNhanVien " +
                        "set Status=0 " +"where MaNhanVien='"+maNhanVien+"'"+"and MaDoan='"+maDoan+"'";
        if(conn.executeUpdate(query)){
            System.out.println("NhiemVuNhanVienDAO delete success.");
            return true;
        }
        return false;
    } 
    
    public boolean update(String  maNhanVien,String maDoan,String nhiemVuNhanVien){
        String sql =    "update NhiemVuNhanVien " +
                        "set TenNhiemVu='" +nhiemVuNhanVien+"' "+
                        " where MaNhanVien='"+maNhanVien+"'"+"and MaDoan='"+maDoan+"'";
        conn = new Connect();
        conn.getConnection();
        if(conn.executeUpdate(sql)){
            System.out.println("NhiemVuNhanVienDAO update success.");
            conn.close();
            return true;
        }
        return false;
    }
    
}
