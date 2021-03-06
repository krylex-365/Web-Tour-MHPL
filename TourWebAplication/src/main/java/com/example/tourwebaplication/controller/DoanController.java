package com.example.tourwebaplication.controller;

import com.example.tourwebaplication.model.BUS.*;
import com.example.tourwebaplication.model.DAO.NhiemVuNhanVienDAO;
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

        if(maDoan != null && !maDoan.equals("")) {
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
            if (doanDuLichBUS.themDoan(new DoanDuLichDTO(maDoan, maTour, tenDoan, giaTour, ngayKH, ngayKT, chiTiet))) {
                redirectAttributes.addFlashAttribute("success", "Th??m th??nh c??ng.");
                return "redirect:/doan";
            }
        }
        redirectAttributes.addFlashAttribute("error", "Th??m th???t b???i!");
        return "redirect:/doan";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/doan/xoa")
    public String xoaDoan(@RequestParam String id, RedirectAttributes redirectAttributes){
        DoanDuLichBUS doanDuLichBUS = new DoanDuLichBUS();
        if(doanDuLichBUS.xoaDoan(id)){
            redirectAttributes.addFlashAttribute("success", "X??a th??nh c??ng.");
            return "redirect:/doan";
        }
        redirectAttributes.addFlashAttribute("error", "X??a th???t b???i!");
        return "redirect:/doan";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/doan/sua")
    public String hienThongTinSuaDoan(@RequestParam String id, Model model, RedirectAttributes redirectAttributes){

        DoanDuLichBUS doanDuLichBUS = new DoanDuLichBUS();
        ArrayList<DoanDuLichDTO> doanDuLichDTOs = doanDuLichBUS.getDoanDuLichDTOs();

        TourBUS tourBUS = new TourBUS();
        GiaTourBUS giaTourBUS = new GiaTourBUS();

        ArrayList<TourDTO> tourDTOs = tourBUS.getTourDTOS();
        ArrayList<Data> list = new ArrayList<>();
        ArrayList<GiaTourDTO> giaTourDTOs = giaTourBUS.getGiaTourDTOs();

        for (DoanDuLichDTO doanDuLichDTO: doanDuLichDTOs){
            if(doanDuLichDTO.getMaDoan().equals(id)){

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
                model.addAttribute("maDoan", id);
                model.addAttribute("doanDuLichDTO", doanDuLichDTO);
                return "doanUpdate";
            }
        }
        redirectAttributes.addFlashAttribute("error", "Kh??ng t??m th???y ??o??n!");
        return "redirect:/doan";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/doan/sua")
    public String suaDoan(@RequestParam String maDoan, String tenDoan, String maTour, String giaTour, String ngayKH,
                          String ngayKT, String chiTiet, RedirectAttributes redirectAttributes){

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
        TourBUS tourBUS = new TourBUS();
        ArrayList<TourDTO> tourDTOs = tourBUS.getTourDTOS();
        ArrayList<DoanDuLichDTO> doanDuLichDTOs = doanDuLichBUS.getDoanDuLichDTOs();
        for (DoanDuLichDTO doanDuLichDTO: doanDuLichDTOs){
            if (doanDuLichDTO.getMaDoan().equals(maDoan)){
                for (TourDTO tourDTO: tourDTOs){
                    if(tourDTO.getMaTour().equals(doanDuLichDTO.getMaTour())){
                        if(doanDuLichBUS.suaDoan(new DoanDuLichDTO(maDoan, maTour, tenDoan, giaTour, ngayKH, ngayKT, chiTiet), tourDTO.getMaTour())){
                            redirectAttributes.addFlashAttribute("success", "S???a th??nh c??ng.");
                            return "redirect:/doan";
                        }
                    }
                }
                break;
            }
        }

        redirectAttributes.addFlashAttribute("error", "S???a th???t b???i!");
        return "redirect:/doan";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/doan/thietlap")
    public String thietLapDoan(@RequestParam String id, Model model){
        //DoanDuLichBUS doanDuLichBUS = new DoanDuLichBUS();
        NhanVienBUS nhanVienBUS = new NhanVienBUS();
        NhiemVuNhanVienBUS nhiemVuNhanVienBUS = new NhiemVuNhanVienBUS();
        KhachHangBUS khachHangBUS = new KhachHangBUS();
        ChiTietDoanBUS chiTietDoanBUS = new ChiTietDoanBUS();
        ChiPhiBUS chiPhiBUS = new ChiPhiBUS();
        LoaiChiPhiBUS loaiChiPhiBUS = new LoaiChiPhiBUS();

        //ArrayList<DoanDuLichDTO> doanDuLichDTOs = doanDuLichBUS.getDoanDuLichDTOs();
        ArrayList<NhanVienDTO> nhanVienDTOs = nhanVienBUS.getNhanVienDTOs();
        ArrayList<NhiemVuNhanVienDTO> nhiemVuNhanVienDTOs = nhiemVuNhanVienBUS.nhiemVuNhanVienDAO.getList();
        ArrayList<KhachHangDTO> khachHangDTOs = khachHangBUS.getKhachHangDTOs();
        ArrayList<ChiTietDoanDTO> chiTietDoanDTOs = chiTietDoanBUS.getList();
        ArrayList<ChiPhiDTO> chiPhiDTOs = chiPhiBUS.getList();
        ArrayList<LoaiChiPhiDTO> loaiChiPhiDTOs = loaiChiPhiBUS.getLoaiChiPhiDTOs();

        ArrayList<DataNhanVien> dataNhanViens = new ArrayList<>();
        ArrayList<NhanVienDTO> dsNhanVienKhongThuocDoan = (ArrayList<NhanVienDTO>) nhanVienDTOs.clone();
        for (NhiemVuNhanVienDTO nhiemVuNhanVienDTO: nhiemVuNhanVienDTOs){
            if (nhiemVuNhanVienDTO.getMaDoan().equals(id)){
                DataNhanVien dataNhanVien = new DataNhanVien();
                dataNhanVien.nhiemVuNhanVienDTO = nhiemVuNhanVienDTO;
                for (NhanVienDTO nhanVienDTO: nhanVienDTOs){
                    if(nhanVienDTO.getMaNhanVien().equals(nhiemVuNhanVienDTO.getMaNhanVien())){
                        dataNhanVien.nhanVienDTO = nhanVienDTO;
                        dsNhanVienKhongThuocDoan.remove(nhanVienDTO);
                        break;
                    }
                }
                if(dataNhanVien.nhiemVuNhanVienDTO != null && dataNhanVien.nhanVienDTO != null) {
                    dataNhanViens.add(dataNhanVien);
                }
            }
        }

        ArrayList<KhachHangDTO> dsKhachHangKhongThuocDoan = (ArrayList<KhachHangDTO>) khachHangDTOs.clone();
        ArrayList<DataKhach> dataKhachs = new ArrayList<>();
        for (ChiTietDoanDTO chiTietDoanDTO: chiTietDoanDTOs){
            if (chiTietDoanDTO.getMaDoan().equals(id)){
                DataKhach dataKhach = new DataKhach();
                dataKhach.chiTietDoanDTO = chiTietDoanDTO;
                for (KhachHangDTO khachHangDTO: khachHangDTOs){
                    if(khachHangDTO.getMaKhachHang().equals(chiTietDoanDTO.getMaKhachHang())){
                        dataKhach.khachHangDTO = khachHangDTO;
                        dsKhachHangKhongThuocDoan.remove(khachHangDTO);
                        break;
                    }
                }
                if(dataKhach.khachHangDTO != null && dataKhach.chiTietDoanDTO != null) {
                    dataKhachs.add(dataKhach);
                }
            }
        }

        ArrayList<DataChiPhi> dataChiPhis = new ArrayList<>();
        for (ChiPhiDTO chiPhiDTO: chiPhiDTOs){
            if(chiPhiDTO.getMaDoan().equals(id)){
                DataChiPhi dataChiPhi = new DataChiPhi();
                dataChiPhi.chiPhiDTO = chiPhiDTO;
                for (LoaiChiPhiDTO loaiChiPhiDTO: loaiChiPhiDTOs){
                    if (loaiChiPhiDTO.getMaLoaiChiPhi().equals(chiPhiDTO.getMaLoaiChiPhi())){
                        dataChiPhi.loaiChiPhiDTO = loaiChiPhiDTO;
                    }
                }
                if(dataChiPhi.loaiChiPhiDTO != null && dataChiPhi.loaiChiPhiDTO != null) {
                    dataChiPhis.add(dataChiPhi);
                }
            }
        }

        model.addAttribute("maDoan", id);
        model.addAttribute("dsNhanVienKhongThuocDoan", dsNhanVienKhongThuocDoan);
        model.addAttribute("dataChiPhis", dataChiPhis);
        model.addAttribute("loaiChiPhiDTOs", loaiChiPhiDTOs);
        model.addAttribute("capPhatMaChiPhi", capPhatMaChiPhi());
        model.addAttribute("dataNhanViens", dataNhanViens);
        model.addAttribute("dataKhachs", dataKhachs);
        model.addAttribute("dsKhachHangKhongThuocDoan", dsKhachHangKhongThuocDoan);
        return "doanThietlap";
    }

    public String capPhatMaDoan(){
        Utils utils = new Utils();
        return utils.initMaDoanDuLich();
    }

    public String capPhatMaChiPhi(){
        Utils utils = new Utils();
        return utils.initMaChiPhi();
    }

    public class Data{
        public TourDTO tourDTO;
        public GiaTourDTO giaTourDTO;
    }

    public class DataNhanVien{
        public NhanVienDTO nhanVienDTO;
        public NhiemVuNhanVienDTO nhiemVuNhanVienDTO;
    }

    public class DataKhach{
        public KhachHangDTO khachHangDTO;
        public ChiTietDoanDTO chiTietDoanDTO;
    }

    public class DataChiPhi{
        public ChiPhiDTO chiPhiDTO;
        public LoaiChiPhiDTO loaiChiPhiDTO;
    }
}
