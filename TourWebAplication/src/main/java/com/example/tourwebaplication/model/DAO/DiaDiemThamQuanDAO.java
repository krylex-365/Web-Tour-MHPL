/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DAO;

import com.example.tourwebaplication.model.DTO.DiaDiemThamQuanDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author minhk
 */
public class DiaDiemThamQuanDAO {
    Connect conn;


    public DiaDiemThamQuanDAO() {

    }

    public ArrayList<DiaDiemThamQuanDTO> getList(){
        ArrayList<DiaDiemThamQuanDTO> dsDiaDiemThamQuan = new ArrayList<DiaDiemThamQuanDTO>();
        conn = new Connect();
        conn.getConnection();
        String query = "select * from DiaDiemThamQuan where Status=1";
        try {
            conn.executeQuery(query);
            while (conn.rs.next()) {
                DiaDiemThamQuanDTO ddtq = new DiaDiemThamQuanDTO();
                ddtq.setMaTour(conn.rs.getString(1));
                ddtq.setMaDiaDiem(conn.rs.getString(2));
                ddtq.setThuTu(conn.rs.getInt(3));
                dsDiaDiemThamQuan.add(ddtq);
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("DiDiemThamQuanDAO.getList.executeQuery error.");
        }
        try{
        conn.getConn().close();
        }catch (SQLException e){
            System.out.println("DiDiemThamQuanDAO.getList.close error.");
        }
        return dsDiaDiemThamQuan;
    }

    public boolean add(String maTour,String maDiaDiem,int thuTu){
        conn = new Connect();
        conn.getConnection();
        String query = "insert into DiaDiemThamQuan"
                + " (MaTour,MaDiaDiem,ThuTu)"
                + " values ('"+maTour+"','"+maDiaDiem+"',"+thuTu+")";

        if(conn.executeUpdate(query)){
            System.out.println("DiaDiemThamQuanDAO add success.");
            conn.close();
            return true;
        }
        return false;
    }

    public boolean Add(String maTour,String maDiaDiem,int thuTu){
        conn = new Connect();
        conn.getConnection();
        String query = " IF EXISTS (SELECT * FROM DiaDiemThamQuan WHERE MaTour='"+maTour+"' AND MaDiaDiem='"+maDiaDiem+"' AND ThuTu="+thuTu+") "+
        " BEGIN "+
        " UPDATE DiaDiemThamQuan SET Status=1 WHERE MaTour='"+maTour+"' AND MaDiaDiem='"+maDiaDiem+"' AND ThuTu="+thuTu+
        " END "+
        " ELSE "+
        " BEGIN "+
        " INSERT INTO DiaDiemThamQuan (MaTour,MaDiaDiem,ThuTu) VALUES ('"+maTour+"','"+maDiaDiem+"',"+thuTu+") "+
        " END";

        try{
        if(conn.executeUpdate(query)){
            conn.getConn().close();
            return true;
        }

        return false;
        }catch (SQLException e){
            System.out.println("DiDiemThamQuanDAO.getList.close error.");
        }
        return false;
    }

    public boolean delete(String maTour,String maDiaDiem,int thuTu){
        conn = new Connect();
        conn.getConnection();
        String query = "update DiaDiemThamQuan " +
                        "set Status=0 " +"where MaTour='"+maTour+"'"+"and MaDiaDiem='"+maDiaDiem+"'"+"and ThuTu="+thuTu;
        if(conn.executeUpdate(query)){
            System.out.println("DiaDiemThamQuanDAO delete success.");
            return true;
        }
        return false;
    }

    public boolean deleteAll(String maTour){
        conn = new Connect();
        conn.getConnection();
        String query = "delete from DiaDiemThamQuan where MaTour='"+maTour+"'";
        if(conn.executeUpdate(query)){
            System.out.println("DiaDiemThamQuanDAO deleteAll success.");
            return true;
        }
        return false;
    }

    public boolean deleteDDTQuanByMaTour(String maTour) {
        String sql =    "update DiaDiemThamQuan " +
                        "set Status=0 " +
                        "where MaTour='" + maTour + "' and Status=1";
        conn = new Connect();
        conn.getConnection();
        if(conn.executeUpdate(sql)){
            conn.close();
            return true;
        }
        return false;
    }
    
    public boolean updateDiaDiemThamQuan(String  maTour,String maDiaDiem,int thuTu,int newThuTu){
        String sql =    "update DiaDiemThamQuan " +
                        "set ThuTu=" +newThuTu+
                        " where MaTour='"+maTour+"'"+"and MaDiaDiem='"+maDiaDiem+"'"+"and ThuTu="+thuTu;
        conn = new Connect();
        conn.getConnection();
        if(conn.executeUpdate(sql)){
            System.out.println("DiaDiemThamQuanDAO update success.");
            conn.close();
            return true;
        }
        return false;
    }
}
