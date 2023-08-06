package com.todoapp.todos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommendationsController {
    @GetMapping("/hello")
    public String helloWorld() {
        return "To-do Application !";
    }
}
