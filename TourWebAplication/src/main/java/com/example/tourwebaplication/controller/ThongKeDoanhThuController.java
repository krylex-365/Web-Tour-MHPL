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
        ArrayList<thongkeTour> thongkeTours = new ArrayList<>();
        model.addAttribute("thongkeTours", thongkeTours);
        return "thongkeDoanhthu";

    }

    @RequestMapping(method = RequestMethod.POST, value = "/thongkeDoanhthu/thongketheotg")
    public String thongketheoTG(@RequestParam String NgayBD, String NgayKT, Model model) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date tgBD = simpleDateFormat.parse(NgayBD);
        Date tgKT = simpleDateFormat.parse(NgayKT);

        thongkeDoan tkd;
        TourBUS tourBUS = new TourBUS();
        ArrayList<TourDTO> tourDTOS = tourBUS.getTourDTOS();
        DoanDuLichBUS doanDuLichBUS = new DoanDuLichBUS();

        ChiTietDoanBUS chiTietDoanBUS = new ChiTietDoanBUS();
        ArrayList<ChiTietDoanDTO> chiTietDoanDTOS = chiTietDoanBUS.getList();
        ChiPhiBUS chiPhiBUS = new ChiPhiBUS();
        ArrayList<ChiPhiDTO> chiPhiDTOS = chiPhiBUS.getChiPhiDTOS();
        ArrayList<DoanDuLichDTO> doanDuLichDTOS = doanDuLichBUS.getDoanDuLichDTOs();
        ArrayList<thongkeTour> thongkeTours = new ArrayList<>();

        ThongKeDoanhThuController.thongkeTour tkt;
        Date tgDoanKT = null;
        for (TourDTO tourDTO : tourDTOS) {
            boolean tmp = false;
            tkt = new thongkeTour();
            tkt.listDoan = new ArrayList<>();
            int sodoan = 0;
            long tongchiphiTour = 0;
            double tongdoanhthuTour = 0;
            long tongCPD = 0;
            int sokhach = 0;
            double DoanhthuD = 0;
            for (DoanDuLichDTO doanDuLichDTO : doanDuLichDTOS) {
                try
                {
                    tgDoanKT = simpleDateFormat1.parse(doanDuLichDTO.getNgayKetThuc());
                }
                catch (ParseException ex)
                {
                    System.out.println ("Lỗi format String to Date!!" + ex.getMessage ());
                }
                if ((tgDoanKT.after(tgBD) || tgDoanKT.equals(tgBD)) &&
                        (tgDoanKT.before(tgKT) || tgDoanKT.equals(tgKT))) {
                    if (doanDuLichDTO.getMaTour().equals(tourDTO.getMaTour())) {
                        tkt.tourDTO = tourDTO;
                        sodoan++;
                        //tính doanh thu đoàn
                        tkd = new thongkeDoan();
                        tkd.doanDuLichDTO = doanDuLichDTO;
                        for (ChiPhiDTO chiPhiDTO : chiPhiDTOS) {
                            if (doanDuLichDTO.getMaDoan().equals(chiPhiDTO.getMaDoan())) {
                                tongCPD += Long.parseLong(chiPhiDTO.getSoTien());
                            }
                        }
                        for (ChiTietDoanDTO chiTietDoanDTO : chiTietDoanDTOS) {
                            if (doanDuLichDTO.getMaDoan().equals(chiTietDoanDTO.getMaDoan())) {
                                sokhach++;
                            }
                        }
                        double tienNhan = Double.parseDouble(doanDuLichDTO.getGiaTour()) * sokhach;
                        DoanhthuD = tienNhan - tongCPD;
                        tkd.SoKhach = sokhach;
                        tkd.DoanhThu = DoanhthuD;
                        tkd.ChiPhi = tongCPD;
                        // tour
                        tkt.listDoan.add(tkd);
                        tongdoanhthuTour += DoanhthuD;
                        tongchiphiTour += tongCPD;
                        tmp = true;
                    }
                }
            }
            if (tmp == true) {
                tkt.SoDoan = sodoan;
                tkt.TongDoanhThu = tongdoanhthuTour;
                tkt.TongChiPhi = tongchiphiTour;
                thongkeTours.add(tkt);
            }
            tmp = false;
        }
        model.addAttribute("thongkeTours", thongkeTours);
        return "thongkeDoanhthu";
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