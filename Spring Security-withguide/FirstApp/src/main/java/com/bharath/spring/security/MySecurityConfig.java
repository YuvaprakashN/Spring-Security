package com.bharath.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEnoder;
	
	@Autowired
	private MyAuthenticationProvider authenticationProvider;
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		BCryptPasswordEncoder passwordEnoder = new BCryptPasswordEncoder();
//		InMemoryUserDetailsManager userDetailService = new InMemoryUserDetailsManager();
//		 UserDetails user = User.withUsername("tom").password(passwordEnoder.encode("cruise")).authorities("read").build();
//		
//		userDetailService.createUser(user);
//		 
//		//auth.userDetailsService(userDetailService).passwordEncoder(passwordEnoder);
//		auth.userDetailsService(userDetailService);	//If we define BCryptPasswordEncoder bean then authenticationmanagerBuilder gets that passwordEncoder bean
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//http.httpBasic();
		http.httpBasic();
		//http.authorizeRequests().anyRequest().authenticated();
		//http.authorizeRequests().antMatchers("/hello").authenticated().anyRequest().permitAll();
		http.authorizeRequests().antMatchers("/hello").authenticated();
		http.addFilterBefore(new MySecurityFilter(), BasicAuthenticationFilter.class);
	}
}
