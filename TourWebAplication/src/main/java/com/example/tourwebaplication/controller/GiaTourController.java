package com.example.tourwebaplication.controller;

import com.example.tourwebaplication.model.BUS.DiaDiemThamQuanBUS;
import com.example.tourwebaplication.model.BUS.GiaTourBUS;
import com.example.tourwebaplication.model.DTO.DiaDiemThamQuanDTO;
import com.example.tourwebaplication.model.DTO.GiaTourDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class GiaTourController {

    @RequestMapping(method = RequestMethod.POST, value = "/tour/apdunggiatour")
    public String apDungGiaTour(@RequestParam String maTour, String maGia, RedirectAttributes redirectAttributes) {
        GiaTourBUS giaTourBUS = new GiaTourBUS();
        if(giaTourBUS.suaHienHanh(maGia, maTour)){
            redirectAttributes.addFlashAttribute("success", "Đã áp dụng.");
            return "redirect:/tour/thietlap?id=" + maTour;
        }
        redirectAttributes.addFlashAttribute("error", "Lỗi áp dụng!");
        return "redirect:/tour/thietlap?id=" + maTour;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tour/thietlap/suaGia")
    public String suaGia(@RequestParam String maTour, String maGia, String giaTour, String ngayBD, String ngayKT,
                         int hienHanh, RedirectAttributes redirectAttributes) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date tgBD = simpleDateFormat.parse(ngayBD);
            Date tgKT = simpleDateFormat.parse(ngayKT);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            ngayBD = format.format(tgBD);
            ngayKT = format.format(tgKT);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        GiaTourBUS giaTourBUS = new GiaTourBUS();

        if(giaTourBUS.suaGiaTour(maTour, maGia, giaTour, ngayBD, ngayKT, hienHanh)){
            redirectAttributes.addFlashAttribute("success", "Sửa thành công.");
            return "redirect:/tour/thietlap?id=" + maTour;
        }
        redirectAttributes.addFlashAttribute("error", "Sửa thất bại!");
        return "redirect:/tour/thietlap?id=" + maTour;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tour/thietlap/themGia")
    public String themGia(@RequestParam String maTour, String maGia, String giaTour, String ngayBD, String ngayKT,
                         RedirectAttributes redirectAttributes) {

        if(maGia != null && !maGia.equals("")) {

            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Date tgBD = simpleDateFormat.parse(ngayBD);
                Date tgKT = simpleDateFormat.parse(ngayKT);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                ngayBD = format.format(tgBD);
                ngayKT = format.format(tgKT);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            GiaTourBUS giaTourBUS = new GiaTourBUS();

            if (giaTourBUS.themGiaTour(maGia, maTour, giaTour, ngayBD, ngayKT)) {
                redirectAttributes.addFlashAttribute("success", "Thêm thành công.");
                return "redirect:/tour/thietlap?id=" + maTour;
            }
        }
        redirectAttributes.addFlashAttribute("error", "Thêm thất bại!");
        return "redirect:/tour/thietlap?id=" + maTour;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tour/thietlap/xoaGia")
    public String xoaGiaTour(@RequestParam String maTour, String maGia, int hienHanh, RedirectAttributes redirectAttributes) {
        GiaTourBUS giaTourBUS = new GiaTourBUS();
        if(hienHanh != 1 && giaTourBUS.xoaGiaTour(maTour, maGia)){
            redirectAttributes.addFlashAttribute("success", "Đã xóa.");
            return "redirect:/tour/thietlap?id=" + maTour;
        }
        redirectAttributes.addFlashAttribute("error", "Lỗi xóa!");
        return "redirect:/tour/thietlap?id=" + maTour;
    }

}
