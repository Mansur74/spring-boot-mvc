package com.mycompany.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.web.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(String name);
}
