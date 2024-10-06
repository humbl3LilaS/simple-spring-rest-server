package com.edelweiss.placeholder.repository;

import org.springframework.data.repository.CrudRepository;

import com.edelweiss.placeholder.domain.Comments;
import java.util.List;


public interface CommentsRepository extends CrudRepository<Comments, Integer> {
    List<Comments> findByPostId(Integer postId);;
    List<Comments> findByUserId(Integer userId);
}
