package com.xformics.todoserver.service;


import com.xformics.todoserver.entity.Todo;
import com.xformics.todoserver.exception.TodoNotFoundException;
import org.springframework.http.ResponseEntity;

public interface TodoService {
    ResponseEntity<Object> createTodo(Todo todo);

    ResponseEntity<Object> getTodos(Boolean completed);

    ResponseEntity<Object> getTodoById(Long id) throws TodoNotFoundException;

    ResponseEntity<Object> updateTodoById(Long id, Todo todo) throws TodoNotFoundException;

    ResponseEntity<Object> deleteTodoById(Long id) throws TodoNotFoundException;
}
