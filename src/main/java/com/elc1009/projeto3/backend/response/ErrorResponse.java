package com.elc1009.projeto3.backend.response;

public class ErrorResponse extends Response {

    public ErrorResponse(String message) {
        super("Error", message);
    }
}
