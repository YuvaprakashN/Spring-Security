package com.bharath.springcloud;

import org.springframework.data.jpa.repository.JpaRepository; 


public interface RoleRepo extends JpaRepository<Role, Long> {

}
