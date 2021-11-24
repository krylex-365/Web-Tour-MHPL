/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DAO;

import com.example.tourwebaplication.model.DTO.TourDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author minhk
 */
public class TourDAO {

    Connect conn;

    public TourDAO() {

    }

    public ArrayList<TourDTO> getList() {
        ArrayList<TourDTO> dsTour = new ArrayList<TourDTO>();
        conn = new Connect();
        conn.getConnection();
        String query = "select * from Tour where Status=1";
        try {
            conn.executeQuery(query);
            while (conn.rs.next()) {
                TourDTO cp = new TourDTO();
                cp.setMaTour(conn.rs.getString(1));
                cp.setMaLoai(conn.rs.getString(2));
                cp.setTenTour(conn.rs.getString(3));
                cp.setDacDiem(conn.rs.getString(4));
                dsTour.add(cp);
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("TourDAO.getList.executeQuery error.");
        }
        try {
            conn.getConn().close();
        } catch (SQLException e) {
            System.out.println("TourDAO.getList.close error.");
        }
        return dsTour;
    }

    public boolean insertTour(TourDTO tourDTO) {
        conn = new Connect();
        conn.getConnection();
        String query = "INSERT INTO Tour"
                + " VALUES ('" + tourDTO.getMaTour() + "','" + tourDTO.getMaLoai()
                + "','" + tourDTO.getTenTour() + "','" + tourDTO.getDacDiem() + "', 1);";
        if (conn.executeUpdate(query)) {
            conn.close();
            System.out.println("TourDAO insert success.");
            return true;
        }
        conn.close();
        System.out.println("TourDAO insert fail.");
        return false;
    }

    public boolean updateTour(TourDTO tourDTO, boolean checkLH) {
        conn = new Connect();
        conn.getConnection();
        String sql;
        if (checkLH) {
            sql = "UPDATE Tour SET"
                + " TenTour='" + tourDTO.getTenTour() + "',"
                + " DacDiem='" + tourDTO.getDacDiem() + "',"
                + " MaLoai='" + tourDTO.getMaLoai() + "'"
                + " WHERE MaTour='" + tourDTO.getMaTour() + "';";
        } else {
            sql = "UPDATE Tour SET"
                + " TenTour='" + tourDTO.getTenTour() + "',"
                + " DacDiem='" + tourDTO.getDacDiem() + "'"
                + " WHERE MaTour='" + tourDTO.getMaTour() + "';";
        }
        if (conn.executeUpdate(sql)) {
            conn.close();
            System.out.println("TourDAO update success.");
            return true;
        }
        conn.close();
        System.out.println("TourDAO update fail.");
        return false;
    }
    
    public boolean deleteTour(String maTour) {
        String sql =    "update Tour " +
                        "set Status=0 " +
                        "where MaTour='" + maTour + "'";
        conn = new Connect();
        conn.getConnection();
        if(conn.executeUpdate(sql)){
            conn.close();
            System.out.println("TourDAO delete success.");
            return true;
        }
        conn.close();
        System.out.println("TourDAO delete fail.");
        return false;
    }
}
