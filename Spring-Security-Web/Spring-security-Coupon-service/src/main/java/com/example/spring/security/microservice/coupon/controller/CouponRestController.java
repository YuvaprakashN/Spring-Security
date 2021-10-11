package com.example.spring.security.microservice.coupon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.security.microservice.coupon.Repositary.CouponRepo;
import com.example.spring.security.microservice.coupon.model.Coupon;

@RestController
@RequestMapping("/couponapi")
public class CouponRestController {

	@Autowired
	private CouponRepo repo;
	
	@PostMapping("/coupons")
	public Coupon create(@RequestBody Coupon coupon)
	{
		System.out.println("Create Coupon: "+coupon.getCode());
		return repo.save(coupon);
	}
	
	@GetMapping("/coupons/{code}")
	public Coupon getCoupon(@PathVariable String code,HttpServletRequest request)
	{
		System.out.println(request.getRequestURI());
		System.out.println("Get Coupon: "+code);
		return repo.findByCode(code);
	}
	
	
}
