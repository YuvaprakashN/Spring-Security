package com.bharath.spring.security;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(new BCryptPasswordEncoder().encode("password"));
		System.out.println(new Pbkdf2PasswordEncoder().encode("password"));
		System.out.println(new SCryptPasswordEncoder().encode("password"));
		
		
		
		Map<String, PasswordEncoder> encoders= new HashMap<>();
		encoders.put("Bcrypt", new BCryptPasswordEncoder());
		encoders.put("Scrypt", new SCryptPasswordEncoder());
		encoders.put("pbkd", new Pbkdf2PasswordEncoder());
		String encodedPassword = new DelegatingPasswordEncoder("Bcrypt", encoders).encode("password");
		System.out.println(encodedPassword);
	}

}
