package com.todoapp.todos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class TodoResponseDto {
    private long todoId;
    private String title;
    private int todoOrder;
    private boolean completed;

}
