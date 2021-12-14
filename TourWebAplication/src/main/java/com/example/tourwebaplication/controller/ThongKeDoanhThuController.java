package com.example.tourwebaplication.controller;

import com.example.tourwebaplication.model.BUS.*;
import com.example.tourwebaplication.model.DTO.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class ThongKeDoanhThuController {
    @RequestMapping(method = RequestMethod.GET, value = "/thongkeDoanhthu")
    public String getListTour(Model model) {
        ArrayList<thongkeTour> thongkeTours = listThongKeTour();
        model.addAttribute("thongkeTours", thongkeTours);
        return "thongkeDoanhthu";

    }

    @RequestMapping(method = RequestMethod.POST, value = "/thongkeDoanhthu/thongketheotg")
    public String thongketheoTG(@RequestParam String NgayBD, String NgayKT, Model model) throws ParseException {
        ArrayList<thongkeTour> listTour = listThongKeTour();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date tgBD = simpleDateFormat.parse(NgayBD);
        Date tgKT = simpleDateFormat.parse(NgayKT);
        ArrayList<thongkeTour> thongkeTours = new ArrayList<>();
        for (thongkeTour tkt : listTour) {
            for (int i = 0; i < tkt.listDoan.size(); i++) {
                Date tgDoanKH = simpleDateFormat1.parse(tkt.listDoan.get(i).doanDuLichDTO.getNgayKhoiHanh());
                Date tgDoanKT = simpleDateFormat1.parse(tkt.listDoan.get(i).doanDuLichDTO.getNgayKetThuc());
                if (tgDoanKH.after(tgBD) || tgDoanKH.equals(tgBD) &&
                        tgDoanKT.before(tgDoanKT) || tgDoanKT.equals(tgKT)) {
                    thongkeTours.add(tkt);
                    break;
                }
                model.addAttribute("thongkeTours", thongkeTours);
            }
        }

        return "thongkeDoanhthu";
    }


    public thongkeTour getTour(String matour) {
        thongkeTour tkt = new thongkeTour();
        TourBUS tourBUS = new TourBUS();
        ArrayList<TourDTO> tourDTOS = tourBUS.getTourDTOS();
        DoanDuLichBUS doanDuLichBUS = new DoanDuLichBUS();
        tkt.listDoan = new ArrayList<>();
        ChiTietDoanBUS chiTietDoanBUS = new ChiTietDoanBUS();
        ArrayList<ChiTietDoanDTO> chiTietDoanDTOS = chiTietDoanBUS.getList();
        ChiPhiBUS chiPhiBUS = new ChiPhiBUS();
        ArrayList<ChiPhiDTO> chiPhiDTOS = chiPhiBUS.getChiPhiDTOS();
        for (TourDTO tourDTO : tourDTOS) {
            if (tourDTO.getMaTour().equals(matour)) {
                ArrayList<DoanDuLichDTO> doanDuLichDTOS = doanDuLichBUS.searchDoanDuLichByMaTour(tourDTO.getMaTour());

                tkt.tourDTO = tourDTO;
                int sodoan = 0;
                long tongchiphi = 0;
                double tongdoanhthu = 0;
                thongkeDoan tkd;
                for (DoanDuLichDTO doanDuLichDTO : doanDuLichDTOS) {
                    sodoan++;

                    long tongCP = 0;
                    int sokhach = 0;
                    for (ChiPhiDTO chiPhiDTO : chiPhiDTOS) {
                        if (doanDuLichDTO.getMaDoan().equals(chiPhiDTO.getMaDoan())) {
                            tongCP += Long.parseLong(chiPhiDTO.getSoTien());
                        }
                    }
                    tongchiphi += tongCP;
                    for (ChiTietDoanDTO chiTietDoanDTO : chiTietDoanDTOS) {
                        if (doanDuLichDTO.getMaDoan().equals(chiTietDoanDTO.getMaDoan())) {
                            sokhach++;
                        }
                    }
                    double tienNhan = Double.parseDouble(doanDuLichDTO.getGiaTour()) * sokhach;
                    double Doanhthu = tienNhan - tongCP;
                    tongdoanhthu += Doanhthu;
                    tkt.TongDoanhThu = tongdoanhthu;
                    tkt.TongChiPhi = tongchiphi;
                    tkt.SoDoan = sodoan;

                    tkd = getDoan(doanDuLichDTO.getMaDoan());
                    tkt.listDoan.add(tkd);
                }
            }
        }
        return tkt;
    }

    public thongkeDoan getDoan(String madoan) {
        thongkeDoan tkd = new thongkeDoan();
        DoanDuLichBUS doanDuLichBUS = new DoanDuLichBUS();
        ArrayList<DoanDuLichDTO> doanDuLichDTOS = doanDuLichBUS.getDoanDuLichDTOs();
        ChiTietDoanBUS chiTietDoanBUS = new ChiTietDoanBUS();
        ArrayList<ChiTietDoanDTO> chiTietDoanDTOS = chiTietDoanBUS.getList();
        ChiPhiBUS chiPhiBUS = new ChiPhiBUS();
        ArrayList<ChiPhiDTO> chiPhiDTOS = chiPhiBUS.getChiPhiDTOS();
        for (DoanDuLichDTO doanDuLichDTO : doanDuLichDTOS) {
            if (doanDuLichDTO.getMaDoan().equals(madoan)) {
                long tongCP = 0;
                int sokhach = 0;
                for (ChiPhiDTO chiPhiDTO : chiPhiDTOS) {
                    if (doanDuLichDTO.getMaDoan().equals(chiPhiDTO.getMaDoan())) {
                        tongCP += Long.parseLong(chiPhiDTO.getSoTien());
                    }
                }
                for (ChiTietDoanDTO chiTietDoanDTO : chiTietDoanDTOS) {
                    if (doanDuLichDTO.getMaDoan().equals(chiTietDoanDTO.getMaDoan())) {
                        sokhach++;
                    }
                }
                double tienNhan = Double.parseDouble(doanDuLichDTO.getGiaTour()) * sokhach;
                double Doanhthu = tienNhan - tongCP;
                tkd.doanDuLichDTO = doanDuLichDTO;
                tkd.SoKhach = sokhach;
                tkd.ChiPhi = tongCP;
                tkd.DoanhThu = Doanhthu;
            }
        }
        return tkd;
    }

    public ArrayList<thongkeTour> listThongKeTour() {
        ArrayList<thongkeTour> thongkeTours = new ArrayList<>();
        TourBUS tourBUS = new TourBUS();
        ArrayList<TourDTO> tourDTOS = tourBUS.getTourDTOS();

        for (TourDTO tourDTO : tourDTOS) {
            thongkeTours.add(getTour(tourDTO.getMaTour()));
        }
        return thongkeTours;
    }

    public class thongkeTour {
        public TourDTO tourDTO;
        public int SoDoan;
        public long TongChiPhi;
        public Double TongDoanhThu;
        public ArrayList<thongkeDoan> listDoan;

    }

    public class thongkeDoan {
        public DoanDuLichDTO doanDuLichDTO;
        public int SoKhach;
        public long ChiPhi;
        public Double DoanhThu;
    }
}