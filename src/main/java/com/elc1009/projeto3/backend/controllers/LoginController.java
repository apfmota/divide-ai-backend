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
import com.elc1009.projeto3.backend.util.PasswordEncrypt;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @ResponseBody
    public Object login(@RequestBody User user, HttpServletRequest request) {
        if (userRepository.existsByUserName(user.getUserName())) {
            if (userRepository.findByUserNameAndPassword(user.getUserName(), PasswordEncrypt.passwordToMD5Hash(user.getPassword())) != null) {
                request.getSession().setAttribute("userName", user.getUserName());
                return new SuccessResponse("Login successful");
            } else {
                return new ErrorResponse("Invalid password");
            }
        } else {
            return new ErrorResponse("User not found");
        }
    }
}
