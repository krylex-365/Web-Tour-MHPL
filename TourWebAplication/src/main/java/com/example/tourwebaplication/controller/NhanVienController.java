package com.example.tourwebaplication.controller;

import com.example.tourwebaplication.model.BUS.NhanVienBUS;
import com.example.tourwebaplication.model.BUS.Utils;
import com.example.tourwebaplication.model.DTO.NhanVienDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
public class NhanVienController {

    @RequestMapping(method = RequestMethod.GET, value = "/nhanvien")
    public String getListNhanVien(Model model) {
        //gan du lieu truoc khi gui cho view
        NhanVienBUS nhanVienBUS = new NhanVienBUS();

        ArrayList<Data> list = new ArrayList<>();
        ArrayList<NhanVienDTO> nhanVienDTOs = nhanVienBUS.getNhanVienDTOs();

        for (NhanVienDTO nhanVienDTO : nhanVienDTOs){
            Data data = new Data();
            data.nhanVienDTO = nhanVienDTO;
            list.add(data);
        }

        //gui du lieu cho view
        model.addAttribute("list", list);
        return "nhanvien";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/nhanvien/xoa")
    public String xoaNhanVien(@RequestParam String id, RedirectAttributes redirectAttributes) {
        //gan du lieu truoc khi gui cho view
        NhanVienBUS nhanVienBUS = new NhanVienBUS();
        if(nhanVienBUS.delete(id)){
            redirectAttributes.addFlashAttribute("success", "Xóa thành công.");
            return "redirect:/nhanvien";
        }
        redirectAttributes.addFlashAttribute("error", "Xóa thất bại!");
        return "redirect:/nhanvien";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/nhanvien/sua")
    public String layThongTinSuaNhanVien(@RequestParam String id, Model model, RedirectAttributes redirectAttributes) {
        //gan du lieu truoc khi gui cho view
        NhanVienBUS nhanVienBUS = new NhanVienBUS();

        ArrayList<NhanVienDTO> nhanVienDTOs = nhanVienBUS.getNhanVienDTOs();
        for (NhanVienDTO nhanVienDTO: nhanVienDTOs){
            if (nhanVienDTO.getMaNhanVien().equals(id)){
                model.addAttribute("nhanVienDTO", nhanVienDTO);
                return "nhanvienUpdate";
            }
        }
        redirectAttributes.addFlashAttribute("error", "Không tìm thấy tour");
        return "redirect:/nhanvien";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/nhanvien/sua")
    public String suaNhanVien(@RequestParam String maNhanVien, String tenNhanVien, String gioiTinh, String ngaySinh,String SDT,String diaChi,
                          RedirectAttributes redirectAttributes) {
        NhanVienBUS khachHangBUS = new NhanVienBUS();

        if(khachHangBUS.update(new NhanVienDTO(maNhanVien,tenNhanVien,gioiTinh,ngaySinh,SDT,diaChi)))
        {
            redirectAttributes.addFlashAttribute("success", "Sửa thành công.");
            return "redirect:/nhanvien";
        }
        redirectAttributes.addFlashAttribute("error", "Không tìm thấy tour!");
        return "redirect:/nhanvien";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/nhanvien/them")
    public String layThongTinThemKhachHang(Model model) {

        model.addAttribute("maNhanVien",capPhatId());
        return "nhanvienAdd";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/nhanvien/them")
    public String themKhachHang(@RequestParam String maNhanVien, String tenNhanVien, String gioiTinh, String ngaySinh,String SDT,String diaChi,
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

        NhanVienBUS nhanVienBUS = new NhanVienBUS();
        if(nhanVienBUS.add(new NhanVienDTO(maNhanVien,tenNhanVien,gioiTinh,ngaySinh,SDT,diaChi))){
            redirectAttributes.addFlashAttribute("success", "Thêm thành công.");
            return "redirect:/nhanvien";
        }

        redirectAttributes.addFlashAttribute("error", "Thêm thất bại!");
        return "redirect:/nhanvien";
    }

    public String capPhatId(){
        Utils utils = new Utils();
        return utils.initMaNhanVien();
    }

    public class Data{
        public NhanVienDTO nhanVienDTO;

    }

}
