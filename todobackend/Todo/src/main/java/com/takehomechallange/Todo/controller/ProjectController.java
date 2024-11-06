package com.takehomechallange.Todo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {

    @Autowired
    private com.takehomechallange.Todo.service.ProjectService projectService;

    @Autowired
    private com.takehomechallange.Todo.service.TodoService todoService;

    @Autowired
    private com.takehomechallange.Todo.service.MarkdownService markdownService;

    @PostMapping
    public com.takehomechallange.Todo.model.Project createProject(@RequestBody com.takehomechallange.Todo.model.Project project) {
        return projectService.save(project);
    }

    @GetMapping
    public List<com.takehomechallange.Todo.model.Project> getAllProjects() {
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.takehomechallange.Todo.model.Project> getProjectById(@PathVariable Long id) {
        Optional<com.takehomechallange.Todo.model.Project> project = projectService.findById(id);
        return project.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{projectId}/todos")
    public ResponseEntity<com.takehomechallange.Todo.model.Todo> addTodoToProject(@PathVariable Long projectId, @RequestBody com.takehomechallange.Todo.model.Todo todo) {
        Optional<com.takehomechallange.Todo.model.Project> project = projectService.findById(projectId);
        if (project.isPresent()) {
            todo.setProject(project.get());
            com.takehomechallange.Todo.model.Todo savedTodo = todoService.save(todo);
            return ResponseEntity.ok(savedTodo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/todos/{todoId}")
    public ResponseEntity<com.takehomechallange.Todo.model.Todo> updateTodo(@PathVariable Long todoId, @RequestBody com.takehomechallange.Todo.model.Todo updatedTodo) {
        Optional<com.takehomechallange.Todo.model.Todo> todoOptional = todoService.findById(todoId);
        if (todoOptional.isPresent()) {
            com.takehomechallange.Todo.model.Todo todo = todoOptional.get();
            todo.setDescription(updatedTodo.getDescription());
            todo.setCompleted(updatedTodo.getCompleted());
            com.takehomechallange.Todo.model.Todo savedTodo = todoService.save(todo);
            return ResponseEntity.ok(savedTodo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{projectId}/todos/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long projectId, @PathVariable Long todoId) {
        todoService.deleteById(todoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{projectId}/export")
    public ResponseEntity<String> exportProjectToMarkdown(@PathVariable Long projectId) {
        Optional<com.takehomechallange.Todo.model.Project> project = projectService.findById(projectId);
        if (project.isPresent()) {
            String markdown = markdownService.generateMarkdown(project.get());
            return ResponseEntity.ok(markdown);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
