package com.elc1009.projeto3.backend.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public abstract class Response {
    
    private final String type;
    private final String message;

    public Response(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        try {
            ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return writer.writeValueAsString(this);
        } catch (Exception e) {
            return null;
        }
    }   
}
