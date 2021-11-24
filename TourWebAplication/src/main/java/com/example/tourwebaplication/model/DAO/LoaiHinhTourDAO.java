/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DAO;

import com.example.tourwebaplication.model.DTO.LoaiHinhTourDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author minhk
 */
public class LoaiHinhTourDAO {

    Connect conn;

    public LoaiHinhTourDAO() {

    }

    public ArrayList<LoaiHinhTourDTO> getList() {
        ArrayList<LoaiHinhTourDTO> dsLoaiHinh = new ArrayList<LoaiHinhTourDTO>();
        conn = new Connect();
        conn.getConnection();
        String query = "select * from LoaiHinhTour where Status=1";
        try {
            conn.executeQuery(query);
            while (conn.rs.next()) {
                LoaiHinhTourDTO lht = new LoaiHinhTourDTO();
                lht.setMaLoai(conn.rs.getString(1));
                lht.setTenLoai(conn.rs.getString(2));
                dsLoaiHinh.add(lht);
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("LoaiHinhTourDAO.getList.executeQuery error.");
        }
        try {
            conn.getConn().close();
        } catch (SQLException e) {
            System.out.println("LoaiHinhTourDAO.getList.close error.");
        }
        return dsLoaiHinh;
    }

    public boolean insertLoaiHinh(String maLoai, String tenLoai) {
        conn = new Connect();
        conn.getConnection();
        String query = "insert into LoaiHinhTour "
                + "values ('" + maLoai + "','" + tenLoai + "', 1);";
        if (conn.executeUpdate(query)) {
            conn.close();
            System.out.println("LoaiHinhTourDAO insert success.");
            return true;
        }
        conn.close();
        System.out.println("LoaiHinhTourDAO insert fail.");
        return false;
    }

    public boolean deleteLoaiHinh(String maLoai) {
        conn = new Connect();
        conn.getConnection();
        String sql = "update LoaiHinhTour "
                + "set Status=0 "
                + "where MaLoai='" + maLoai + "'";
        if (conn.executeUpdate(sql)) {
            conn.close();
            System.out.println("LoaiHinhTourDAO delete success.");
            return true;
        }
        conn.close();
        System.out.println("LoaiHinhTourDAO delete fail.");
        return false;
    }

    public boolean updateLoaiHinh(LoaiHinhTourDTO loaiHinhTourDTO) {
        conn = new Connect();
        conn.getConnection();
        String sql = "UPDATE LoaiHinhTour SET"
                + " TenLoai='" + loaiHinhTourDTO.getTenLoai() + "'"
                + " WHERE MaLoai='" + loaiHinhTourDTO.getMaLoai() + "'";
        if (conn.executeUpdate(sql)) {
            conn.close();
            System.out.println("LoaiHinhTourDAO update success.");
            return true;
        }
        conn.close();
        System.out.println("LoaiHinhTourDAO update fail.");
        return false;
    }
}
