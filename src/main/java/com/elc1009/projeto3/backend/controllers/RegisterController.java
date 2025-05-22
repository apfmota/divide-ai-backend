package com.elc1009.projeto3.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.elc1009.projeto3.backend.model.User;
import com.elc1009.projeto3.backend.repository.UserRepository;
import com.elc1009.projeto3.backend.response.ErrorResponse;
import com.elc1009.projeto3.backend.response.SuccessResponse;

@RestController
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	UserRepository userRepository;

	@PostMapping
	@ResponseBody
	public Object register(@RequestBody User user) {
		if (userRepository.existsByUsername(user.getUserName())) {
			return new ErrorResponse("User already exists");
		}
		userRepository.save(user);
		return new SuccessResponse("User registered successfully");
	}

}