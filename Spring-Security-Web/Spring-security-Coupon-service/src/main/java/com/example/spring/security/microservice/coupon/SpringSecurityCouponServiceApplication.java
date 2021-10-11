package com.example.spring.security.microservice.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringSecurityCouponServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityCouponServiceApplication.class, args);
	}

	}
