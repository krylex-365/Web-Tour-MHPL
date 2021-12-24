package com.example.tourwebaplication.controller;

import com.example.tourwebaplication.model.BUS.DiaDiemBUS;
import com.example.tourwebaplication.model.BUS.LoaiHinhTourBUS;
import com.example.tourwebaplication.model.BUS.Utils;
import com.example.tourwebaplication.model.DTO.DiaDiemDTO;
import com.example.tourwebaplication.model.DTO.LoaiHinhTourDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller

public class DiaDiemController {

    @RequestMapping(method = RequestMethod.GET, value = "/diadiem")
    public String getListDiaDiem(Model model) {
        DiaDiemBUS diaDiemBUS= new DiaDiemBUS();
        ArrayList<DiaDiemDTO> diaDiemDTOs= diaDiemBUS.getDiaDiemDTOs();
        model.addAttribute("diaDiemDTOs", diaDiemDTOs);
        model.addAttribute("maDiaDiem", capPhatId());
        return "diadiem";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/diadiem")
    public String getDiaDiem(@RequestParam("maDiaDiem") String id, Model model) {
        DiaDiemBUS diaDiemBUS= new DiaDiemBUS();
        ArrayList<DiaDiemDTO> diaDiemDTOs= diaDiemBUS.getDiaDiemDTOs();
       for (DiaDiemDTO diaDiemDTO: diaDiemDTOs){
           if (id.equals(diaDiemDTO.getMaDiaDiem())){
               model.addAttribute("diaDiemDTOs", diaDiemDTOs);
               model.addAttribute("diaDiemDTO", diaDiemDTO);
               return "diadiem";

           }
       }
       return "redirect:/diadiem";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/diadiem/sua")
    public String suaDiaDiem(@RequestParam String maDiaDiem, String tenDiaDiem, Model model, RedirectAttributes redirectAttributes) {
        DiaDiemBUS diaDiemBUS= new DiaDiemBUS();
        ArrayList<DiaDiemDTO> diaDiemDTOs= diaDiemBUS.getDiaDiemDTOs();
            if (!maDiaDiem.equals("")&&!tenDiaDiem.equals("")){
               if (diaDiemBUS.suaDiaDiem(maDiaDiem,tenDiaDiem)){
                   redirectAttributes.addFlashAttribute("success", "Sửa thành công.");
                   return "redirect:/diadiem";
               }
        }
        redirectAttributes.addFlashAttribute("error", "Sửa thất bại!");
        model.addAttribute("maDiaDiem", capPhatId());
        model.addAttribute("diaDiemDTOs", diaDiemDTOs);
        return "diadiem";

    }
    @RequestMapping(method = RequestMethod.POST, value = "/diadiem/them")
    public String themDiaDiem(@RequestParam String maDiaDiem, String tenDiaDiem, Model model, RedirectAttributes redirectAttributes) {
        DiaDiemBUS diaDiemBUS = new DiaDiemBUS();
        ArrayList<DiaDiemDTO> diaDiemDTOs = diaDiemBUS.getDiaDiemDTOs();
        if(maDiaDiem != null && !maDiaDiem.equals("")) {
            if (!maDiaDiem.equals("") && !tenDiaDiem.equals("")) {
                if (diaDiemBUS.themDiaDiem(maDiaDiem, tenDiaDiem)) {
                    redirectAttributes.addFlashAttribute("success", "Thêm thành công.");
                    return "redirect:/diadiem";
                }

            }
        }
        redirectAttributes.addFlashAttribute("maDiaDiem", capPhatId());
        redirectAttributes.addFlashAttribute("error","Thêm thất bại!");
        redirectAttributes.addFlashAttribute("diaDiemDTOs", diaDiemDTOs);
        return "redirect:/diadiem";

    }
    @RequestMapping(method = RequestMethod.POST, value = "/diadiem/xoa")
    public String xoaDiaDiem(@RequestParam("id") String id, Model model, RedirectAttributes redirectAttributes) {
        DiaDiemBUS diaDiemBUS= new DiaDiemBUS();
        ArrayList<DiaDiemDTO> diaDiemDTOs= diaDiemBUS.getDiaDiemDTOs();
        if(!id.equals("")) {
            if (diaDiemBUS.xoaDiaDiem(id)) {
                redirectAttributes.addFlashAttribute("success", "Xoá thành công.");
                return "redirect:/diadiem";
            }
        }
        redirectAttributes.addFlashAttribute("error", "Xoá thất bại");
        model.addAttribute("id", capPhatId());
        model.addAttribute("diaDiemDTOs", diaDiemDTOs);
        return "diadiem";
    }
    public String capPhatId(){
        Utils utils = new Utils();
        return utils.initMaDiaDiem();
    }
}
