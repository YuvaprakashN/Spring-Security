package com.bharath.springcloud.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bharath.springcloud.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

}
