package com.example.service.servicesInterface;

import com.example.model.Coupon;
import com.example.request.CouponResquestToAdd;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CouponServiceInterface {
    ResponseEntity<Object> addCoupon(CouponResquestToAdd coupon);
    Iterable<Coupon> allCoupon();
    String deleteById(Long id);
    ResponseEntity<Object> updateById(Long id, CouponResquestToAdd coupon);
    ResponseEntity<List<Object>> findCouponForCustomer(Long id, int days );

}
