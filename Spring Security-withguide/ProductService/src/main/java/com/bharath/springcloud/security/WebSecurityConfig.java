package com.bharath.springcloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailServiceImpl userDetailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailService);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.httpBasic();
	http.authorizeRequests().mvcMatchers(HttpMethod.GET,"/productapi/products/{id:^[0-9]*$}").hasAnyRole("USER", "ADMIN")
	.mvcMatchers(HttpMethod.POST,"/productapi/products").hasRole("ADMIN").and().csrf().disable();
	}
	
}
