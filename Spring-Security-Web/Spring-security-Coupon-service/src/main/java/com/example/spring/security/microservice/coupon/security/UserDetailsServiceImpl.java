package com.example.spring.security.microservice.coupon.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.spring.security.microservice.coupon.Repositary.UserRepo;
import com.example.spring.security.microservice.coupon.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("UserDetailsService");
		User user = repo.findByEmail(username);
		System.out.println("LoadUserByUserName: "+user.toString());
		if (user == null)
			throw new UsernameNotFoundException("Invalid User Email: " + username);
		org.springframework.security.core.userdetails.User springUser = new org.springframework.security.core.userdetails.User(
				user.getEmail(), user.getPassword(), user.getRoles());
		System.out.println("Spring USer"+springUser.toString());
		return springUser;
	}

}
