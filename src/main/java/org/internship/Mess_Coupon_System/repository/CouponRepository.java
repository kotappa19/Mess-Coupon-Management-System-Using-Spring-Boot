package org.internship.Mess_Coupon_System.repository;


import org.internship.Mess_Coupon_System.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{
	
	Coupon findCouponByUsn(String usn);
	

}
