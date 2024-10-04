package com.edelweiss.placeholder.repository;

import org.springframework.data.repository.CrudRepository;

import com.edelweiss.placeholder.domain.Posts;
import java.util.List;


public interface PostsRepository extends CrudRepository<Posts, Integer> {
    List<Posts> findByUserId(Integer userId);
}
