package com.elc1009.projeto3.backend.controllers;

import com.elc1009.projeto3.backend.model.User;
import com.elc1009.projeto3.backend.repository.UserRepository;
import com.elc1009.projeto3.backend.response.ErrorResponse;
import com.elc1009.projeto3.backend.response.GoogleAuthResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class GoogleAuthController {

    @Autowired
    UserRepository userRepository;

    private static final String CLIENT_ID = "823491690532-81b0v1ffin4kvvmb0jlpn51dvlivcjq1.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "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";
    private static final String REDIRECT_URI = "http://localhost:5173";

    private final NetHttpTransport httpTransport = new NetHttpTransport();
    private final JsonFactory jsonFactory = new GsonFactory();

    @PostMapping("/google-auth")
    public Object authenticateWithGoogle(@RequestBody Map<String, String> body, HttpServletRequest request) {
        String code = body.get("code");

        if (code == null || code.isEmpty()) {
            return new ErrorResponse("Authorization code is missing from the request body.");
        }

        try {
            GoogleAuthorizationCodeTokenRequest tokenRequest = new GoogleAuthorizationCodeTokenRequest(
                    httpTransport,
                    jsonFactory,
                    CLIENT_ID,
                    CLIENT_SECRET,
                    code,
                    REDIRECT_URI
            );

            com.google.api.client.auth.oauth2.TokenResponse tokenResponse = tokenRequest.execute();
            String idTokenString = (String) tokenResponse.get("id_token");

            if (idTokenString == null || idTokenString.isEmpty()) {
                return new ErrorResponse("ID Token not received from Google during code exchange.");
            }

            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);

            if (idToken == null) {
                return new ErrorResponse("Invalid Google ID Token.");
            }

            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();

            User existingUser = userRepository.findByEmail(email);

            if (existingUser != null) {
                request.getSession().setAttribute("username", existingUser.getUsername());
                return new GoogleAuthResponse(email, true); 
            } else {
                return new GoogleAuthResponse(email, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse("An unexpected error occurred during Google authentication: " + e.getMessage());
        }
    }
}