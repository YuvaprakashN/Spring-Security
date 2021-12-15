package com.bharath.spring.security;

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
		String userNAme = authentication.getName();
		String password = authentication.getCredentials().toString();
		if("tom".equals(userNAme)&&"cruise".equals(password)) {
			return new UsernamePasswordAuthenticationToken(userNAme, password,Arrays.asList());
			
		}
		throw new BadCredentialsException("UserNAme password mismatch");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
