package com.mycompany.web.services.concretes;

import java.util.Arrays;

import com.mycompany.web.services.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mycompany.web.dtos.RegistrationDto;
import com.mycompany.web.models.Role;
import com.mycompany.web.models.UserEntity;
import com.mycompany.web.repositories.RoleRepository;
import com.mycompany.web.repositories.UserRepository;

@Service
public class UserManager implements UserService {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserManager(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void saveUser(RegistrationDto registrationDto) {
		UserEntity user = new UserEntity();
		user.setUsername(registrationDto.getUsername());
		user.setEmail(registrationDto.getEmail());
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		Role role = roleRepository.findByName("USER");
		user.setRoles(Arrays.asList(role));
		userRepository.save(user);
		
	}

	@Override
	public UserEntity findByUsername(String username) {
		UserEntity user = userRepository.findByUsername(username);
		return user;
	}

	@Override
	public UserEntity findByEmail(String email) {
		UserEntity user = userRepository.findByEmail(email);
		return user;
	}
	
	
	
}
