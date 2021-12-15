package com.bharath.springcloud.controllers;

import java.math.BigDecimal;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bharath.springcloud.dto.Coupon;
import com.bharath.springcloud.model.Product;
import com.bharath.springcloud.repo.ProductRepo;

@RestController
@RequestMapping("productapi")
public class ProductController {

	@Autowired
	private ProductRepo repo;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${couponService.url}")
	private String couponUrl;

	@PostMapping("products")
	public Product create(@RequestBody Product product) {
		//Coupon coupon = restTemplate.getForObject(couponUrl + product.getCouponCode(), Coupon.class);
		Coupon coupon = new Coupon(1,"SUPERSALE",new BigDecimal(80),"12/12/2021");
		if (coupon != null) {
			product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		}
		return repo.save(product);
		// return product;
	}

	@GetMapping("products/{id}")
	public Product getProduct(@PathVariable("id") long id) {

		return repo.findById(id).get();
	}

//	//@RequestMapping(value = "/products", method = RequestMethod.POST)
//
//	@PostMapping("/products")
//
//	public Product create(@RequestBody Product product) {
//
//	// Coupon coupon = restTemplate.getForObject(couponServiceUrl + product.getCouponCode(), Coupon.class);
//
//	// product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
//
//	String username = "";
//
//	String userpassword = "";
//
//	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//	if (principal instanceof UserDetails) {
//
//	username = ((UserDetails)principal).getUsername();
//
//	userpassword = ((UserDetails)principal).getPassword();
//
//	if ("".equals(userpassword) || userpassword == null) {
//
//	userpassword = getPasswordFromDB(username);
//
//	}
//
//	} else {
//
//	username = principal.toString();
//
//	userpassword = getPasswordFromDB(username);
//
//	}
//
//	//String plainCreds = username+":"+userpassword;
//
//	String plainCreds = "doug@bailey.com"+":"+"doug"; // could not find how to use current authentication
//
//	byte[] plainCredsBytes = plainCreds.getBytes();
//
//	byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
//
//	String base64Creds = new String(base64CredsBytes);
//
//	org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
//
//	headers.add("Authorization", "Basic " + base64Creds);
//
//	HttpEntity<String> request = new HttpEntity<String>(headers);
//
//	ResponseEntity<Coupon> response = restTemplate.exchange(couponServiceUrl + product.getCouponCode(), HttpMethod.GET, request, Coupon.class);
//
//	Coupon coupon = response.getBody();
//
//	product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
//
//	return productRepo.save(product);
//
//	}

}
