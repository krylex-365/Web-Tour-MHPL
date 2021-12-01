package com.example.tourwebaplication.controller;

import com.example.tourwebaplication.model.BUS.*;
import com.example.tourwebaplication.model.DTO.*;
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
public class KhachHangController {

    @RequestMapping(method = RequestMethod.GET, value = "/khachhang")
    public String getListKhachHang(Model model) {
        //gan du lieu truoc khi gui cho view
        KhachHangBUS khachHangBUS = new KhachHangBUS();

        ArrayList<Data> list = new ArrayList<>();
        ArrayList<KhachHangDTO> khachHangDTOs = khachHangBUS.getKhachHangDTOs();

        for (KhachHangDTO khachHangDTO: khachHangDTOs){
            Data data = new Data();
            data.khachHangDTO = khachHangDTO;
            list.add(data);
        }

        //gui du lieu cho view
        model.addAttribute("list", list);
        return "khachhang";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/khachhang/xoa")
    public String xoaKhachHang(@RequestParam String id, RedirectAttributes redirectAttributes) {
        //gan du lieu truoc khi gui cho view
        KhachHangBUS khachhangBUS = new KhachHangBUS();
        if(khachhangBUS.deleteKhachHang(id)){
            redirectAttributes.addFlashAttribute("success", "Xóa thành công.");
            return "redirect:/khachhang";
        }
        redirectAttributes.addFlashAttribute("error", "Xóa thất bại!");
        return "redirect:/khachhang";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/khachhang/sua")
    public String layThongTinSuaKhachHang(@RequestParam String id, Model model, RedirectAttributes redirectAttributes) {
        //gan du lieu truoc khi gui cho view
        KhachHangBUS khachHangBUS = new KhachHangBUS();

        ArrayList<KhachHangDTO> khachHangDTOs = khachHangBUS.getKhachHangDTOs();
        for (KhachHangDTO khachHangDTO: khachHangDTOs){
            if (khachHangDTO.getMaKhachHang().equals(id)){
                model.addAttribute("khachHangDTO", khachHangDTO);
                return "khachhangUpdate";
            }
        }
        redirectAttributes.addFlashAttribute("error", "Không tìm thấy tour");
        return "redirect:/khachhang";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/khachhang/sua")
    public String suaKhachHang(@RequestParam String maKhachHang, String tenKhachHang, String gioiTinh, String ngaySinh,String CMND,String SDT,String MAIL,String diaChi,String quoctich,
                          RedirectAttributes redirectAttributes) {
        KhachHangBUS khachHangBUS = new KhachHangBUS();

        if(khachHangBUS.updateKhachHang(new KhachHangDTO(maKhachHang,tenKhachHang,gioiTinh,ngaySinh,CMND,SDT,MAIL,diaChi,quoctich)))
        {
            redirectAttributes.addFlashAttribute("success", "Sửa thành công.");
            return "redirect:/khachhang";
        }
        redirectAttributes.addFlashAttribute("error", "Không tìm thấy tour!");
        return "redirect:/khachhang";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/khachhang/them")
    public String layThongTinThemKhachHang(Model model) {

        model.addAttribute("maKhachHang",capPhatId());
        return "khachhangAdd";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/khachhang/them")
    public String themKhachHang(@RequestParam String maKhachHang, String tenKhachHang, String gioiTinh, String ngaySinh,String CMND,String SDT,String MAIL,String diaChi,String quocTich,
                           RedirectAttributes redirectAttributes) {
//        try {
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
//            Date tgBD = tgBD = simpleDateFormat.parse(ngayBD);
//            Date tgKT = simpleDateFormat.parse(ngayKT);
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            ngayBD = format.format(tgBD);
//            ngayKT = format.format(tgKT);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        KhachHangBUS khachHangBUS = new KhachHangBUS();
        if(khachHangBUS.addKhachHang(new KhachHangDTO(maKhachHang,tenKhachHang,gioiTinh,ngaySinh,CMND,SDT,MAIL,diaChi,quocTich))){
            redirectAttributes.addFlashAttribute("success", "Thêm thành công.");
            return "redirect:/khachhang";
        }

        redirectAttributes.addFlashAttribute("error", "Thêm thất bại!");
        return "redirect:/khachhang";
    }

    public String capPhatId(){
        Utils utils = new Utils();
        return utils.initMaKhachHang();
    }

    public class Data{
        public KhachHangDTO khachHangDTO;

    }

}
