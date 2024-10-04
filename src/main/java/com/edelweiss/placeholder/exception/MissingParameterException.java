package com.edelweiss.placeholder.exception;

public class MissingParameterException extends IllegalArgumentException {

   public MissingParameterException() {
        super("Missing Request Parameter");
    }

   public MissingParameterException(String message) {
        super(message);
    }
    
}
