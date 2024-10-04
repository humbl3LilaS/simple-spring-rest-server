package com.edelweiss.placeholder.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edelweiss.placeholder.domain.Posts;
import com.edelweiss.placeholder.exception.IllegalParameterException;
import com.edelweiss.placeholder.service.PostsService;


@RestController
@RequestMapping("/posts")
public class PostsController {
    private final PostsService service;
    
    PostsController(PostsService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Posts>> findAllPosts() {
        List<Posts> posts = service.findAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping(params = "userId")
    public ResponseEntity<List<Posts>> findPostByUserId(@RequestParam(required = false) Integer userId) {
        if (userId == null) {
            throw new IllegalParameterException("Request Paramer userId cannot be null");
        }
        List<Posts> post = service.findPostByUserId(userId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Posts> findPostById(@PathVariable Integer id) {
        Posts post = service.findPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

}
