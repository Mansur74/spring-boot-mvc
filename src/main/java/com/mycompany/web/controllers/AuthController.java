package com.mycompany.web.controllers;

import com.mycompany.web.services.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mycompany.web.dtos.RegistrationDto;
import com.mycompany.web.models.UserEntity;
import com.mycompany.web.services.concretes.UserManager;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	private final UserService userService;

	@Autowired
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/register")
	public String getRegisterForm(Model model)
	{
		RegistrationDto user = new RegistrationDto();
		model.addAttribute("user", user);
		return "register";
	}
	
	@PostMapping("/register")
	public String register(
			@Valid
			@ModelAttribute("user") RegistrationDto user, 
			Model model,
			BindingResult result)
	{
		
		UserEntity userByUsername = userService.findByUsername(user.getUsername());
		if(userByUsername != null && userByUsername.getUsername() != null && !userByUsername.getUsername().isEmpty())
		{
			return "redirect:/register?fail";
		}
		
		UserEntity userByEmail = userService.findByEmail(user.getEmail());
		if(userByEmail != null && userByEmail.getEmail() != null && !userByEmail.getEmail().isEmpty())
		{
			return "redirect:/register?fail";
		}
		
		if(result.hasErrors())
		{
			model.addAttribute("user", user);
			return "register";
		}

		userService.saveUser(user);
		return "redirect:/clubs?success";
		
	}
	
	@GetMapping("/login")
	public String loginPage()
	{
		return "login";
	}
	
}
