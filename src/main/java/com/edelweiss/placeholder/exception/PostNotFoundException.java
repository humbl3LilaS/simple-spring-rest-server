package com.edelweiss.placeholder.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class PostNotFoundException extends ResponseStatusException {
    public PostNotFoundException(HttpStatusCode status) {
        super(status);
    }
    
    public PostNotFoundException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}