package com.mycompany.web.services.abstracts;

import com.mycompany.web.dtos.RegistrationDto;
import com.mycompany.web.models.UserEntity;

public interface UserService {
	void saveUser(RegistrationDto registrationDto);
	UserEntity findByUsername(String username);
	UserEntity findByEmail(String email);
}
