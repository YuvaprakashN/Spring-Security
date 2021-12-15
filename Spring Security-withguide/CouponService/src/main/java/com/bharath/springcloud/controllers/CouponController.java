package com.bharath.springcloud.controllers;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bharath.springcloud.model.Coupon;
import com.bharath.springcloud.repo.CouponRepo;

@Controller

public class CouponController {
	@Autowired
	private CouponRepo repo;

//	@GetMapping("/")
//	public String index() {
//		return "index";
//	}

	@GetMapping("/showCreateCoupon")
//	@PreAuthorize("hasRole('ADMIN')")
//	@Secured("ADMIN")
//	@RolesAllowed("ADMIN")
	public String showCreateCoupon() {
		return "createCoupon";
	}

	@PostMapping("/createCoupon")
	
	public String createCoupon(Coupon coupon) {
		repo.save(coupon);
		return "couponCreated";
	}
	
	
	@GetMapping("/showGetCoupon")
	public String showgetCoupon() {
		return "getCoupon";
	}
	
	@PostMapping("/getCoupon")
	//@PostAuthorize("returnObject.discount<60")
	public ModelAndView getCoupon(String couponCode) {
		ModelAndView mv = new ModelAndView("couponDetails");
		mv.addObject(repo.findByCode(couponCode));
		return mv;
	}
	
	
	
	
}
