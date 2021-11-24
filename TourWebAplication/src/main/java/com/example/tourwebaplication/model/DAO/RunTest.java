/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tourwebaplication.model.DAO;

import com.example.tourwebaplication.model.DTO.TourDTO;

/**
 *
 * @author minhk
 */
public class RunTest {
    
    public static void main(String[] args) {
        TourDAO dsNhanVien = new TourDAO();
        for(TourDTO a : dsNhanVien.getList()){
            System.out.println(a.toString());
        }
    }
}
