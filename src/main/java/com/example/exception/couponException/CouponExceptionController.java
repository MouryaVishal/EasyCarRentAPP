package com.example.exception.couponException;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CouponExceptionController {
    @ExceptionHandler(value = CouponNotFoundException.class)
    public ResponseEntity<Object> exception(CouponNotFoundException exception) {
        return new ResponseEntity<>("Coupon is not found.....", HttpStatus.NOT_FOUND);
    }
}
