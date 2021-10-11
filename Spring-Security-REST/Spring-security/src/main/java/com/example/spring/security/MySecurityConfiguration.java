package com.example.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MyAuthenticationProvider authenticationProvider;
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		InMemoryUserDetailsManager userDetailsService=new InMemoryUserDetailsManager();
//		UserDetails user = User.withUsername("yuva").password(passwordEncoder.encode("prakash")).authorities("read").build();
//		userDetailsService.createUser(user);
//		
//		auth.userDetailsService(userDetailsService);
//	
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authenticationProvider);
	
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic();
		http.authorizeRequests().anyRequest().authenticated();
		//http.authorizeRequests().antMatchers("/hello").authenticated().anyRequest().denyAll();//allows only /hello
		http.addFilterBefore(new MySecurityFilter(), BasicAuthenticationFilter.class);
		http.addFilterBefore(new MySecurityFilter1(), BasicAuthenticationFilter.class);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
