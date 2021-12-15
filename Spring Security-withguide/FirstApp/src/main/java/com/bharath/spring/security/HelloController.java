package com.bharath.spring.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello")
	public String HelloWorld() {
		return "Spring Security";
				
	}
	
	@GetMapping("/bye")
	public String bye() {
		return "BYE bye";
				
	}
	
}
