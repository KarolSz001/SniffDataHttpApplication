package com.app.exception;

public class MyAppException extends RuntimeException {
    private String message;

    public MyAppException(String message) {
        super(message);

    }
}
