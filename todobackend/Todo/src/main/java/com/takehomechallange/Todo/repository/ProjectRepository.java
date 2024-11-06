package com.takehomechallange.Todo.repository;

import com.takehomechallange.Todo.model.Project;
import com.takehomechallange.Todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}


