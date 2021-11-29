package com.example.tourwebaplication.controller;

import com.example.tourwebaplication.model.BUS.DiaDiemThamQuanBUS;
import com.example.tourwebaplication.model.BUS.GiaTourBUS;
import com.example.tourwebaplication.model.DTO.DiaDiemDTO;
import com.example.tourwebaplication.model.DTO.DiaDiemThamQuanDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
public class DiaDiemThamQuanController {

    @RequestMapping(method = RequestMethod.POST, value = "/tour/thietlap/xoaDiaDiemThamQuan")
    public String xoaDiaDiemThamQuan(@RequestParam String maTour, String maDiaDiem, int thuTu, RedirectAttributes redirectAttributes) {
        DiaDiemThamQuanBUS diaDiemThamQuanBUS = new DiaDiemThamQuanBUS();
        if(diaDiemThamQuanBUS.xoaDDiemTQuan(maTour, maDiaDiem, thuTu)){
            redirectAttributes.addFlashAttribute("success", "Đã xóa.");
            return "redirect:/tour/thietlap?id=" + maTour;
        }
        redirectAttributes.addFlashAttribute("error", "Lỗi xóa!");
        return "redirect:/tour/thietlap?id=" + maTour;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tour/thietlap/themDiaDiemThamQuan")
    public String themDiaDiemThamQuan(@RequestParam String maTour, String maDiaDiem, RedirectAttributes redirectAttributes) {
        DiaDiemThamQuanBUS diaDiemThamQuanBUS = new DiaDiemThamQuanBUS();
        ArrayList<DiaDiemThamQuanDTO> diaDiemThamQuanDTOs = diaDiemThamQuanBUS.searchDiaDiemThamQuanByMaTour(maTour);
        int thuTu = 0;
        for (DiaDiemThamQuanDTO diaDiemThamQuanDTO: diaDiemThamQuanDTOs){
            if(diaDiemThamQuanDTO.getThuTu() >= thuTu){
                thuTu = diaDiemThamQuanDTO.getThuTu() + 1;
            }
        }
        if(diaDiemThamQuanBUS.themDDiemTQuan(maTour, maDiaDiem, thuTu)){
            redirectAttributes.addFlashAttribute("success", "Đã thêm.");
            return "redirect:/tour/thietlap?id=" + maTour;
        }
        redirectAttributes.addFlashAttribute("error", "Lỗi thêm!");
        return "redirect:/tour/thietlap?id=" + maTour;
    }
}
