package com.takehomechallange.Todo.repository;

import com.takehomechallange.Todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}