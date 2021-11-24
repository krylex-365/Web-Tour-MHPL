package com.example.tourwebaplication.controller;

import com.example.tourwebaplication.model.BUS.DiaDiemBUS;
import com.example.tourwebaplication.model.DTO.DiaDiemDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class HomeController {
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String sayHello() {
        return "hello";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public String sayHello(Model model) {
        DiaDiemBUS diaDiemBUS = new DiaDiemBUS();
        ArrayList<DiaDiemDTO> diaDiemDTOs = diaDiemBUS.getDiaDiemDTOs();
        model.addAttribute("diaDiemDTOs", diaDiemDTOs);
        return "hello";
    }
}
