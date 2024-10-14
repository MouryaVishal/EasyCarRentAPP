package com.example.service.servicesInterface;

import com.example.model.Coupon;
import com.example.model.RentalOrder;
import com.example.request.RequsetRentalOrder;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RentalOrderServiceImterface {
    ResponseEntity<Object> placeRentalOrder(RequsetRentalOrder request);
    Iterable<RentalOrder> allRentralOrder();
    String deleteById(Long id);
}
