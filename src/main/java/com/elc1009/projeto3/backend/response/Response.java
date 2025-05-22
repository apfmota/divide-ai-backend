package com.elc1009.projeto3.backend.response;

public abstract class Response {
    
    private final String type;
    private final String message;

    public Response(String type, String message) {
        this.type = type;
        this.message = message;
    }
}
