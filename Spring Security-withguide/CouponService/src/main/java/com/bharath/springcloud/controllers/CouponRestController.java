package com.bharath.springcloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bharath.springcloud.model.Coupon;
import com.bharath.springcloud.repo.CouponRepo;

@RestController
@RequestMapping("/couponapi")
@CrossOrigin
public class CouponRestController {

	@Autowired
	private CouponRepo repo;
	
	@PostMapping("/coupons")
	public Coupon create(@RequestBody Coupon coupon) {
		System.out.println(coupon);
		return repo.save(coupon);
	}
	
	
	@GetMapping("/coupons/{code}")
	public Coupon getCoupon(@PathVariable("code") String couponCode) {
		System.out.println(couponCode);
		return repo.findByCode(couponCode);
	}
	
}
