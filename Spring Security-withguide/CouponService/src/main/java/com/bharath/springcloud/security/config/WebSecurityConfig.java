package com.bharath.springcloud.security.config;

import java.util.List;

import org.hibernate.type.TrueFalseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.bharath.springcloud.security.UserDetailServiceImpl;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true,jsr250Enabled = true,securedEnabled = true)
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

		http.authorizeRequests()
				.mvcMatchers(HttpMethod.GET, "/couponapi/coupons/{code:^[A-Z]*$}", "/index", 
						"/showGetCoupon","/getCoupon","/couponDetails").hasAnyRole("USER", "ADMIN")
				.mvcMatchers(HttpMethod.POST,"/getCoupon").hasAnyRole("ADMIN","USER")
				.mvcMatchers(HttpMethod.GET, "/showCreateCoupon","/createCoupon","/couponCreated").hasAnyRole("ADMIN")
				.mvcMatchers(HttpMethod.POST, "/couponapi/coupons","/createCoupon").hasRole("ADMIN")
				.mvcMatchers("/", "/login", "/logout", "/showReg", "/registerUser").permitAll()
				.and()
				.csrf().disable()
				.logout().logoutSuccessUrl("/");
		
//		http.csrf(csrfCustomiser -> {
//			csrfCustomiser.ignoringAntMatchers("/couponapi/coupons/**");
//			//RequestMatcher requestMatcher = new RegexRequestMatcher("/couponapi/coupons/{code:^[A-Z]*$}", "POST");
//			RequestMatcher  requestMatcher = new MvcRequestMatcher(new HandlerMappingIntrospector(), "/getCoupon");
//			 csrfCustomiser.ignoringRequestMatchers(requestMatcher);
//		});
		
//		http.cors(corsCustomiser -> {
//			CorsConfigurationSource configurationSource= request ->{		//CorsConfigurationSource- search google its function Interface
//				CorsConfiguration corsConfiguration=new CorsConfiguration();
//				corsConfiguration.setAllowedOrigins(List.of("localhost:3000"));		//Allowing list of orgins
//				corsConfiguration.setAllowedMethods(List.of("GET"));				//Itt allows onlt Http Get Request
//				return corsConfiguration;
//			};
//			corsCustomiser.configurationSource(configurationSource);
//			
//		});
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
