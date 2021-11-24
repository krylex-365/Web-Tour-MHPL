/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DAO;

import com.example.tourwebaplication.model.DTO.AccountDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class AccountDAO {
    Connect conn;

    public AccountDAO() {

    }

    public ArrayList<AccountDTO> getList() {
        ArrayList<AccountDTO> dsAccount = new ArrayList<AccountDTO>();
        conn = new Connect();
        conn.getConnection();
        String query = "select * from Account where Status=1";
        try {
            conn.executeQuery(query);
            while (conn.rs.next()) {
                AccountDTO cp = new AccountDTO();
                cp.setTaiKhoan(conn.rs.getString(1));
                cp.setMatKhau(conn.rs.getString(2));
                dsAccount.add(cp);
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("AccountDAO.getList.executeQuery error.");
        }
        try {
            conn.getConn().close();
        } catch (SQLException e) {
            System.out.println("AccountDAO.getList.close error.");
        }
        return dsAccount;
    }
}
