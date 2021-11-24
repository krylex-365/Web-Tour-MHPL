/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DAO;

import com.example.tourwebaplication.model.DTO.DiaDiemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author minhk
 */
public class DiaDiemDAO {
    Connect conn;


    public DiaDiemDAO() {

    }

    public ArrayList<DiaDiemDTO> getList(){
        ArrayList<DiaDiemDTO> dsDiaDiem = new ArrayList<DiaDiemDTO>();
        conn = new Connect();
        conn.getConnection();
        String query = "select * from DiaDiem where Status=1";
        try {
            conn.executeQuery(query);
            while (conn.rs.next()) {
                DiaDiemDTO dd = new DiaDiemDTO();
                dd.setMaDiaDiem(conn.rs.getString(1));
                dd.setTenDiaDiem(conn.rs.getString(2));
                dsDiaDiem.add(dd);
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("DiaDiemDAO.getList.executeQuery error.");
        }
        try{
        conn.getConn().close();
        }catch (SQLException e){
            System.out.println("DiaDiemDAO.getList.close error.");
        }
        return dsDiaDiem;
    }

    public boolean insertDiaDiem(DiaDiemDTO diaDiemDTO) {
        String sql = "insert into DiaDiem" +
                " values ('"+diaDiemDTO.getMaDiaDiem()+"', '"+diaDiemDTO.getTenDiaDiem()+"', 1)";
        conn = new Connect();
        conn.getConnection();
        if(conn.executeUpdate(sql)){
            conn.close();
            System.out.println("DiaDiemDAO insert success.");
            return true;
        }
        conn.close();
        System.out.println("DiaDiemDAO insert fail.");
        return false;
    }

    public boolean updateMaDiaDiem(String maDiaDiem, String tenDiaDiem) {
        String sql = "update DiaDiem set" +
                    " TenDiaDiem = '" + tenDiaDiem + "'" +
                    " where MaDiaDiem = '" + maDiaDiem + "'";
        conn = new Connect();
        conn.getConnection();
        if(conn.executeUpdate(sql)){
            conn.close();
            System.out.println("DiaDiemDAO update success.");
            return true;
        }
        conn.close();
        System.out.println("DiaDiemDAO update fail.");
        return false;
    }

    public boolean deleteDiaDiem(String maDiaDiem) {
        String sql = "update DiaDiem set" +
                " Status = 0" +
                " where MaDiaDiem = '" + maDiaDiem + "'";
        conn = new Connect();
        conn.getConnection();
        if(conn.executeUpdate(sql)){
            conn.close();
            System.out.println("DiaDiemDAO delete success.");
            return true;
        }
        conn.close();
        System.out.println("DiaDiemDAO delete fail.");
        return false;
    }
}
