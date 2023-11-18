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
    
    public static class DataNotFound extends RuntimeException {
        public DataNotFound(String message) {
            super(message);
        }
        public DataNotFound(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
