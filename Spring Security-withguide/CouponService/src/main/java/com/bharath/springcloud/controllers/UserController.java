package com.bharath.springcloud.controllers;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bharath.springcloud.model.Role;
import com.bharath.springcloud.model.User;
import com.bharath.springcloud.repo.RoleRepo;
import com.bharath.springcloud.repo.UserRepo;
import com.bharath.springcloud.security.SecurityService;

@Controller
public class UserController {

	@Autowired
	private SecurityService securityService; 
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	@GetMapping("/")
	public String login() {
		return "login";
	}


	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@PostMapping("/login")
	public String loginAuthentication(String email,String password) {
		boolean result = securityService.login(email, password);
		System.out.println("RESULT: "+result);
		if(result)
			return "index";
		
		return "login";
	}
	
	
	@GetMapping("/showReg")
	public String showRegistrationPage() {
		return "registerUser";

	}

	@PostMapping("/registerUser")
	public String register(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		Role role = roleRepo.findById(2l).get();
		user.addRole(role);
		userRepo.save(user);
		return "login";
	}

}
