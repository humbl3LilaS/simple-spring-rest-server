package com.edelweiss.placeholder.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edelweiss.placeholder.domain.Users;
import com.edelweiss.placeholder.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users getUser(Integer id) {
        return userRepository.findById(id).orElse(new Users(1, "Suepr", "duper", "duper@gmail.com"));
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
