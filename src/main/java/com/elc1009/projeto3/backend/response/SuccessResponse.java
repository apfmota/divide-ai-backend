package com.elc1009.projeto3.backend.response;

public class SuccessResponse extends Response {
    
    public SuccessResponse(String message) {
        super("Success", message);
    }
}
