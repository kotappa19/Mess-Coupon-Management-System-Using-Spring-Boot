package org.internship.Mess_Coupon_System.controller;


import java.time.LocalDate;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.internship.Mess_Coupon_System.model.Coupon;
import org.internship.Mess_Coupon_System.model.Student;
import org.internship.Mess_Coupon_System.repository.CouponRepository;
import org.internship.Mess_Coupon_System.repository.StudentRepository;
import org.internship.Mess_Coupon_System.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CouponController {
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/showCoupons")
	public String viewCoupons(Model model) {
		model.addAttribute("listCoupons", couponService.getAllCoupons());
		return "coupons";
	}
	
	@GetMapping("/showNewCouponForm")
	public String showNewCouponForm(Model model) {
			Coupon coupons = new Coupon();
			List<String> usns = couponService.getAllUsns();
			LocalDate currentDate = LocalDate.now();
			LocalDate lastDayOfMonth = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), currentDate.lengthOfMonth());
			long daysBetween = ChronoUnit.DAYS.between(currentDate, lastDayOfMonth);
			int max = (int) daysBetween; 
			model.addAttribute("usns", usns);
			model.addAttribute("coupons", coupons);
			model.addAttribute("max", max);
			return "new-coupon";
	}
	
	@PostMapping("/saveCoupon")
	public String saveCoupon(@ModelAttribute("coupons") Coupon coupon, Model model) {
		Coupon coupon2 = couponRepository.findCouponByUsn(coupon.getUsn());
		 LocalDate currentDate = LocalDate.now();
		 List<String> usns = couponService.getAllUsns();
		 LocalDate lastDayOfMonth = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), currentDate.lengthOfMonth());
		long daysBetween = ChronoUnit.DAYS.between(currentDate, lastDayOfMonth);
		int max = (int) daysBetween; 
		 model.addAttribute("usns", usns);
		 model.addAttribute("max", max);
		 String error = "Coupon is already present with this USN";
		 LocalDate creationDate = coupon2.getStartDate();
		  long days =  ChronoUnit.DAYS.between(creationDate, currentDate);
		  int daysSinceCreation = (int) days;
		  if(coupon2 != null && daysSinceCreation >= coupon2.getDays())
		  {
			  coupon2.setStartDate(currentDate);
			  couponService.saveCoupon(coupon);
				return "redirect:/showCoupons";
		}
		if(coupon2 != null) {
			  
			  if(daysSinceCreation < coupon2.getDays()) {
				  model.addAttribute("error", error);
				  return "new-coupon";
			  }
			  model.addAttribute("error", error);
			  return "new-coupon";
		}
		else {
				couponService.saveCoupon(coupon);
				return "redirect:/showCoupons";
		}
	}
	
	@GetMapping("/deleteCoupon/{id}")
	public String deleteCoupon(@PathVariable(value = "id") Long id) {
		this.couponService.deleteCouponById(id);
		return "redirect:/showCoupons";
	}


}
