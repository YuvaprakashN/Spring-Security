package com.bharath.springcloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bharath.springcloud.model.User;
import com.bharath.springcloud.repo.UserRepo;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repo.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("User Email not Found: "+username);
		}
		org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRoles());
		
		return userDetail;
	}

	
}
