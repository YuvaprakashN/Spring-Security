package com.example.spring.security.microservice.coupon.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.security.microservice.coupon.model.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Long> {

	Coupon findByCode(String code);
}
