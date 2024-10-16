package com.example.controller;

import com.example.model.*;
import com.example.request.CarRequest;
import com.example.request.CategoryRequest;
import com.example.request.CouponResquestToAdd;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// * All Admin RestApi Listed Here.

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private CarService carService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RentalOrderService rentalOrderService;


//    !For Category
//    *Api related to Category like addcategory,delete,update

    @PostMapping("/addcategory")
    public ResponseEntity<Object> addCategory(@RequestBody CategoryRequest request){
        ResponseEntity<Object>newCategory= categoryService.addCategory(request);
        return ResponseEntity.ok(newCategory);
    }
    @GetMapping("/allcategory")
    public ResponseEntity<Iterable<Category>> allCategory(){
        Iterable<Category> categories=categoryService.allCategory();
        return ResponseEntity.ok(categories);
    }
    @DeleteMapping ("/deleteCategory/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        String responseStr=categoryService.deleteById(id);
        return ResponseEntity.ok(responseStr);
    }
    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable Long id,@RequestBody CategoryRequest category){
        ResponseEntity<Object> updatedCategory= categoryService.updateById(id,category);
        return ResponseEntity.ok(updatedCategory);
    }


//    !For car
//    *Api related to Category like addcar,delete,update...
    @PostMapping("/addcar")
    public ResponseEntity<Object> addCar(@RequestBody CarRequest car){
        ResponseEntity<Object> newcar= carService.addCar(car);
        return  ResponseEntity.ok(newcar);
    }

    @PostMapping("/increasecarcount/{id}/{byNum}")
    public ResponseEntity<Object> increseCountOfCarWithCategory(@PathVariable Long id,@PathVariable int byNum){
        ResponseEntity<Object> updatecar=carService.increseCountOfCar(id,byNum);
        return ResponseEntity.ok(updatecar);
    }

    @PostMapping("/increasecarcountbyname/{name}/{byNum}")
    public ResponseEntity<Object> increseCountOfCarWithName(@PathVariable String name,@PathVariable int byNum){
        ResponseEntity<Object> updatecar= carService.increseCountOfCarByName(name,byNum);
        return ResponseEntity.ok(updatecar);
    }

    @GetMapping("/allcars")
    public ResponseEntity<Iterable<Car>> allCar(){
        Iterable<Car> cars=carService.allcars();
        return ResponseEntity.ok(cars);
    }

    @DeleteMapping ("/deletecar/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id){
        String responseStr=carService.deleteById(id);
        return ResponseEntity.ok(responseStr);
    }
    @PutMapping("/updatecar/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable Long id,@RequestBody CarRequest car){
        ResponseEntity<Object> updateNewCar= carService.updateById(id,car);
        return ResponseEntity.ok(updateNewCar);
    }


//   !For Coupon
//    *Api related to Category like addCoupon,delete,update...
    @PostMapping("/addcoupon")
    public ResponseEntity<Object> addCoupon(@RequestBody CouponResquestToAdd coupon){
        ResponseEntity<Object> newCategory=couponService.addCoupon(coupon);
        return ResponseEntity.ok(newCategory);
    }

    @GetMapping("/allcoupon")
    public ResponseEntity<Iterable<Coupon>> allCoupon(){
        Iterable<Coupon> coupon=couponService.allCoupon();
        return ResponseEntity.ok(coupon);
    }

    @DeleteMapping ("/deletecoupon/{id}")
    public ResponseEntity<String> deleteCoupon(@PathVariable Long id){
        String responseStr=couponService.deleteById(id);
        return ResponseEntity.ok(responseStr);
    }
    @PutMapping("/updatecoupon/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable Long id,@RequestBody CouponResquestToAdd coupon){
        ResponseEntity<Object> updatedCoupon= couponService.updateById(id,coupon);
        return ResponseEntity.ok(updatedCoupon);
    }


//    ! CustomerAPI
    @GetMapping("/allcustomer")
    public ResponseEntity<Iterable<Customer>> allcustomer(){
        Iterable<Customer> customers=customerService.allCustomer();
        return ResponseEntity.ok(customers);
    }



//    ! Rental order API
    @GetMapping("/allrentalorder")
    public ResponseEntity<Iterable<RentalOrder>> allRentalOrder(){
        Iterable<RentalOrder> rentalOrders=rentalOrderService.allRentralOrder();
        return ResponseEntity.ok(rentalOrders);
    }
}
