package com.example.spring.security.microservice.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.spring.security.microservice.product.model.Coupon;
import com.example.spring.security.microservice.product.model.Product;
import com.example.spring.security.microservice.product.repositary.ProductRepo;

@RestController
@RequestMapping("productapi")
public class ProductRestController {
	
	@Autowired
	ProductRepo repo;
	@Value("${couponService.url}")
	private String couponServiceURL;
	
	@PostMapping("/products")
	public Product create(@RequestBody Product product) {
		System.out.println("P C"+product);
		Coupon coupon = new RestTemplate().getForEntity(couponServiceURL+"/coupons/"+product.getCouponCode(), Coupon.class).getBody();
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return repo.save(product);
	}

	@GetMapping("/products/{productId}")
	public Product getProduct(@PathVariable Long productId) {
		return repo.findById(productId).get();
	}
}
