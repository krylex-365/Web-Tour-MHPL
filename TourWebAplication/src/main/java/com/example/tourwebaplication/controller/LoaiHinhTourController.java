package com.example.tourwebaplication.controller;

import com.example.tourwebaplication.model.BUS.LoaiHinhTourBUS;
import com.example.tourwebaplication.model.BUS.Utils;
import com.example.tourwebaplication.model.DTO.LoaiHinhTourDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class LoaiHinhTourController {

    @RequestMapping(method = RequestMethod.GET, value = "/loaihinhtour")
    public String getListLoaiHinhTuor(Model model) {
        LoaiHinhTourBUS loaiHinhTourBUS = new LoaiHinhTourBUS();
        ArrayList<LoaiHinhTourDTO> loaiHinhTourDTOs = loaiHinhTourBUS.getLoaiHinhTourDTOs();
        model.addAttribute("loaiHinhTourDTOs", loaiHinhTourDTOs);
        return "loaihinhtour";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/loaihinhtour")
    public String getloaiHinhTuor(@RequestParam("id") String id, Model model) {
        LoaiHinhTourBUS loaiHinhTourBUS = new LoaiHinhTourBUS();
        ArrayList<LoaiHinhTourDTO> loaiHinhTourDTOs = loaiHinhTourBUS.getLoaiHinhTourDTOs();
        for (LoaiHinhTourDTO loaiHinhTourDTO : loaiHinhTourDTOs) {
            if (id.equals(loaiHinhTourDTO.getMaLoai())) {
                model.addAttribute("loaiHinhTourDTO", loaiHinhTourDTO);
                model.addAttribute("loaiHinhTourDTOs", loaiHinhTourDTOs);
                return "loaihinhtour";
            }
        }
        model.addAttribute("loaiHinhTourDTOs", loaiHinhTourDTOs);
        return "loaihinhtour";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sualoaihinhtour")
    public String suaLoaiHinhTuor(@ModelAttribute("loaiHinhTourDTO") LoaiHinhTourDTO loaiHinhTourDTO, Model model) {
        LoaiHinhTourBUS loaiHinhTourBUS = new LoaiHinhTourBUS();
        ArrayList<LoaiHinhTourDTO> loaiHinhTourDTOs = loaiHinhTourBUS.getLoaiHinhTourDTOs();
        if (loaiHinhTourBUS.suaLoaiHinhTour(loaiHinhTourDTO.getMaLoai(), loaiHinhTourDTO.getTenLoai())) {
            model.addAttribute("loaiHinhTourDTOs", loaiHinhTourDTOs);
            return "loaihinhtour";
        }
        model.addAttribute("message", "false");
        model.addAttribute("loaiHinhTourDTOs", loaiHinhTourDTOs);
        return "loaihinhtour";
    }

    public String capPhatId(){
        Utils utils = new Utils();
        return utils.initMaLoai();
    }
}
