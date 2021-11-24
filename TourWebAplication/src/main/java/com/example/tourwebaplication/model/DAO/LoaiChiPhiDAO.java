/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DAO;

import com.example.tourwebaplication.model.DTO.LoaiChiPhiDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author minhk
 */
public class LoaiChiPhiDAO {
    Connect conn;


    public LoaiChiPhiDAO() {

    }

    public ArrayList<LoaiChiPhiDTO> getList(){
        ArrayList<LoaiChiPhiDTO> dsLoaiChiPhi = new ArrayList<LoaiChiPhiDTO>();
        conn = new Connect();
        conn.getConnection();
        String query = "select * from LoaiChiPhi where Status=1";
        try {
            conn.executeQuery(query);
            while (conn.rs.next()) {
                LoaiChiPhiDTO lcp = new LoaiChiPhiDTO();
                lcp.setMaLoaiChiPhi(conn.rs.getString(1));
                lcp.setTenLoai(conn.rs.getString(2));
                dsLoaiChiPhi.add(lcp);
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("LoaiChiPhiDAO.getList.executeQuery error.");
        }
        try{
        conn.getConn().close();
        }catch (SQLException e){
            System.out.println("LoaiChiPhiDAO.getList.close error.");
        }
        return dsLoaiChiPhi;
    }

    public boolean insertLoaiChiPhi(LoaiChiPhiDTO loaiChiPhiDTO)
    {
        String sql = "insert into LoaiChiPhi"
                + " values ('" + loaiChiPhiDTO.getMaLoaiChiPhi() + "', '" + loaiChiPhiDTO.getTenLoai() + "', 1)";
        conn = new Connect();
        conn.getConnection();
        if (conn.executeUpdate(sql))
        {
            conn.close();
            System.out.println("LoaiChiPhiDAO insert success.");
            return true;
        }
        conn.close();
        System.out.println("LoaiChiPhiDAO insert fail.");
        return false;
    }

    public boolean updateMaLoaiChiPhi(String maLoaiChiPhi, String tenLoaiChiPhi)
    {
        String sql = "update LoaiChiPhi set"
                + " TenLoai = '" + tenLoaiChiPhi + "'"
                + " where MaLoaiChiPhi = '" + maLoaiChiPhi + "'";
        conn = new Connect();
        conn.getConnection();
        if (conn.executeUpdate(sql))
        {
            conn.close();
            System.out.println("LoaiChiPhiDAO update success.");
            return true;
        }
        conn.close();
        System.out.println("LoaiChiPhiDAO update fail.");
        return false;
    }

    public boolean deleteLoaiChiPhi(String maLoaiChiPhi)
    {
        String sql = "update LoaiChiPhi set"
                + " Status = 0"
                + " where MaLoaiChiPhi = '" + maLoaiChiPhi + "'";
        conn = new Connect();
        conn.getConnection();
        if (conn.executeUpdate(sql))
        {
            conn.close();
            System.out.println("LoaiChiPhiDAO delete success.");
            return true;
        }
        conn.close();
        System.out.println("LoaiChiPhiDAO delete fail.");
        return false;
    }
}
