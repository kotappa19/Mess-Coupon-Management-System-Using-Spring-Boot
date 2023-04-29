package org.internship.Mess_Coupon_System.service;

import java.util.List;

import org.internship.Mess_Coupon_System.controller.dto.CouponDTO;
import org.internship.Mess_Coupon_System.model.Coupon;

public interface CouponService {
	List<Coupon> getAllCoupons();
	Coupon saveCoupon(Coupon coupon);
	List<String> getAllUsns();
	Coupon getCouponById(Long id);
	void deleteCouponById(Long id);
}
