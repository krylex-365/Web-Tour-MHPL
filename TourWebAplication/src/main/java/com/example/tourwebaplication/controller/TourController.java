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
public class TourController {

    @RequestMapping(method = RequestMethod.GET, value = "/tour")
    public String getListTuor(Model model) {
        //gan du lieu truoc khi gui cho view
        TourBUS tourBUS = new TourBUS();
        GiaTourBUS giaTourBUS = new GiaTourBUS();
        LoaiHinhTourBUS loaiHinhTourBUS = new LoaiHinhTourBUS();

        ArrayList<Data> list = new ArrayList<>();
        ArrayList<TourDTO> tourDTOs = tourBUS.getTourDTOS();
        ArrayList<GiaTourDTO> giaTourDTOs = giaTourBUS.getGiaTourDTOs();
        ArrayList<LoaiHinhTourDTO> loaiHinhTourDTOs = loaiHinhTourBUS.getLoaiHinhTourDTOs();
        for (TourDTO tourDTO: tourDTOs){
            Data data = new Data();
            data.tourDTO = tourDTO;
            for (GiaTourDTO giaTourDTO: giaTourDTOs){
                if(giaTourDTO.getMaTour().equals(tourDTO.getMaTour()) && giaTourDTO.getHienHanh() == 1){
                    data.giaTourDTO = giaTourDTO;
                    break;
                }
            }
            for (LoaiHinhTourDTO loaiHinhTourDTO: loaiHinhTourDTOs){
                if(loaiHinhTourDTO.getMaLoai().equals(tourDTO.getMaLoai())){
                    data.loaiHinhTourDTO = loaiHinhTourDTO;
                }
            }
            list.add(data);
        }

        //gui du lieu cho view
        model.addAttribute("list", list);
        return "tour";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tour/xoa")
    public String xoaTuor(@RequestParam String id, RedirectAttributes redirectAttributes) {
        //gan du lieu truoc khi gui cho view
        TourBUS tourBUS = new TourBUS();
        if(tourBUS.xoaTour(id)){
            redirectAttributes.addFlashAttribute("success", "Xóa thành công.");
            return "redirect:/tour";
        }
        redirectAttributes.addFlashAttribute("error", "Xóa thất bại!");
        return "redirect:/tour";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tour/sua")
    public String layThongTinSuaTuor(@RequestParam String id, Model model, RedirectAttributes redirectAttributes) {
        //gan du lieu truoc khi gui cho view
        TourBUS tourBUS = new TourBUS();
        LoaiHinhTourBUS loaiHinhTourBUS = new LoaiHinhTourBUS();
        GiaTourBUS giaTourBUS = new GiaTourBUS();

        ArrayList<TourDTO> tourDTOs = tourBUS.getTourDTOS();
        ArrayList<LoaiHinhTourDTO> loaiHinhTourDTOs = loaiHinhTourBUS.getLoaiHinhTourDTOs();
        for (TourDTO tourDTO: tourDTOs){
            if (tourDTO.getMaTour().equals(id)){
                model.addAttribute("loaiHinhTourDTOs", loaiHinhTourDTOs);
                model.addAttribute("tourDTO", tourDTO);
                return "tourUpdate";
            }
        }
        redirectAttributes.addFlashAttribute("error", "Không tìm thấy tour");
        return "redirect:/tour";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tour/sua")
    public String suaTuor(@RequestParam String maTour, String tenTour, String loaiHinh, String dacDiem,
                          RedirectAttributes redirectAttributes) {
        TourBUS tourBUS = new TourBUS();
        GiaTourBUS giaTourBUS = new GiaTourBUS();
        ArrayList<GiaTourDTO> giaTourDTOs = giaTourBUS.getGiaTourDTOs();
        ArrayList<TourDTO> tourDTOs = tourBUS.getTourDTOS();

        TourDTO tourDTO = new TourDTO();
        for (TourDTO tourDTO1: tourDTOs){
            if (tourDTO1.getMaTour().equals(maTour)){
                tourDTO = tourDTO1;
            }
        }

        GiaTourDTO giaTourDTO = new GiaTourDTO();
        for (GiaTourDTO giaTourDTO1: giaTourDTOs){
            if(giaTourDTO1.getMaTour().equals(tourDTO.getMaTour())){
                giaTourDTO = giaTourDTO1;
            }
        }

        if(tourBUS.suaTour(maTour, tenTour, dacDiem, tourDTO.getMaLoai(), loaiHinh, giaTourDTO.getMaGia(), giaTourDTO.getMaGia())){
            redirectAttributes.addFlashAttribute("success", "Sửa thành công.");
            return "redirect:/tour";
        }
        redirectAttributes.addFlashAttribute("error", "Không tìm thấy tour!");
        return "redirect:/tour";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tour/them")
    public String layThongTinThemTuor(Model model) {
        LoaiHinhTourBUS loaiHinhTourBUS = new LoaiHinhTourBUS();
        ArrayList<LoaiHinhTourDTO> loaiHinhTourDTOs = loaiHinhTourBUS.getLoaiHinhTourDTOs();

        model.addAttribute("loaiHinhTourDTOs", loaiHinhTourDTOs);
        model.addAttribute("maTour", capPhatId());
        return "tourAdd";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tour/them")
    public String themTour(@RequestParam String maTour, String tenTour, String loaiHinh, String dacDiem,
                           String giaTour, String ngayBD, String ngayKT, RedirectAttributes redirectAttributes) {
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

        TourBUS tourBUS = new TourBUS();
        if(tourBUS.themTour(maTour, loaiHinh, tenTour, dacDiem, giaTour, ngayBD, ngayKT)){
            redirectAttributes.addFlashAttribute("success", "Thêm thành công.");
            return "redirect:/tour";
        }

        redirectAttributes.addFlashAttribute("error", "Thêm thất bại!");
        return "redirect:/tour";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tour/thietlap")
    public String lapThongTinThietLap(@RequestParam String id, Model model) {
        DiaDiemThamQuanBUS diaDiemThamQuanBUS = new DiaDiemThamQuanBUS();
        GiaTourBUS giaTourBUS = new GiaTourBUS();
        DiaDiemBUS diaDiemBUS = new DiaDiemBUS();

        ArrayList<DiaDiemDTO> diaDiemDTOs = diaDiemBUS.getDiaDiemDTOs();
        ArrayList<DiaDiemThamQuanDTO> diaDiemThamQuanDTOs = diaDiemThamQuanBUS.searchDiaDiemThamQuanByMaTour(id);
        ArrayList<GiaTourDTO> giaTourDTOs = giaTourBUS.getGiaTourDTOs();
        ArrayList<GiaTourDTO> giaTourDTOstmp = new ArrayList<>();
        for (GiaTourDTO giaTourDTO: giaTourDTOs){
            if(giaTourDTO.getMaTour().equals(id)){
                giaTourDTOstmp.add(giaTourDTO);
            }
        }
        ArrayList<DataThietLap> dataThietLaps = new ArrayList<>();
        ArrayList<DiaDiemDTO> diaDiemDTOsTmp = new ArrayList<>();
        diaDiemDTOsTmp = (ArrayList<DiaDiemDTO>) diaDiemDTOs.clone();
        for (DiaDiemThamQuanDTO diaDiemThamQuanDTO: diaDiemThamQuanDTOs){
            DataThietLap dataThietLap = new DataThietLap();
            dataThietLap.diaDiemThamQuanDTO = diaDiemThamQuanDTO;
            for (DiaDiemDTO diaDiemDTO: diaDiemDTOs){
                if(diaDiemDTO.getMaDiaDiem().equals(diaDiemThamQuanDTO.getMaDiaDiem())){
                    dataThietLap.diaDiemDTO = diaDiemDTO;
                    diaDiemDTOsTmp.remove(diaDiemDTO);
                }
            }
            dataThietLaps.add(dataThietLap);
        }

        model.addAttribute("maTour", id);
        model.addAttribute("capPhatMaGia", capPhatIdGiaTour());
        model.addAttribute("giaTourDTOs", giaTourDTOstmp);
        model.addAttribute("dataThietLaps", dataThietLaps);
        model.addAttribute("diaDiemDTOs", diaDiemDTOsTmp);
        return "tourThietlap";
    }

    public String capPhatId(){
        Utils utils = new Utils();
        return utils.initMaTour("");
    }

    public String capPhatIdGiaTour(){
        Utils utils = new Utils();
        return utils.initMaGia("");
    }

    public class Data{
        public TourDTO tourDTO;
        public GiaTourDTO giaTourDTO;
        public LoaiHinhTourDTO loaiHinhTourDTO;
    }

    public class DataThietLap{
        public DiaDiemThamQuanDTO diaDiemThamQuanDTO;
        public DiaDiemDTO diaDiemDTO;
    }
}
