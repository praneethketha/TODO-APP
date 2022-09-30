package com.xformics.todoserver.service;


import com.xformics.todoserver.entity.Todo;
import com.xformics.todoserver.exception.TodoNotFoundException;
import com.xformics.todoserver.model.Response;
import com.xformics.todoserver.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    public TodoRepository todoRepository;

    // sending a custom response to the client
    @Override
    public ResponseEntity<Object> createTodo(Todo todo) {
        return Response.generateResponse(HttpStatus.CREATED, todoRepository.save(todo));
    }

    // checking the presence of optional query parameter
    @Override
    public ResponseEntity<Object> getTodos(Boolean completed) {
        List<Todo> todos;
        if(completed == null){
            todos = todoRepository.findAllByIdAsc();
        }else{
            todos = todoRepository.findByCompletedAsc(completed);
        }

        return Response.generateResponse(HttpStatus.OK, todos);
    }

    @Override
    public ResponseEntity<Object> getTodoById(Long id) throws TodoNotFoundException {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException("Please provide a valid id"));
        return Response.generateResponse(HttpStatus.OK, todo);
    }

    @Override
    public ResponseEntity<Object> updateTodoById(Long id, Todo todo) throws TodoNotFoundException {
        Todo todoDB = todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException("Please provide a valid id"));

        if(todo.getCompleted() != null){
            todoDB.setCompleted(todo.getCompleted());
        }
        if(todo.getTitle() != null){
            todoDB.setTitle(todo.getTitle());
        }

        todoRepository.save(todoDB);

        return Response.generateResponse(HttpStatus.OK, todoDB);
    }

    @Override
    public ResponseEntity<Object> deleteTodoById(Long id) throws TodoNotFoundException {
        Optional<Todo> optional = todoRepository.findById(id);
        if(!optional.isPresent()){
            throw new TodoNotFoundException("Please provide a valid id");
        }
        todoRepository.deleteById(id);
        return Response.generateResponse(HttpStatus.NO_CONTENT, null);
    }

}



