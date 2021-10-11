package com.example.spring.security.microservice.coupon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring.security.microservice.coupon.Repositary.UserRepo;
import com.example.spring.security.microservice.coupon.model.User;
import com.example.spring.security.microservice.coupon.security.SecurityService;
import com.example.spring.security.microservice.coupon.security.SecurityServiceImpl;

@Controller
public class UserController {

	@Autowired
	private SecurityServiceImpl securityServiceImpl;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepo repo;
	
	@GetMapping("/")
	public String showLoginPage() {
		
		return "login";
	}
	
	@GetMapping("/showReg")
	public String showRegistrationPage() {
		return "registerUser";

	}
	
	@PostMapping("/registerUser")
	public String createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repo.save(user);
		return "login";
	}
	
	
	@PostMapping("/login")
	public String login(@RequestParam String username,@RequestParam String password) {
		
		boolean loginResponse = securityServiceImpl.login(username, password);
		if(loginResponse) {
			return "index";
		}
		
		return "login";
	}
	
	
}
