package com.todoapp.todos.mapper;

import com.todoapp.todos.dto.TodoPatchDto;
import com.todoapp.todos.dto.TodoPostDto;
import com.todoapp.todos.dto.TodoResponseDto;
import com.todoapp.todos.entity.Todo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    Todo todoPostDtoToTodo(TodoPostDto todoPostDto);
    Todo todoPatchDtoToTodo(TodoPatchDto todoPatchDto);
    TodoResponseDto todoToTodoResponseDto(Todo todo);
}
