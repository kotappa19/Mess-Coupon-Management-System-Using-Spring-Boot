package org.internship.Mess_Coupon_System.service;

import java.time.LocalDate;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.internship.Mess_Coupon_System.model.Coupon;
import org.internship.Mess_Coupon_System.model.Student;
import org.internship.Mess_Coupon_System.repository.CouponRepository;
import org.internship.Mess_Coupon_System.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class CouponServiceImplementation implements CouponService{

	
	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	@Override
	public List<Coupon> getAllCoupons() {
		return couponRepository.findAll();
	}
	@Override
	public Coupon saveCoupon(Coupon coupon) {
		Coupon coupon2 = couponRepository.findCouponByUsn(coupon.getUsn());
		if(coupon2 != null) {
			coupon2.setDays(coupon.getDays());
			coupon2.setPrice(coupon.getDays()*100.00);
			return couponRepository.save(coupon2);
		}
		coupon.setPrice(coupon.getDays()*100.00);
		return couponRepository.save(coupon);
	}
	
	@Override
	public List<String> getAllUsns() {
		List<Student> students = studentRepository.findAll();
		List<String> usns = new ArrayList<>();
		for (Student student : students) {
			usns.add(student.getUsn());
		}
		return usns;
	}
	@Override
	public Coupon getCouponById(Long id) {
		Optional<Coupon> optional = couponRepository.findById(id);
		Coupon coupon = null;
		if(optional.isPresent()) {
			coupon = optional.get();
		} else {
			throw new RuntimeException("Coupon not found");
		}
		return coupon;
	}
	@Override
	public void deleteCouponById(Long id) {
		this.couponRepository.deleteById(id);
		
	}


}
