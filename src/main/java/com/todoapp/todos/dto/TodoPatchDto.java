package com.todoapp.todos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoPatchDto {
    private long todoId;
    private String title;
    private int todoOrder;
    private boolean completed;

}
