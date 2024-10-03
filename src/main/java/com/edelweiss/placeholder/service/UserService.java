package com.edelweiss.placeholder.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.edelweiss.placeholder.domain.Users;
import com.edelweiss.placeholder.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    
    public Users getUser(Integer id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return user;
    }

    public List<Users> getAllUsers() {
        List<Users> users = new ArrayList<>();
        Iterable<Users> response = userRepository.findAll();
        response.forEach(item -> {
            users.add(item);
        });
        return users;
    }

}
