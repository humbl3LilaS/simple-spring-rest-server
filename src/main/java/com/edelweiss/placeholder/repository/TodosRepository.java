package com.edelweiss.placeholder.repository;

import org.springframework.data.repository.CrudRepository;

import com.edelweiss.placeholder.domain.Todos;
import java.util.List;


public interface TodosRepository extends CrudRepository<Todos, Integer> {
    List<Todos> findByUserId(Integer userId);
}
