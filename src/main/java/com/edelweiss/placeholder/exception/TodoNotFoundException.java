package com.edelweiss.placeholder.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class TodoNotFoundException extends ResponseStatusException {
    public TodoNotFoundException(HttpStatusCode status) {
        super(status);
    }
    
    public TodoNotFoundException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}