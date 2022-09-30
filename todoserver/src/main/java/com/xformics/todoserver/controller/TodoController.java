package com.xformics.todoserver.controller;


import com.xformics.todoserver.entity.Todo;
import com.xformics.todoserver.exception.TodoNotFoundException;
import com.xformics.todoserver.service.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

// declaring as a controller & specifying CORS
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/todos")
public class TodoController {

    // autowired todoService bean
    @Autowired
    public TodoServiceImpl todoService;

    @PostMapping
    public ResponseEntity<Object> createTodo(@Valid @RequestBody Todo todo){
        return todoService.createTodo(todo);
    }

    // get mapping with optional query parameter
    @GetMapping
    public ResponseEntity<Object> getTodos(@RequestParam(required = false) Boolean completed){
        return todoService.getTodos(completed);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTodoById(@PathVariable("id") Long id) throws TodoNotFoundException {
        return todoService.getTodoById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateTodoById(@PathVariable("id") Long id, @RequestBody Todo todo) throws TodoNotFoundException {
        return todoService.updateTodoById(id, todo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTodoById(@PathVariable("id") Long id) throws TodoNotFoundException{
        return todoService.deleteTodoById(id);
    }
}
