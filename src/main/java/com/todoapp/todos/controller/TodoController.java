package com.todoapp.todos.controller;


import com.todoapp.todos.dto.TodoPatchDto;
import com.todoapp.todos.dto.TodoPostDto;
import com.todoapp.todos.dto.TodoResponseDto;
import com.todoapp.todos.entity.Todo;
import com.todoapp.todos.mapper.TodoMapper;
import com.todoapp.todos.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/todos")
@CrossOrigin
public class TodoController {

    private final TodoService todoService;
    private final TodoMapper mapper;

    public TodoController(TodoService todoService, TodoMapper mapper) {
        this.todoService = todoService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postTodo(@RequestBody TodoPostDto todoPostDto){
        Todo todo = mapper.todoPostDtoToTodo(todoPostDto);
        Todo response = todoService.createTodo(todo);
        return new ResponseEntity(mapper.todoToTodoResponseDto(response), HttpStatus.CREATED);
    }


    @PatchMapping("/{todo-id}")
    public ResponseEntity patchTodo(@PathVariable("todo-id") @Positive long todoId,
                                    @RequestBody TodoPatchDto todoPatchDto){
        todoPatchDto.setTodoId(todoId);

        Todo todo = mapper.todoPatchDtoToTodo(todoPatchDto);
        Todo response = todoService.updateTodo(todo);
        return new ResponseEntity(mapper.todoToTodoResponseDto(response), HttpStatus.OK);

    }

    @GetMapping("/{todo-id}")
    public ResponseEntity getTodo(@PathVariable("todo-id") @Positive long todoId){
        Todo response = todoService.findTodo(todoId);
        return new ResponseEntity(mapper.todoToTodoResponseDto(response), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getTodos() {
        List<Todo> response = todoService.findTodos();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/{todo-id}")
    public ResponseEntity deleteTodo(@PathVariable("todo-id") long todoId){
        System.out.println(" # delete Todo ");
        todoService.deleteTodo(todoId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity deleteTodo(){
        System.out.println(" # delete Todo All ");
        todoService.deleteAll();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
