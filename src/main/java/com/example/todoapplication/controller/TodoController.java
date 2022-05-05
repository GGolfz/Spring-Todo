package com.example.todoapplication.controller;

import com.example.todoapplication.domain.Todo;
import com.example.todoapplication.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;
    private final String TODO_CONTROLLER_PATH = "/todo";
    @GetMapping(TODO_CONTROLLER_PATH)
    public @ResponseBody List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    @GetMapping(value = TODO_CONTROLLER_PATH, params = "id")
    public @ResponseBody Todo getTodoById(@RequestParam("id") long id) {
        return todoRepository.findById(id);
    }

    @GetMapping(value = TODO_CONTROLLER_PATH, params = "title")
    public @ResponseBody List<Todo> getTodoByTitle(@RequestParam("title") String title) {
        return todoRepository.findByTitle(title);
    }

    @PostMapping(TODO_CONTROLLER_PATH)
    public @ResponseBody Todo addTodo(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    @PutMapping(value = TODO_CONTROLLER_PATH +"/{id}")
    public @ResponseBody Todo editTodo(@PathVariable("id") long id, @RequestBody Todo updatedValue) {
        Todo currentTodo = todoRepository.findById(id);
        if(updatedValue.getTitle() != null) {
            currentTodo.setTitle(updatedValue.getTitle());
        }
        if(updatedValue.getDescription() != null) {
            currentTodo.setDescription(updatedValue.getDescription());
        }
        return todoRepository.save(currentTodo);
    }

    @PatchMapping(value=TODO_CONTROLLER_PATH + "/{id}")
    public @ResponseBody Todo toggleTodo(@PathVariable("id") long id) {
        Todo currentTodo = todoRepository.findById(id);
        currentTodo.setFinish(!currentTodo.isFinish());
        return todoRepository.save(currentTodo);
    }

    @DeleteMapping(value=TODO_CONTROLLER_PATH + "/{id}")

    public @ResponseBody boolean deleteTodo(@PathVariable("id") long id) {
        try {
            todoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
