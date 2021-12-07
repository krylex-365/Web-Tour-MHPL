package com.example.tourwebaplication.controller;

import com.example.tourwebaplication.model.BUS.ChiPhiBUS;
import com.example.tourwebaplication.model.BUS.ChiTietDoanBUS;
import com.example.tourwebaplication.model.DTO.ChiPhiDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ChiPhiController {
    @RequestMapping(method = RequestMethod.POST, value = "/doan/thietlap/themChiPhi")
    public String themChiPhi(@RequestParam String maDoan, String maChiPhi, String maloaiChiPhi, String thanhTien,
                             String chiTiet, RedirectAttributes redirectAttributes) {
        ChiPhiBUS chiPhiBUS = new ChiPhiBUS();
        if(chiPhiBUS.themChiPhi(new ChiPhiDTO(maChiPhi, maDoan, maloaiChiPhi, thanhTien, chiTiet))){
            redirectAttributes.addFlashAttribute("success", "Đã thêm.");
            return "redirect:/doan/thietlap?id=" + maDoan;
        }
        redirectAttributes.addFlashAttribute("error", "Lỗi thêm!");
        return "redirect:/doan/thietlap?id=" + maDoan;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/doan/thietlap/suaChiPhi")
    public String suaChiPhi(@RequestParam String maDoan, String maChiPhi, String maLoaiChiPhi, String thanhTien,
                             String chiTiet, RedirectAttributes redirectAttributes) {
        ChiPhiBUS chiPhiBUS = new ChiPhiBUS();
        if(chiPhiBUS.suaChiPhi(new ChiPhiDTO(maChiPhi, maDoan, maLoaiChiPhi, thanhTien, chiTiet))){
            redirectAttributes.addFlashAttribute("success", "Đã sửa.");
            return "redirect:/doan/thietlap?id=" + maDoan;
        }
        redirectAttributes.addFlashAttribute("error", "Lỗi sửa!");
        return "redirect:/doan/thietlap?id=" + maDoan;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/doan/thietlap/xoaChiPhi")
    public String xoaChiPhi(@RequestParam String maDoan, String maChiPhi, RedirectAttributes redirectAttributes) {
        ChiPhiBUS chiPhiBUS = new ChiPhiBUS();
        if(chiPhiBUS.xoaChiPhi(maChiPhi)){
            redirectAttributes.addFlashAttribute("success", "Đã sửa.");
            return "redirect:/doan/thietlap?id=" + maDoan;
        }
        redirectAttributes.addFlashAttribute("error", "Lỗi sửa!");
        return "redirect:/doan/thietlap?id=" + maDoan;
    }
}
