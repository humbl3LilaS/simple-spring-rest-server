package com.edelweiss.placeholder.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edelweiss.placeholder.domain.Todos;
import com.edelweiss.placeholder.exception.MissingParameterException;
import com.edelweiss.placeholder.service.TodosService;

@RestController
@RequestMapping("/todos")
public class TodosController {
    private final TodosService service;

    TodosController(TodosService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Todos>> getAllTodos() {
        List<Todos> todos = service.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping(params = "userId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Todos>> getTodosByUserId(@RequestParam(required = false) Integer userId) {
        if (userId == null) {
            throw new MissingParameterException("Request Paramer userId cannot be null");
        }
        List<Todos> todos = service.getTodosByUserId(userId);
        return new ResponseEntity<>(todos, HttpStatus.OK);        
    }

    @GetMapping(path ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todos> getTodoById(@PathVariable Integer id) {
        Todos todo = service.getTodoById(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }
}
