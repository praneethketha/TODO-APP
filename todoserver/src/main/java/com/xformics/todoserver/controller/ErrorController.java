package com.xformics.todoserver.controller;

import com.xformics.todoserver.exception.TodoNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

// declaring it as an advice controller
@ControllerAdvice
@ResponseStatus
public class ErrorController extends ResponseEntityExceptionHandler {

    // sending custom response once it hits the validation error
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errors.put(fieldName, message);
        });

        Map<String, Object> errorResponse = new LinkedHashMap<String, Object>();
        errorResponse.put("status", status.getReasonPhrase());
        errorResponse.put("error", errors);

        return new ResponseEntity<>(errorResponse, status);
    }

    // declaring handler for TodoNotFoundException
    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<Object> handleTodoNotFoundException(TodoNotFoundException ex){
        Map<String, Object> errorResponse = new LinkedHashMap<String, Object>();
        errorResponse.put("status", "failure");
        errorResponse.put("message", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
