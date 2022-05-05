package com.example.todoapplication.repositories;

import com.example.todoapplication.domain.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    List<Todo> findAll();
    List<Todo> findByTitle(String title);
    Todo findById(long id);
}
