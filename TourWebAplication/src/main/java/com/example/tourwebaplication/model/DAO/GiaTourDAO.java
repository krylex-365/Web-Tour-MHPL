/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DAO;

import com.example.tourwebaplication.model.DTO.GiaTourDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author minhk
 */
public class GiaTourDAO {

    Connect conn;

    public GiaTourDAO() {

    }

    public ArrayList<GiaTourDTO> getList() {
        ArrayList<GiaTourDTO> dsGiaTour = new ArrayList<GiaTourDTO>();
        conn = new Connect();
        conn.getConnection();
        String query = "select * from GiaTour where Status=1";
        try {
            conn.executeQuery(query);
            while (conn.rs.next()) {
                GiaTourDTO gt = new GiaTourDTO();
                gt.setMaGia(conn.rs.getString(1));
                gt.setMaTour(conn.rs.getString(2));
                gt.setThanhTien(conn.rs.getString(3));
                gt.setTgBatDau(conn.rs.getString(4));
                gt.setTgKetThuc(conn.rs.getString(5));
                gt.setHienHanh(conn.rs.getInt(6));
                dsGiaTour.add(gt);
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("GiaTourDAO.getList.executeQuery error.");
        }
        try {
            conn.getConn().close();
        } catch (SQLException e) {
            System.out.println("GiaTourDAO.getList.close error.");
        }
        return dsGiaTour;
    }

    public boolean updateGiaTour(GiaTourDTO giaTourDTO) {
        //sua hien hanh thanh 1
        //cac bang co ma tour khac sua thanh 0
        conn = new Connect();
        conn.getConnection();
        String sql = "UPDATE GiaTour SET"
                + " ThanhTien='" + giaTourDTO.getThanhTien() + "',"
                + " TgBatDau='" + giaTourDTO.getTgBatDau() + "',"
                + " TgKetThuc='" + giaTourDTO.getTgKetThuc() + "'"
                + " WHERE MaGia='" + giaTourDTO.getMaGia() + "' AND MaTour='" + giaTourDTO.getMaTour() +"';";
        if (conn.executeUpdate(sql)) {
            conn.close();
            System.out.println("update GiaTourDAO success");
            return true;
        }
        conn.close();
        System.out.println("update GiaTourDAO fail");
        return false;
    }

    public boolean deleteGiaTour(String maTour, String maGia) {
        conn = new Connect();
        conn.getConnection();
        String sql = "UPDATE GiaTour SET" +
                " HienHanh=0," +
                " Status=0" +
                " WHERE MaTour='" + maTour + "' AND MaGia='" + maGia + "';";
        if (conn.executeUpdate(sql)) {
            conn.close();
            System.out.println("delete GiaTourDAO success");
            return true;
        }
        conn.close();
        System.out.println("delete GiaTourDAO fail");
        return false;
    }

    public boolean insertGiaTourByTour(GiaTourDTO giaTourDTO) {
        conn = new Connect();
        conn.getConnection();
        String query = "INSERT INTO GiaTour"
                + " VALUES ('" + giaTourDTO.getMaGia() + "','" + giaTourDTO.getMaTour()
                + "','" + giaTourDTO.getThanhTien() + "','" + giaTourDTO.getTgBatDau()
                + "','" + giaTourDTO.getTgKetThuc() + "','" + giaTourDTO.getHienHanh() + "', 1);";
        if (conn.executeUpdate(query)) {
            conn.close();
            System.out.println("insert GiaTourByTour success");
            return true;
        }
        conn.close();
        System.out.println("insert GiaTourByTour fail");
        return false;
    }

    public boolean updateHienHanhByTour(String maGia, String maTour) {
        //sua hien hanh thanh 1
        //cac bang co ma tour khac sua thanh 0
        conn = new Connect();
        conn.getConnection();
        String sql1 = "UPDATE GiaTour SET"
                + " HienHanh=0"
                + " WHERE MaTour='" + maTour + "' AND HienHanh=1;";
        String sql2 = "UPDATE GiaTour SET"
                + " HienHanh=1"
                + " WHERE MaGia='" + maGia + "' AND MaTour='" + maTour +"';";
        if (conn.executeUpdate(sql1) && conn.executeUpdate(sql2)) {
            conn.close();
            System.out.println("update HH GiaTourByTour success");
            return true;
        }
        conn.close();
        System.out.println("update HH GiaTourByTour fail");
        return false;
    }
    
    public boolean deleteGiaTourByTour(String maTour) {
        conn = new Connect();
        conn.getConnection();
        String sql = "UPDATE GiaTour " +
                "SET HienHanh = 0, " +
                "Status = 0 " +
                "WHERE MaTour='" + maTour + "' and Status=1";
        if (conn.executeUpdate(sql)) {
            conn.close();
            System.out.println("delete GiaTourByTour success");
            return true;
        }
        conn.close();
        System.out.println("delete GiaTourByTour fail");
        return false;
    }
}
