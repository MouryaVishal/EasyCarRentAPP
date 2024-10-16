package com.example.exception.categoryException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class CategoryAlreadyFindExceptionController {
    @ExceptionHandler(value = com.example.exception.categoryException.CategoryAlreadyFoundException.class)
    public ResponseEntity<Object> exception(com.example.exception.categoryException.CategoryAlreadyFoundException exception) {
        return new ResponseEntity<>(exception.getMsg()+" ctegory is already present in the space....", HttpStatus.CONFLICT);
    }
}