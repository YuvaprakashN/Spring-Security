package com.bharath.springcloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManger;
	
//	@Override
//	public boolean login(String username, String password) {
//	
//		UserDetails userDetail = userDetailsService.loadUserByUsername(username);   //Gets userdetail from DB using our own userDetailsService
//		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetail,password,userDetail.getAuthorities());		//creating the token
//		authenticationManger.authenticate(token);		//we pass token to Authentication manager to check credentials and authenticate token
//		boolean result = token.isAuthenticated();
//		
//		if(result) {
//			SecurityContextHolder.getContext().setAuthentication(token); 	//If authenticated then we saving into security Context
//		}
//		
//		return result;
//	}
	
	

	@Override
	public boolean login(String userName, String password) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password,
				userDetails.getAuthorities());
		authenticationManger.authenticate(token);
		boolean result = token.isAuthenticated();

		if (result) {
			SecurityContextHolder.getContext().setAuthentication(token);
		}
		return result;
	}

}
