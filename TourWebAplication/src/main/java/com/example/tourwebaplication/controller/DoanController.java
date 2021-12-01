package com.example.tourwebaplication.controller;

import com.example.tourwebaplication.model.BUS.DoanDuLichBUS;
import com.example.tourwebaplication.model.BUS.GiaTourBUS;
import com.example.tourwebaplication.model.BUS.TourBUS;
import com.example.tourwebaplication.model.BUS.Utils;
import com.example.tourwebaplication.model.DTO.DoanDuLichDTO;
import com.example.tourwebaplication.model.DTO.GiaTourDTO;
import com.example.tourwebaplication.model.DTO.LoaiHinhTourDTO;
import com.example.tourwebaplication.model.DTO.TourDTO;
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
public class DoanController {

    @RequestMapping(method = RequestMethod.GET, value = "/doan")
    public String getListDoan(Model model){
        DoanDuLichBUS doanDuLichBUS = new DoanDuLichBUS();
        ArrayList<DoanDuLichDTO> doanDuLichDTOs = doanDuLichBUS.getDoanDuLichDTOs();
        model.addAttribute("doanDuLichDTOs", doanDuLichDTOs);
        return "doan";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/doan/them")
    public String trangThemDoan(Model model){
        model.addAttribute("maDoan", capPhatMaDoan());
        TourBUS tourBUS = new TourBUS();
        GiaTourBUS giaTourBUS = new GiaTourBUS();
        ArrayList<TourDTO> tourDTOs = tourBUS.getTourDTOS();

        ArrayList<Data> list = new ArrayList<>();
        ArrayList<GiaTourDTO> giaTourDTOs = giaTourBUS.getGiaTourDTOs();
        for (TourDTO tourDTO: tourDTOs){
            Data data = new Data();
            data.tourDTO = tourDTO;
            for (GiaTourDTO giaTourDTO: giaTourDTOs){
                if(giaTourDTO.getMaTour().equals(tourDTO.getMaTour()) && giaTourDTO.getHienHanh() == 1){
                    data.giaTourDTO = giaTourDTO;
                    break;
                }
            }
            list.add(data);
        }

        model.addAttribute("list", list);
        model.addAttribute("tourDTOs", tourDTOs);
        return "doanAdd";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/doan/them")
    public String themDoan(@RequestParam String maDoan, String tenDoan, String maTour, String chiTiet, String giaTour,
                           String ngayKH, String ngayKT, RedirectAttributes redirectAttributes){

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date tgBD = simpleDateFormat.parse(ngayKH);
            Date tgKT = simpleDateFormat.parse(ngayKT);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            ngayKH = format.format(tgBD);
            ngayKT = format.format(tgKT);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DoanDuLichBUS doanDuLichBUS = new DoanDuLichBUS();
        if(doanDuLichBUS.themDoan(new DoanDuLichDTO(maDoan, maTour, tenDoan, giaTour, ngayKH, ngayKT, chiTiet))){
            redirectAttributes.addFlashAttribute("success", "Thêm thành công.");
            return "redirect:/doan";
        }
        redirectAttributes.addFlashAttribute("error", "Thêm thất bại!");
        return "redirect:/doan";
    }

    public String capPhatMaDoan(){
        Utils utils = new Utils();
        return utils.initMaDoanDuLich();
    }

    public class Data{
        public TourDTO tourDTO;
        public GiaTourDTO giaTourDTO;
    }
}
