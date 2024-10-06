package com.edelweiss.placeholder.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.edelweiss.placeholder.domain.Comments;
import com.edelweiss.placeholder.exception.ItemNotFoundException;
import com.edelweiss.placeholder.repository.CommentsRepository;

@Service
public class CommentsService {
    private final CommentsRepository repository;

    CommentsService(CommentsRepository repository) {
        this.repository = repository;
    }

    public List<Comments> findAllComments() {
        List<Comments> comments = new ArrayList<>();
        Iterable<Comments> response = repository.findAll();
        response.forEach(item -> {
            comments.add(item);
        });
        return comments;
    }

    public Comments findCommentById(Integer id) {
        Comments comment = repository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException(HttpStatus.NOT_FOUND, "Invalid Comment Id");
        });
        return comment;
    }

    public List<Comments> findCommentsByPostId(Integer id) {
        List<Comments> comments = repository.findByPostId(id);
        return comments;
    }

    public List<Comments> findCommentsByUserId(Integer id) {
        List<Comments> comments = repository.findByUserId(id);
        return comments;
    }

}
