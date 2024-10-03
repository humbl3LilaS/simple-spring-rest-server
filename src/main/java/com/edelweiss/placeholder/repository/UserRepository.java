package com.edelweiss.placeholder.repository;

import org.springframework.data.repository.CrudRepository;

import com.edelweiss.placeholder.domain.Users;

public interface UserRepository extends CrudRepository<Users,Integer> {
    
}
