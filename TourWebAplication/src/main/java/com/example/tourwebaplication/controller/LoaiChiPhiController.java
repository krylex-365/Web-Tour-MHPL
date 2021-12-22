package com.example.tourwebaplication.controller;

import com.example.tourwebaplication.model.BUS.LoaiChiPhiBUS;
import com.example.tourwebaplication.model.BUS.Utils;
import com.example.tourwebaplication.model.DTO.LoaiChiPhiDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
public class LoaiChiPhiController {

    @RequestMapping(method = RequestMethod.GET, value = "/loaichiphi")
    public String getListLoaiChiPhi(Model model) {
        LoaiChiPhiBUS loaiChiPhiBUS = new LoaiChiPhiBUS();
        ArrayList<LoaiChiPhiDTO> loaiChiPhiDTOs = loaiChiPhiBUS.getLoaiChiPhiDTOs();
        model.addAttribute("loaiChiPhiDTOs", loaiChiPhiDTOs);
        model.addAttribute("maLoai", capPhatId());
        return "loaichiphi";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/loaichiphi")
    public String getLoaiChiPhi(@RequestParam("id") String id, Model model) {
        LoaiChiPhiBUS loaiChiPhiBUS = new LoaiChiPhiBUS();
        ArrayList<LoaiChiPhiDTO> loaiChiPhiDTOs = loaiChiPhiBUS.getLoaiChiPhiDTOs();
        for (LoaiChiPhiDTO loaiChiPhiDTO:loaiChiPhiDTOs){
            if (id.equals(loaiChiPhiDTO.getMaLoaiChiPhi())){
                model.addAttribute("loaiChiPhiDTO", loaiChiPhiDTO);
                model.addAttribute("loaiChiPhiDTOs", loaiChiPhiDTOs);
                return "loaichiphi";
            }
        }
        return "redirect:/loaichiphi";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/loaichiphi/sua")
    public String suaLoaiChiPhi(@RequestParam String maLoaiChiPhi, String tenLoaiChiPhi, Model model) {
        LoaiChiPhiBUS loaiChiPhiBUS = new LoaiChiPhiBUS();
        ArrayList<LoaiChiPhiDTO> loaiChiPhiDTOs = loaiChiPhiBUS.getLoaiChiPhiDTOs();
        if(!maLoaiChiPhi.equals("") && !tenLoaiChiPhi.equals("")) {
            if (loaiChiPhiBUS.suaLoaiChiPhi(maLoaiChiPhi,tenLoaiChiPhi,loaiChiPhiBUS.getLoaiChiPhiDTOs())) {
                return "redirect:/loaichiphi";
            }
        }
        model.addAttribute("maLoai", capPhatId());
        model.addAttribute("message","false");
        model.addAttribute("loaiChiPhiDTOs", loaiChiPhiDTOs);
        return "loaichiphi";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/loaichiphi/them")
    public String themLoaiChiPhi(@RequestParam String maLoaiChiPhi, String tenLoaiChiPhi, Model model, RedirectAttributes redirectAttributes) {
        LoaiChiPhiBUS loaiChiPhiBUS = new LoaiChiPhiBUS();
        ArrayList<LoaiChiPhiDTO> loaiChiPhiDTOs = loaiChiPhiBUS.getLoaiChiPhiDTOs();
        if(maLoaiChiPhi != null && !maLoaiChiPhi.equals("")) {
            if (!maLoaiChiPhi.equals("") && !tenLoaiChiPhi.equals("")) {
                if (loaiChiPhiBUS.themLoaiChiPhi(maLoaiChiPhi, tenLoaiChiPhi, loaiChiPhiBUS.getLoaiChiPhiDTOs())) {
                    return "redirect:/loaichiphi";
                }
            }
        }
        redirectAttributes.addFlashAttribute("maLoai", capPhatId());
        redirectAttributes.addFlashAttribute("message","false");
        redirectAttributes.addFlashAttribute("loaiChiPhiDTOs", loaiChiPhiDTOs);
        return "redirect:/loaichiphi";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/loaichiphi/xoa")
    public String xoaLoaiChiPhi(@RequestParam("id") String id, Model model) {
        LoaiChiPhiBUS loaiChiPhiBUS = new LoaiChiPhiBUS();
        ArrayList<LoaiChiPhiDTO> loaiChiPhiDTOs = loaiChiPhiBUS.getLoaiChiPhiDTOs();
        if (!id.equals("")) {
            if (loaiChiPhiBUS.xoaLoaiChiPhi(id,loaiChiPhiBUS.getLoaiChiPhiDTOs())) {
                return "redirect:/loaichiphi";
            }
        }
        model.addAttribute("maLoai", capPhatId());
        model.addAttribute("message","false");
        model.addAttribute("loaiChiPhiDTOs", loaiChiPhiDTOs);
        return "loaichiphi";
    }
    public String capPhatId(){
        Utils utils = new Utils();
        return utils.initMaLoaiChiPhi();
    }
}
