package com.elc1009.projeto3.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elc1009.projeto3.backend.response.ErrorResponse;
import com.elc1009.projeto3.backend.response.Response;
import com.elc1009.projeto3.backend.response.SuccessResponse;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/logout")
public class LogoutController {
    
    @GetMapping
    public Response logout(HttpServletRequest request) {
        if (request.getSession().getAttribute("username") == null) {
            return new ErrorResponse("Already logged out");
        } else {
            request.getSession().removeAttribute("username");
            return new SuccessResponse("Logout successful");
        }
    }
}
