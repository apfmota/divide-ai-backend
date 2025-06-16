package com.elc1009.projeto3.backend.response;

public class GoogleAuthResponse {
    private final  String email;
    private final Boolean userExists;

    public GoogleAuthResponse(String email, Boolean userExists) {
        this.email = email;
        this.userExists = userExists;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getUserExists() {
        return userExists;
    }
}
