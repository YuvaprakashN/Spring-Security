package com.example.spring.security;

import java.util.Arrays;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
System.out.println("Cgecking Auth");
		String name = authentication.getName();
		String credentials = authentication.getCredentials().toString();
		if("yuva".equals(name)&& "brahma".equals(credentials))
			return new UsernamePasswordAuthenticationToken(name, credentials,Arrays.asList());
		else 
			throw new BadCredentialsException("Invalid Username and Password");
		
		
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		if(authentication.equals(UsernamePasswordAuthenticationToken.class))
		return true;
			return false;
	}

}
