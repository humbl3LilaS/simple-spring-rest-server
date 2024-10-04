package com.edelweiss.placeholder.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.edelweiss.placeholder.domain.Todos;
import com.edelweiss.placeholder.exception.TodoNotFoundException;
import com.edelweiss.placeholder.repository.TodosRepository;

@Service
public class TodosService {
    private final TodosRepository repository;

    TodosService(TodosRepository repository) {
        this.repository = repository;
    };

    public List<Todos> getAllTodos() {
        List<Todos> todos = new ArrayList<>();
        Iterable<Todos> response = repository.findAll();
        response.forEach(item -> {
            todos.add(item);
        });
        return todos;
    }

    public Todos getTodoById(Integer id) {
        Todos todos = repository.findById(id).orElseThrow(() -> {
            throw new TodoNotFoundException(HttpStatus.NOT_FOUND, "Todo not found: Invaid Todo Id");
        });
        return todos;
    }

    public List<Todos> getTodosByUserId(Integer id) {
        List<Todos> todos = repository.findByUserId(id);
        if (todos.size() == 0) {
            throw new TodoNotFoundException(HttpStatus.NOT_FOUND, "There is not Todo for provided User Id");
        }
        return todos;
    }
}
