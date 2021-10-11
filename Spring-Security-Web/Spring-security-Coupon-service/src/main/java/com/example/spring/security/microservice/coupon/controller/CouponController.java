package com.example.spring.security.microservice.coupon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring.security.microservice.coupon.Repositary.CouponRepo;
import com.example.spring.security.microservice.coupon.model.Coupon;

@Controller
public class CouponController {

	@Autowired
	private CouponRepo repo;
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	

	@GetMapping("/showCreateCoupon")
	public String showCreateCoupon() {
		return "createCoupon";
	}
	
	@PostMapping("/saveCoupon")
	public String saveCoupon(Coupon coupon) {
		System.out.println("Saving Coupon:"+ coupon.toString());
		return "createReponse";
	}
	
	@GetMapping("/showGetCoupon")
	public String showGetCoupon() {
		return "getCoupon";
	}
	
	@PostMapping("/getCoupon")
	public ModelAndView getCoupon(String code) {
		System.out.println("Search code:"+code);
		ModelAndView mv=new ModelAndView("couponDetails");
		Coupon codeDetails = repo.findByCode(code);
		System.out.println("Code Details: "+codeDetails);
		mv.addObject("coupon",codeDetails);
		return mv;
	}
}
