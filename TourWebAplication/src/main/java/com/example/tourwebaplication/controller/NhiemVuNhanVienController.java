package com.example.tourwebaplication.controller;

import com.example.tourwebaplication.model.BUS.DiaDiemThamQuanBUS;
import com.example.tourwebaplication.model.BUS.NhanVienBUS;
import com.example.tourwebaplication.model.BUS.NhiemVuNhanVienBUS;
import com.example.tourwebaplication.model.DTO.NhiemVuNhanVienDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
public class NhiemVuNhanVienController {

    @RequestMapping(method = RequestMethod.POST, value = "/doan/thietlap/suaNhiemVuNhanVien")
    public String suaNhiemVuNhanVien(@RequestParam String maNhanVien, String maDoan, String tenNhiemVu, RedirectAttributes redirectAttributes) {
        NhiemVuNhanVienBUS nhiemVuNhanVienBUS = new NhiemVuNhanVienBUS();
        if(nhiemVuNhanVienBUS.update(maNhanVien, maDoan, tenNhiemVu)){
            redirectAttributes.addFlashAttribute("success", "Đã sửa.");
            return "redirect:/doan/thietlap?id=" + maDoan;
        }
        redirectAttributes.addFlashAttribute("error", "Lỗi sửa!");
        return "redirect:/doan/thietlap?id=" + maDoan;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/doan/thietlap/themNhiemVuNhanVien")
    public String themNhiemVuNhanVien(@RequestParam String maNhanVien, String maDoan, String nhiemVu, RedirectAttributes redirectAttributes) {
        NhiemVuNhanVienBUS nhiemVuNhanVienBUS = new NhiemVuNhanVienBUS();
        if(nhiemVuNhanVienBUS.add(maNhanVien, maDoan, nhiemVu)){
            redirectAttributes.addFlashAttribute("success", "Đã thêm.");
            return "redirect:/doan/thietlap?id=" + maDoan;
        }
        redirectAttributes.addFlashAttribute("error", "Lỗi thêm!");
        return "redirect:/doan/thietlap?id=" + maDoan;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/doan/thietlap/xoaNhiemVuNhanVien")
    public String xoaNhiemVuNhanVien(@RequestParam String maDoan, String maNhanVien, RedirectAttributes redirectAttributes) {
        NhiemVuNhanVienBUS nhiemVuNhanVienBUS = new NhiemVuNhanVienBUS();
        if(nhiemVuNhanVienBUS.delete(maNhanVien, maDoan)){
            redirectAttributes.addFlashAttribute("success", "Đã xóa.");
            return "redirect:/doan/thietlap?id=" + maDoan;
        }
        redirectAttributes.addFlashAttribute("error", "Lỗi xóa!");
        return "redirect:/doan/thietlap?id=" + maDoan;
    }
}
