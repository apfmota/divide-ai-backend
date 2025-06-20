package com.elc1009.projeto3.backend.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elc1009.projeto3.backend.model.User;
import com.elc1009.projeto3.backend.repository.UserRepository;
import com.elc1009.projeto3.backend.response.ErrorResponse;
import com.elc1009.projeto3.backend.response.SuccessResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/google-register")
public class GoogleRegisterController {

    @Autowired
    UserRepository userRepository;
    
    @PostMapping
    public Object register(@RequestBody Map<String, String> body, HttpServletRequest request) {
        String email = body.get("email");
        String username = body.get("username");
        if (userRepository.findByUsername(username) == null) {
            User user = new User();
            user.setEmail(email);
            user.setUsername(username);
            userRepository.save(user);
            request.getSession().setAttribute("username", user.getUsername());
            return new SuccessResponse("User successfully registered");
        }
        return new ErrorResponse("Username already exists");
    }
}
