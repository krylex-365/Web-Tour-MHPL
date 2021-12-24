package com.example.tourwebaplication.controller;

import com.example.tourwebaplication.model.DAO.ChiPhiDAO;
import com.example.tourwebaplication.model.DAO.DoanDuLichDAO;
import com.example.tourwebaplication.model.DAO.LoaiChiPhiDAO;
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
public class ThongKeChiPhiController
{
	 @RequestMapping (method = RequestMethod.GET, value = "/thongkeChiPhi")
	 public String getListDoan (Model model)
	 {
		  ArrayList<DoanModel> listDoan = new ArrayList<> ();
		  model.addAttribute ("listDoan", listDoan);
		  return "thongkeChiPhi";
		  
	 }
	 
	 @RequestMapping (method = RequestMethod.POST, value = "/thongkeChiPhi/thongketheotg")
	 public String thongketheoTG (@RequestParam String NgayBD, String NgayKT, Model model) throws ParseException
	 {
		  ArrayList<DoanModel> listDoan = new ArrayList<> ();
		  
		  DoanDuLichDAO doanDuLichDAO = new DoanDuLichDAO ();
		  ArrayList<DoanDuLichDTO> doanDuLichDTOS = doanDuLichDAO.getList ();
		  ChiPhiDAO chiphiDAO = new ChiPhiDAO ();
		  ArrayList<ChiPhiDTO> chiPhiDTOs = chiphiDAO.getList ();
		  LoaiChiPhiDAO loaiChiPhiDAO = new LoaiChiPhiDAO ();
		  ArrayList<LoaiChiPhiDTO> loaiChiPhiDTOS = loaiChiPhiDAO.getList ();
		  
		  SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("MM/dd/yyyy");
		  SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat ("yyyy-MM-dd");
		  Date tgBD = simpleDateFormat.parse (NgayBD);
		  Date tgKT = simpleDateFormat.parse (NgayKT);
		  Date dateDoan = null;


		  SimpleDateFormat myFormat = new SimpleDateFormat ("yyyy-MM-dd");
		  DoanModel m;
		  ChiPhiModel chiPhiModel;


		  if(tgBD.before(tgKT)){
			  for (DoanDuLichDTO doan : doanDuLichDTOS)
			  {
				  try
				  {
					  dateDoan = myFormat.parse (doan.getNgayKetThuc ());
				  }
				  catch (ParseException ex)
				  {
					  System.out.println ("Lỗi format String to Date!!" + ex.getMessage ());
				  }
				  if (dateDoan != null
						  && (dateDoan.after (tgBD) || dateDoan.equals (tgBD))
						  && (dateDoan.before (tgKT) || dateDoan.equals (tgKT)))
				  {
					  m = new DoanModel ();
					  m.doanDuLichDTO = doan;
					  m.chiPhiModels = new ArrayList<> ();
					  long tongCP = 0;
					  for (ChiPhiDTO chiPhi : chiPhiDTOs)
					  {
						  if (chiPhi.getMaDoan ().equals (doan.getMaDoan ()))
						  {
							  for (LoaiChiPhiDTO loaiChiPhi : loaiChiPhiDTOS)
							  {
								  if (loaiChiPhi.getMaLoaiChiPhi ().equals (chiPhi.getMaLoaiChiPhi ()))
								  {
									  chiPhiModel = new ChiPhiModel ();
									  chiPhiModel.chiphiDTO = chiPhi;
									  chiPhiModel.tenLoaiChiphi = loaiChiPhi.getTenLoai ();
									  m.chiPhiModels.add (chiPhiModel);
								  }
							  }
							  tongCP += Long.parseLong (chiPhi.getSoTien ());
							  m.tongChiPhi = tongCP;

						  }
					  }
					  listDoan.add (m);
				  }
			  }
			  model.addAttribute ("listDoan", listDoan);
			  model.addAttribute ("success", "Thống kê thành công");
		  }else{
			  model.addAttribute ("error", "Thời gian bắt đầu phải nhỏ hơn thời gian kết thúc");
		  }

		  model.addAttribute ("listDoan", listDoan);
		  return "thongkeChiPhi";
	 }
	 
	 public class DoanModel
	 {
		  public DoanDuLichDTO doanDuLichDTO;
		  public ArrayList<ChiPhiModel> chiPhiModels;
		  public double tongChiPhi;
	 }
	 
	 public class ChiPhiModel
	 {
		  public ChiPhiDTO chiphiDTO;
		  public String tenLoaiChiphi;
	 }
	 
}