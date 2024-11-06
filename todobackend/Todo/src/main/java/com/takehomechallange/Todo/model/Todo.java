package com.takehomechallange.Todo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;


    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    private boolean completed;

    public boolean getCompleted() {
        return completed;
    }
}
