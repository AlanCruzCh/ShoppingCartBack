package com.microservice.shopping.cart.exceptions;

public class Exceptions {
    
    public static class DatabaseAccessException extends RuntimeException {
        public DatabaseAccessException(String message) {
            super(message);
        }
        public DatabaseAccessException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    
}
