package com.edelweiss.placeholder.exception;

public class IllegalParameterException extends RuntimeException {
    public IllegalParameterException() {
        super("Illegal Parameter type");
    }

    public IllegalParameterException(String message) {
        super(message);
    }
}
