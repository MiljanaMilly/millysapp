package com.millysapp.exceptions;

public class SkunkNotFoundException extends Exception {

    public SkunkNotFoundException() {
        super();
    }

    public SkunkNotFoundException(String message) {
        super(message);
    }

    public SkunkNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
