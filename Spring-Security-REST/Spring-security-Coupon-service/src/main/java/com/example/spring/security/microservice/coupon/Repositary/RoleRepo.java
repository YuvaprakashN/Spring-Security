package com.example.spring.security.microservice.coupon.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.security.microservice.coupon.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
