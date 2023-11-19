package com.microservice.shopping.cart.models.dtoResponses;

public class ResponseTypeDtoMessaje {
    
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseTypeDtoMessaje() {
    }

    public ResponseTypeDtoMessaje(String message) {
        this.message = message;
    }
    
}
