package com.edelweiss.placeholder.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edelweiss.placeholder.domain.Users;
import com.edelweiss.placeholder.service.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {
    
    private final UserService service;

    UsersController(UserService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Users> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping(path ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> getUserById(@PathVariable Integer id) {
        Users user = service.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
