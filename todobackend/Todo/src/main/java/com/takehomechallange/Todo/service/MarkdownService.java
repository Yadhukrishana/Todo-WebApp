package com.takehomechallange.Todo.service;
import org.springframework.stereotype.Service;

@Service
public class MarkdownService {

    // Method to generate markdown for a project
    public String generateMarkdown(com.takehomechallange.Todo.model.Project project) {
        StringBuilder markdown = new StringBuilder();
        markdown.append("# ").append(project.getTitle()).append("\n\n");
        markdown.append("**Summary**: ").append(project.getTodos().stream().filter(com.takehomechallange.Todo.model.Todo::getCompleted).count())
                .append(" / ").append(project.getTodos().size()).append(" todos completed\n\n");

        markdown.append("## Pending\n");
        project.getTodos().stream().filter(todo -> !todo.getCompleted()).forEach(todo ->
                markdown.append("- [ ] ").append(todo.getDescription()).append("\n")
        );

        markdown.append("\n## Completed\n");
        project.getTodos().stream().filter(com.takehomechallange.Todo.model.Todo::getCompleted).forEach(todo ->
                markdown.append("- [x] ").append(todo.getDescription()).append("\n")
        );

        return markdown.toString();
    }
}
