package com.xformics.todoserver.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// defining a custom response output
public class Response {
    public static ResponseEntity<Object> generateResponse(HttpStatus status, Object responseObj){
        return new ResponseEntity<Object>(responseObj, status);
    }
}
