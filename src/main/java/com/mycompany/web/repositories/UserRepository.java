package com.mycompany.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.web.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findByUsername(String username);
	UserEntity findByEmail(String email);
	UserEntity findFirstByUsername(String username);
}
