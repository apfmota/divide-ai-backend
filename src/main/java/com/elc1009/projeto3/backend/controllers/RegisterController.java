package com.elc1009.projeto3.backend.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

	@PostMapping("/register")
	public String index(String userName, String password) {
		return "Greetings from Spring Boot!";
	}

}