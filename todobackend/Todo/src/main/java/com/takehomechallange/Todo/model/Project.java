package com.takehomechallange.Todo.model;

import jakarta.persistence.*;
import lombok.Data;


import java.util.List;
@Data
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todos;


}
