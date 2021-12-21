package com.example.tourwebaplication.controller;

import com.example.tourwebaplication.model.BUS.*;
import com.example.tourwebaplication.model.DTO.DiaDiemDTO;
import com.example.tourwebaplication.model.DTO.DoanDuLichDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class HomeController {
    @RequestMapping(method = RequestMethod.GET, value = {"/index", "/"})
    public String dashboard(Model model) {
        TourBUS tourBUS = new TourBUS();
        int soTour = tourBUS.getTourDTOS().size();
        DoanDuLichBUS doanDuLichBUS = new DoanDuLichBUS();
        ArrayList<DoanDuLichDTO> doanDuLichDTOS = doanDuLichBUS.getDoanDuLichDTOs();
        int soDoan = doanDuLichDTOS.size();
        NhanVienBUS nhanVienBUS = new NhanVienBUS();
        int soNhanVien = nhanVienBUS.getNhanVienDTOs().size();
        KhachHangBUS khachHangBUS = new KhachHangBUS();
        int soKhach = khachHangBUS.getKhachHangDTOs().size();

        ArrayList<DoanDuLichDTO> doanDuLichDTOStmp = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = simpleDateFormat.format(new Date());
        for (DoanDuLichDTO doanDuLichDTO: doanDuLichDTOS){
            if(doanDuLichDTO.getNgayKhoiHanh().compareTo(dateNow) <= 0 && doanDuLichDTO.getNgayKetThuc().compareTo(dateNow) >= 0){
                doanDuLichDTOStmp.add(doanDuLichDTO);
            }
        }

        model.addAttribute("doanDuLichDTOStmp", doanDuLichDTOStmp);
        model.addAttribute("soTour", soTour);
        model.addAttribute("soDoan", soDoan);
        model.addAttribute("soNhanVien", soNhanVien);
        model.addAttribute("soKhach", soKhach);
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/dashboard/xoa")
    public String xoaDoan(@RequestParam String id, RedirectAttributes redirectAttributes){
        DoanDuLichBUS doanDuLichBUS = new DoanDuLichBUS();
        if(doanDuLichBUS.xoaDoan(id)){
            redirectAttributes.addFlashAttribute("success", "Xóa thành công.");
            return "redirect:/index";
        }
        redirectAttributes.addFlashAttribute("error", "Xóa thất bại!");
        return "redirect:/index";
    }

}
