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

import com.edelweiss.placeholder.domain.Comments;
import com.edelweiss.placeholder.exception.IllegalParameterException;
import com.edelweiss.placeholder.service.CommentsService;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    private final CommentsService service;

    CommentsController(CommentsService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Comments>> getAllComments() {
        List<Comments> comments = service.findAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping(params = "postId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Comments>> getCommentsByPostId(@RequestParam(required = false) Integer postId) {
        if (postId == null) {
            throw new IllegalParameterException("Request Paramer postId cannot be null");
        }
        List<Comments> comments = service.findCommentsByPostId(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

        @GetMapping(params = "userId", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<Comments>> getCommentsByUserId(@RequestParam(required = false) Integer userId) {
        if (userId == null) {
            throw new IllegalParameterException("Request Paramer userId cannot be null");
        }
        List<Comments> comments = service.findCommentsByUserId(userId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }



    @GetMapping(path ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comments> getCommentById(@PathVariable Integer id) {
        Comments comment = service.findCommentById(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
}
