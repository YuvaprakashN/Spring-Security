package com.example.spring.security.microservice.product.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.security.microservice.product.model.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

}
