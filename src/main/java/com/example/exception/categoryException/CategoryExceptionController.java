package com.example.exception.categoryException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CategoryExceptionController {
    @ExceptionHandler(value = CategoryNotFoundException.class)
    public ResponseEntity<Object> exception(CategoryNotFoundException exception) {
        return new ResponseEntity<>("Category is not found.....", HttpStatus.NOT_FOUND);
    }
}