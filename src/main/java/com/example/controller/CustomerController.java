package com.example.controller;

import com.example.model.*;
import com.example.request.CustomerResquestToRegister;
import com.example.request.RequsetRentalOrder;
import com.example.service.CarService;
import com.example.service.CustomerService;
import com.example.service.RentalOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private RentalOrderService rentalOrderService;

    @Autowired
    private CarService carService;

//  ! API related to customer
//    For customer
    @PostMapping("/registercustomer")
    public ResponseEntity<Object> addCustomer(@RequestBody CustomerResquestToRegister customer){
        ResponseEntity<Object> newCustomer= customerService.addCustomer(customer);
        return ResponseEntity.ok(newCustomer);
    }

    @DeleteMapping ("/deletecustomer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
        String responseStr=customerService.deleteById(id);
        return ResponseEntity.ok(responseStr);
    }
    @PutMapping("/updatecustomer/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable Long id,@RequestBody CustomerResquestToRegister customer){
        ResponseEntity<Object> updatedCustomer= customerService.updateById(id,customer);
        return ResponseEntity.ok(updatedCustomer);

    }


//    ! API related to rental order
//    For RentralOrder;
    @PostMapping("/placeOrder")
    public ResponseEntity<Object> placeOrder(@RequestBody RequsetRentalOrder requsetRentalOrder){
        ResponseEntity<Object> placedOrderDetails= rentalOrderService.placeRentalOrder(requsetRentalOrder);
        return ResponseEntity.ok(placedOrderDetails);
    }

    @GetMapping("/allrentalorder/{id}")
    public ResponseEntity<List<RentalOrder>> allRentalOrderForParticularCustomer(@PathVariable Long id){
        List<RentalOrder> rentalOrders=rentalOrderService.allRentalOrderForParticularCustomer(id);
        return ResponseEntity.ok(rentalOrders);
    }
    @DeleteMapping ("/deleterentalorder/{id}")
    public ResponseEntity<String> deleteRentalOrder(@PathVariable Long id){
        String responseStr=rentalOrderService.deleteById(id);
        return ResponseEntity.ok(responseStr);
    }


//    ! API related to car
//    View All cars
    @GetMapping("/allCars")
    public ResponseEntity<Iterable<Car>> allCar(){
        Iterable<Car> cars=carService.allcars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/allcarsbycategory/{categoryName}")
    public ResponseEntity<Iterable<Car>> allCarsByCategory(@PathVariable String categoryName) {
        Iterable<Car> cars = carService.searchByCategory(categoryName);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/specificcar/{carName}")
    public ResponseEntity<Object> specificCar(@PathVariable String carName) {
        ResponseEntity<Object> searchedCar= carService.searchByCarName(carName);
        return ResponseEntity.ok(searchedCar);
    }
}
