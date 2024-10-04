package com.edelweiss.placeholder.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.edelweiss.placeholder.domain.Posts;
import com.edelweiss.placeholder.exception.PostNotFoundException;
import com.edelweiss.placeholder.repository.PostsRepository;

@Service
public class PostsService {
    
    private final PostsRepository repository;

    PostsService(PostsRepository repository) {
        this.repository = repository;
    }

    public List<Posts> findAllPosts() {
        List<Posts> posts = new ArrayList<>();
        Iterable<Posts> reponse = repository.findAll();
        reponse.forEach(item -> {
            posts.add(item);
        });
        return posts;
    }    

    public Posts findPostById(Integer id) {
        Posts post = repository.findById(id).orElseThrow(() -> {
            throw new PostNotFoundException(HttpStatus.NOT_FOUND, "Post not found: Invalid Post Id");
        });
        return post;
    }

    public List<Posts> findPostByUserId(Integer id) {
        List<Posts> posts = repository.findByUserId(id);
        if (posts.size() == 0) {
            throw new PostNotFoundException(HttpStatus.NOT_FOUND, "There is no Post for provided User Id");
        }
        return posts;
    }

}
