package com.edelweiss.placeholder.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edelweiss.placeholder.domain.Users;
import com.edelweiss.placeholder.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    private final UserService service;

    UserController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Users> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Integer id) {
        return service.getUser(id);
    }
}
