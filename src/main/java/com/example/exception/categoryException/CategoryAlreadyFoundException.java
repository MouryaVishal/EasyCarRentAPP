package com.example.exception.categoryException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryAlreadyFoundException extends RuntimeException{
    private String msg;
    public CategoryAlreadyFoundException(String message) {
        super(message);
        this.msg=message;
    }
}
