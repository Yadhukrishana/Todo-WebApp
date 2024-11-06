package com.takehomechallange.Todo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private com.takehomechallange.Todo.repository.TodoRepository todoRepository;

    // Method to save a todo
    public com.takehomechallange.Todo.model.Todo save(com.takehomechallange.Todo.model.Todo todo) {
        return todoRepository.save(todo);
    }

    // Method to find a todo by id
    public Optional<com.takehomechallange.Todo.model.Todo> findById(Long id) {
        return todoRepository.findById(id);
    }

    // Method to delete a todo by id
    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }
}
