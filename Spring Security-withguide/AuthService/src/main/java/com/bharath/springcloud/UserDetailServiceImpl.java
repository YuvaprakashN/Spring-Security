package com.bharath.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("LOAD BY USER NAME: "+username);
		User user = repo.findByEmail(username);
		System.out.println("USER DAT: "+user.toString());
		
		if(user==null) {
			throw new UsernameNotFoundException("User with email: "+username+" not present");
		}
		org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRoles());
		
		return userDetail;
	}

}
