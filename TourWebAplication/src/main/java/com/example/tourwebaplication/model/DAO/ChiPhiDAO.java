/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DAO;

import com.example.tourwebaplication.model.DTO.ChiPhiDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author minhk
 */
public class ChiPhiDAO {
    Connect conn;
    

    public ChiPhiDAO() {
        
    }
    
    public ArrayList<ChiPhiDTO> getList(){
        ArrayList<ChiPhiDTO> dsChiPhi = new ArrayList<ChiPhiDTO>();
        conn = new Connect();
        conn.getConnection();
        String query = "select * from ChiPhi where Status=1";
        try {
            conn.executeQuery(query);
            while (conn.rs.next()) {
                ChiPhiDTO cp = new ChiPhiDTO();
                cp.setMaChiPhi(conn.rs.getString(1));
                cp.setMaDoan(conn.rs.getString(2));
                cp.setMaLoaiChiPhi(conn.rs.getString(3));
                cp.setSoTien(conn.rs.getString(4));
                cp.setGhiChu(conn.rs.getString(5));
                dsChiPhi.add(cp);
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
        return dsChiPhi;
    }
    
    public boolean insertChiPhi(ChiPhiDTO chiPhiDTO) {
        conn = new Connect();
        conn.getConnection();
        String query = "INSERT INTO ChiPhi"
                + " VALUES ('" + chiPhiDTO.getMaChiPhi()
                + "','" + chiPhiDTO.getMaDoan()
                + "','" + chiPhiDTO.getMaLoaiChiPhi()
                + "','" + chiPhiDTO.getSoTien()
                + "','" + chiPhiDTO.getGhiChu()
                + "', 1);";
        if (conn.executeUpdate(query)) {
            conn.close();
            System.out.println("ChiPhiDAO insert success.");
            return true;
        }
        conn.close();
        System.out.println("ChiPhiDAO insert fail.");
        return false;
    }

    public boolean updateChiPhi(ChiPhiDTO chiPhiDTO, boolean checkLoaiCP) {
        conn = new Connect();
        conn.getConnection();
        String sql;
        if (checkLoaiCP) {
            sql = "UPDATE ChiPhi SET"
                    + " MaLoaiChiPhi='" + chiPhiDTO.getMaLoaiChiPhi()+ "',"
                    + " SoTien='" + chiPhiDTO.getSoTien()+ "',"
                    + " GhiChu='" + chiPhiDTO.getGhiChu()+ "'"
                    + " WHERE MaChiPhi='" + chiPhiDTO.getMaChiPhi()+ "';";
            System.out.println("ChiPhiDAO update maLoaiCP");
        } else {
            sql = "UPDATE ChiPhi SET"
                    + " SoTien='" + chiPhiDTO.getSoTien()+ "',"
                    + " GhiChu='" + chiPhiDTO.getGhiChu()+ "'"
                    + " WHERE MaChiPhi='" + chiPhiDTO.getMaChiPhi()+ "';";
        }
        if (conn.executeUpdate(sql)) {
            conn.close();
            System.out.println("ChiPhiDAO update success.");
            return true;
        }
        conn.close();
        System.out.println("ChiPhiDAO update fail.");
        return false;
    }

    public boolean deleteChiPhi(String maChiPhi) {
        String sql = "update ChiPhi "
                + "set Status=0 "
                + "where MaChiPhi='" + maChiPhi + "'";
        conn = new Connect();
        conn.getConnection();
        if (conn.executeUpdate(sql)) {
            conn.close();
            System.out.println("ChiPhi delete success.");
            return true;
        }
        conn.close();
        System.out.println("ChiPhi delete fail.");
        return false;
    }
}
