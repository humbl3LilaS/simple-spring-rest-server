package com.edelweiss.placeholder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {
    public UserNotFoundException(HttpStatusCode status) {
        super(status);
    }
    
    public UserNotFoundException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
