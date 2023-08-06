package com.todoapp.todos.service;

import com.todoapp.todos.entity.Todo;
import com.todoapp.todos.exeption.BusinessLogicException;
import com.todoapp.todos.exeption.ExceptionCode;
import com.todoapp.todos.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
@Service
public class TodoService {
    // 리포지토리 코드 추가
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;

    }

    public Todo createTodo(Todo todo){
        //DB 관련 코드 필요
        return todoRepository.save(todo);
    }
    public Todo updateTodo(Todo todo){
        //DB 관련 코드 필요
        Todo updatedTodo = findTodo(todo.getTodoId());

        Optional.ofNullable(todo.getTitle())
                .ifPresent(title -> updatedTodo.setTitle(title));
        Optional.ofNullable(todo.getTodoOrder())
                .ifPresent(order -> updatedTodo.setTodoOrder(order));
        Optional.ofNullable((todo.isCompleted()))
                .ifPresent(completed -> updatedTodo.setCompleted(completed));

        return todoRepository.save(updatedTodo);
    }

    @Transactional(readOnly = true)
    public Todo findTodo(long todoId) {
//        Todo todo = new Todo(todoId,"할일",1, true);
//        return todo;
        return todoRepository.findById(todoId)
                .orElseThrow(()-> new BusinessLogicException(ExceptionCode.TODO_NOT_FOUND));
    }
    public List<Todo> findTodos() {
        // (8) 모든 회원 정보 조회
        return todoRepository.findAll();
    }
    public void deleteTodo(long todoId){
        //DB 관련 코드 필요
        Todo FoundTodo = findTodo(todoId);
        todoRepository.delete(FoundTodo);
    }

    public void deleteAll() {
        todoRepository.deleteAll();
    }

}
