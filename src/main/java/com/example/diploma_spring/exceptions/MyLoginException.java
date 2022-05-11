package com.example.diploma_spring.exceptions;

public class MyLoginException extends Exception {

    private final String message;

    public MyLoginException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}