package com.elc1009.projeto3.backend.controllers;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.elc1009.projeto3.backend.model.User;
import com.elc1009.projeto3.backend.repository.UserRepository;
import com.elc1009.projeto3.backend.response.ErrorResponse;
import com.elc1009.projeto3.backend.response.GoogleAuthResponse;
import com.elc1009.projeto3.backend.response.SuccessResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class GoogleAuthController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/google-auth")
    public Object authenticateWithGoogle(@RequestBody Map<String, String> body, HttpServletRequest request) {
        String idTokenString = body.get("token");

        NetHttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                .Builder(httpTransport, jsonFactory)
                .setAudience(Collections.singletonList("823491690532-81b0v1ffin4kvvmb0jlpn51dvlivcjq1.apps.googleusercontent.com"))
                .build();

        try {
            GoogleIdToken idToken = verifier.verify(idTokenString);
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            User existingUser = userRepository.findByEmail(email);
            if (existingUser != null) {
                request.getSession().setAttribute("username", existingUser.getUsername());
            }
            return new GoogleAuthResponse(email, existingUser != null);
        } catch (Exception e) {
            return new ErrorResponse("Exception while verifying token: " + e.getMessage());
        }
    }
}
