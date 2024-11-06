package com.takehomechallange.Todo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private com.takehomechallange.Todo.repository.ProjectRepository projectRepository;

    // Method to save a project
    public com.takehomechallange.Todo.model.Project save(com.takehomechallange.Todo.model.Project project) {
        return projectRepository.save(project);
    }

    // Method to find all projects
    public List<com.takehomechallange.Todo.model.Project> findAll() {
        return projectRepository.findAll();
    }

    // Method to find a project by id
    public Optional<com.takehomechallange.Todo.model.Project> findById(Long id) {
        return projectRepository.findById(id);
    }
}
