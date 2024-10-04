package com.edelweiss.placeholder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.edelweiss.placeholder.exception.ErrorCustom;
import com.edelweiss.placeholder.exception.TodoNotFoundException;
import com.edelweiss.placeholder.exception.UserNotFoundException;

@ControllerAdvice
public class GloablExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorCustom> handleGenericException(Exception ex) {
        ErrorCustom error = new ErrorCustom(500, ex.getMessage() != null ? ex.getMessage() : "Server Internal Error");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorCustom> handleIllegalArgumentException(MethodArgumentTypeMismatchException ex) {
        String reason = "Illegal Argument Type: " + ex.getPropertyName() + " must be " + ex.getRequiredType().getSimpleName();
        ErrorCustom error = new ErrorCustom(400, reason);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorCustom> handleUserNotFoundExceptoin(UserNotFoundException ex) {
        ErrorCustom error = new ErrorCustom(ex.getStatusCode().value(), ex.getReason());
        return new ResponseEntity<>(error, ex.getStatusCode());
    }
    
    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ErrorCustom> handleTodoNotFoundException(TodoNotFoundException ex) {
        ErrorCustom error = new ErrorCustom(ex.getStatusCode().value(), ex.getReason());
        return new ResponseEntity<>(error, ex.getStatusCode());
    }
}
