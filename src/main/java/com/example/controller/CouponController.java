package com.example.controller;


import com.example.model.Coupon;
import com.example.model.RentalOrder;
import com.example.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/allcoupon")
    public ResponseEntity<Iterable<Coupon>> allCoupon(){
        Iterable<Coupon> coupon=couponService.allCoupon();
        return ResponseEntity.ok(coupon);
    }

    @GetMapping("/forcurrentCustomer/{id}/{days}")
    public ResponseEntity<List<Object>> forCurrentCustomer(@PathVariable Long id, @PathVariable int days){
        return couponService.findCouponForCustomer( id,days);

    }

}
