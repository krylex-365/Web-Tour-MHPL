package com.example.tourwebaplication.controller;

import com.example.tourwebaplication.model.BUS.ChiTietDoanBUS;
import com.example.tourwebaplication.model.BUS.NhiemVuNhanVienBUS;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ChiTietDoanControler {

    @RequestMapping(method = RequestMethod.POST, value = "/doan/thietlap/themChiTietDoan")
    public String themChiTietDoan(@RequestParam String maKhachHang, String maDoan, RedirectAttributes redirectAttributes) {
        ChiTietDoanBUS chiTietDoanBUS = new ChiTietDoanBUS();
        if(chiTietDoanBUS.add(maDoan, maKhachHang)){
            redirectAttributes.addFlashAttribute("success", "Đã thêm.");
            return "redirect:/doan/thietlap?id=" + maDoan;
        }
        redirectAttributes.addFlashAttribute("error", "Lỗi thêm!");
        return "redirect:/doan/thietlap?id=" + maDoan;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/doan/thietlap/xoaChiTietDoan")
    public String xoaChiTietDoan(@RequestParam String maDoan, String maKhachHang, RedirectAttributes redirectAttributes) {
        ChiTietDoanBUS chiTietDoanBUS = new ChiTietDoanBUS();
        if(chiTietDoanBUS.delete(maDoan, maKhachHang)){
            redirectAttributes.addFlashAttribute("success", "Đã xóa.");
            return "redirect:/doan/thietlap?id=" + maDoan;
        }
        redirectAttributes.addFlashAttribute("error", "Lỗi xóa!");
        return "redirect:/doan/thietlap?id=" + maDoan;
    }
}
