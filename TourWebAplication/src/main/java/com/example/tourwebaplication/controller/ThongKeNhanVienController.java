package com.example.tourwebaplication.controller;

import com.example.tourwebaplication.model.BUS.DoanDuLichBUS;
import com.example.tourwebaplication.model.BUS.NhanVienBUS;
import com.example.tourwebaplication.model.BUS.NhiemVuNhanVienBUS;
import com.example.tourwebaplication.model.DTO.DoanDuLichDTO;
import com.example.tourwebaplication.model.DTO.NhanVienDTO;
import com.example.tourwebaplication.model.DTO.NhiemVuNhanVienDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.print.attribute.standard.MediaSizeName;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class ThongKeNhanVienController {
    @RequestMapping(method = RequestMethod.GET, value = "/thongkeNhanVien")
    public String getListTour(Model model,Date ngayBD,Date ngayKT) {
//        NhanVienBUS nhanVienBUS = new NhanVienBUS();
//        ArrayList<NhanVienDTO> nhanVienDTOS = nhanVienBUS.getNhanVienDTOs();
//




        ArrayList<Data> thongKeNhanVien = new ArrayList<>();
        //model.addAttribute("list", thongKeNhanVien);
        return "thongkeNhanvien";

    }

    @RequestMapping(method = RequestMethod.POST, value = "/thongkeNhanVien")
    public String thongKeNhanVien(@RequestParam Date ngayBD,Date ngayKT, Model model) {
        //gan du lieu truoc khi gui cho view
        if(ngayKT.after(ngayBD)){
            ArrayList<Data> thongKeNhanVien = thongKeNhanVien(ngayBD,ngayKT);
            model.addAttribute("success", "Thống kê thành công");
            model.addAttribute("list", thongKeNhanVien);
        }else{
            model.addAttribute("error", "Ngày bắt đầu không được lớn hơn ngày kết thúc");
        }
        return "thongkeNhanVien";
    }

    public ArrayList<Data> thongKeNhanVien(Date ngayBD,Date ngayKT){
        ArrayList<Data> list = new ArrayList<>();

        NhanVienBUS nhanVienBUS = new NhanVienBUS();
        ArrayList<NhanVienDTO> nhanVienDTOS = nhanVienBUS.getNhanVienDTOs();

        DoanDuLichBUS doanDuLichBUS = new DoanDuLichBUS();
        ArrayList<DoanDuLichDTO> doanDuLichSearchByDateDTOS = doanDuLichBUS.searchDoanByDate(ngayBD,ngayKT);



        NhiemVuNhanVienBUS nhiemVuNhanVienBUS = new NhiemVuNhanVienBUS();
        ArrayList<NhiemVuNhanVienDTO> nhiemVuNhanVienDTOS = nhiemVuNhanVienBUS.getList();

        //ArrayList<DoanDuLichDTO> tempDoan;

        int count = 0;
        int countele = 1;
        Data data;

        ArrayList<DoanDuLichDTO> tempDoan;

        for(DoanDuLichDTO a : doanDuLichSearchByDateDTOS){
            System.out.println(a.getMaDoan());
        }

        for(NhanVienDTO nv : nhanVienDTOS){
            System.out.println(countele++);
            data = new Data();
            data.nhanVienDTO = nv;
            tempDoan = new ArrayList<>();
            for(NhiemVuNhanVienDTO nvnv : nhiemVuNhanVienDTOS){


                    for(DoanDuLichDTO doan : doanDuLichSearchByDateDTOS){
                        if(nvnv.getMaDoan().equals(doan.getMaDoan())&&nv.getMaNhanVien().equals(nvnv.getMaNhanVien())){
                            tempDoan.add(doan);
                            count++;
                            //data.doanDuLichDTOS.add(doan);
                        }
                    }

            }
            data.doanDuLichDTOS = tempDoan;
            data.soLanDi = count;
            count = 0;
            list.add(data);
        }

        return list;
    }

    public class Data {
        public NhanVienDTO nhanVienDTO;
        public int soLanDi;
        public ArrayList<DoanDuLichDTO> doanDuLichDTOS;

    }

}