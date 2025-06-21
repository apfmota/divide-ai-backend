package com.elc1009.projeto3.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elc1009.projeto3.backend.model.User;
import com.elc1009.projeto3.backend.repository.UserRepository;
import com.elc1009.projeto3.backend.response.ErrorResponse;
import com.elc1009.projeto3.backend.response.Response;
import com.elc1009.projeto3.backend.response.SuccessResponse;
import com.elc1009.projeto3.backend.util.PasswordEncrypt;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Object getUser(HttpServletRequest request) {
        User user = userRepository.findByUsername((String) request.getSession().getAttribute("username"));
        if (user != null) {
            return user;
        }
        return new ErrorResponse("User not found");
    }

    @PostMapping
    public Response updateUser(@RequestBody User userDto, HttpServletRequest request) {
        User existingUser = userRepository.findByUsername((String) request.getSession().getAttribute("username"));
        if (existingUser == null) {
            return new ErrorResponse("User not found");
        }
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPassword(PasswordEncrypt.passwordToMD5Hash(userDto.getPassword()));
        userRepository.save(existingUser);
        return new SuccessResponse("User updated successfully");
    }
}
