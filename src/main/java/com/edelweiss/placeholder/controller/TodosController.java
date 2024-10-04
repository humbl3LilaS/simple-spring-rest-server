package com.edelweiss.placeholder.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edelweiss.placeholder.domain.Todos;
import com.edelweiss.placeholder.service.TodosService;

@Controller
@RequestMapping("/todos")
public class TodosController {
    private final TodosService service;

    TodosController(TodosService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Todos>> getAllTodos() {
        List<Todos> todos = service.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping(params = "userId")
    public ResponseEntity<List<Todos>> getTodosByUserId(@RequestParam(required = true) Integer userId) {
        List<Todos> todos = service.getTodosByUserId(userId);
        return new ResponseEntity<>(todos, HttpStatus.OK);        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todos> getTodoById(@PathVariable Integer id) {
        Todos todo = service.getTodoById(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }
}
