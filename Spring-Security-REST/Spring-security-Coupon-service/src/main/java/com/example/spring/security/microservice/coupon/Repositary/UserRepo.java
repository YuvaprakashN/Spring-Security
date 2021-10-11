package com.example.spring.security.microservice.coupon.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.security.microservice.coupon.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
User findByEmail(String email);
}
