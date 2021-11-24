/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DAO;

import com.example.tourwebaplication.model.DTO.NhanVienDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author minhk
 */
public class NhanVienDAO {
    Connect conn;
    

    public NhanVienDAO() {
        
    }
    
    public ArrayList<NhanVienDTO> getList(){
        ArrayList<NhanVienDTO> dsNhanVien = new ArrayList<NhanVienDTO>();
        conn = new Connect();
        conn.getConnection();
        String query = "select * from nhanvien where not MaNhanVien='admin' and Status=1";
        try {
            conn.executeQuery(query);
            while (conn.rs.next()) {
                NhanVienDTO nv = new NhanVienDTO();
                nv.setMaNhanVien(conn.rs.getString(1));
                nv.setTenNhanVien(conn.rs.getString(2));
                nv.setGioiTinh(conn.rs.getString(3));
                nv.setNgaySinh(conn.rs.getString(4));
                nv.setSDT(conn.rs.getString(5));
                nv.setDiaChi(conn.rs.getString(6));
                dsNhanVien.add(nv);
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("NhanVienDao.getList.executeQuery error.");
        }
        try{
        conn.getConn().close();
        }catch (SQLException e){
            System.out.println("NhanVienDao.getList.close error.");
        }
        return dsNhanVien;
    }
    
    public boolean add(NhanVienDTO nhanVien){
//        String maKhachHang,String tenKhachHang,String cmnd,String diaChi,String gioiTinh,String sdt,String mail,String quocTich
        conn = new Connect();
        conn.getConnection();
        //System.out.println(maTour);
        String query = "insert into NhanVien"
                + " (MaNhanVien,TenNhanVien,NgaySinh,GioiTinh,Sdt,DiaChi)"
                + " values ('"+nhanVien.getMaNhanVien()+"','"+nhanVien.getTenNhanVien()+"','"+nhanVien.getNgaySinh()+"','"+nhanVien.getGioiTinh()+"','"+nhanVien.getSDT()+"','"+nhanVien.getDiaChi()+"')";
        if(conn.executeUpdate(query)){
            System.out.println("NhanVienDAO add success.");
            return true;
        }
        conn.close();
        return false;
    }
    
    public boolean delete(String maNhanVien){
        conn = new Connect();
        conn.getConnection();
        String query = "update NhanVien " +
                        "set Status=0 " +"where MaNhanVien='"+maNhanVien+"'";
        if(conn.executeUpdate(query)){
            System.out.println("NhanVienDAO delete success.");
            return true;
        }
        conn.close();
        return false;
    }
    
    public boolean update(NhanVienDTO nhanVien){
        String sql =    "update NhanVien " +
                        "set TenNhanVien='"+ nhanVien.getTenNhanVien()+"' "+
                        ",DiaChi='"+nhanVien.getDiaChi()+"'" +
                        ",NgaySinh='"+nhanVien.getNgaySinh()+"'" +
                        ",GioiTinh='"+nhanVien.getGioiTinh()+"'" +
                        ",Sdt='"+nhanVien.getSDT()+"'" +
                        " where MaNhanVien='"+nhanVien.getMaNhanVien()+"'";
        conn = new Connect();
        conn.getConnection();
        if(conn.executeUpdate(sql)){
            System.out.println("NhanVienDAO update success.");
            conn.close();
            return true;
        }
        conn.close();
        return false;
    }
    
//    public static void main(String[] args){
//        NhanVienDAO nv = new NhanVienDAO();
//        //nv.add( new NhanVienDTO("NV000005","Nhan Vien A","Nam", "1999-1-1", "123456789", "TP.HCM"));
//        nv.update( new NhanVienDTO("NV000005","Nhan Vien A","1", "1999-1-1", "123456789", "TP.HCM"));
//        nv.delete("NV000005");
//        nv.add(nhanVien)
//    }
}
