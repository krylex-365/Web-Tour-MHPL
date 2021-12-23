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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
public class LoaiHinhTourController {

    @RequestMapping(method = RequestMethod.GET, value = "/loaihinhtour")
    public String getListLoaiHinhTuor(Model model) {
        LoaiHinhTourBUS loaiHinhTourBUS = new LoaiHinhTourBUS();
        ArrayList<LoaiHinhTourDTO> loaiHinhTourDTOs = loaiHinhTourBUS.getLoaiHinhTourDTOs();
        model.addAttribute("loaiHinhTourDTOs", loaiHinhTourDTOs);
        model.addAttribute("maLoai", capPhatId());
        return "loaihinhtour";
    }

    //Sua
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
        return "redirect:/loaihinhtour";
    }

    //submit sua
    @RequestMapping(method = RequestMethod.POST, value = "/loaihinhtour/sua")
    public String suaLoaiHinhTuor(@RequestParam String maLoai, String tenLoai, Model model, RedirectAttributes redirectAttributes) {
        LoaiHinhTourBUS loaiHinhTourBUS = new LoaiHinhTourBUS();
        ArrayList<LoaiHinhTourDTO> loaiHinhTourDTOs = loaiHinhTourBUS.getLoaiHinhTourDTOs();
        if(!maLoai.equals("") && !tenLoai.equals("")) {
            if (loaiHinhTourBUS.suaLoaiHinhTour(maLoai, tenLoai)) {
                redirectAttributes.addFlashAttribute("success", "Sửa thành công.");
                return "redirect:/loaihinhtour";
            }
        }
        redirectAttributes.addFlashAttribute("error", "Sửa thất bại!");
        model.addAttribute("maLoai", capPhatId());
        model.addAttribute("loaiHinhTourDTOs", loaiHinhTourDTOs);
        return "loaihinhtour";
    }

    //submit them
    @RequestMapping(method = RequestMethod.POST, value = "/loaihinhtour/them")
    public String themLoaiHinhTuor(@RequestParam String maLoai, String tenLoai, Model model, RedirectAttributes redirectAttributes) {

        LoaiHinhTourBUS loaiHinhTourBUS = new LoaiHinhTourBUS();
        ArrayList<LoaiHinhTourDTO> loaiHinhTourDTOs = loaiHinhTourBUS.getLoaiHinhTourDTOs();
        if(maLoai != null && !maLoai.equals("")) {
            if (!maLoai.equals("") && !tenLoai.equals("")) {
                if (loaiHinhTourBUS.themLoaiHinhTour(maLoai, tenLoai)) {
                    redirectAttributes.addFlashAttribute("success", "Thêm thành công.");
                    return "redirect:/loaihinhtour";
                }
            }
        }
        redirectAttributes.addFlashAttribute("maLoai", capPhatId());
        redirectAttributes.addFlashAttribute("error","Thêm thất bại!");
        redirectAttributes.addFlashAttribute("loaiHinhTourDTOs", loaiHinhTourDTOs);
        return "redirect:/loaihinhtour";
    }

    //Xoa
    @RequestMapping(method = RequestMethod.POST, value = "/loaihinhtour/xoa")
    public String xoaLoaiHinhTuor(@RequestParam("id") String id, Model model, RedirectAttributes redirectAttributes) {
        LoaiHinhTourBUS loaiHinhTourBUS = new LoaiHinhTourBUS();
        ArrayList<LoaiHinhTourDTO> loaiHinhTourDTOs = loaiHinhTourBUS.getLoaiHinhTourDTOs();
        if(!id.equals("")) {
            if (loaiHinhTourBUS.xoaLoaiHinhTour(id)) {
                redirectAttributes.addFlashAttribute("success", "Xoá thành công.");
                return "redirect:/loaihinhtour";
            }
        }
        redirectAttributes.addFlashAttribute("error", "Xoá thất bại!");
        model.addAttribute("maLoai", capPhatId());
        model.addAttribute("loaiHinhTourDTOs", loaiHinhTourDTOs);
        return "loaihinhtour";
    }

    public String capPhatId(){
        Utils utils = new Utils();
        return utils.initMaLoai();
    }
}
