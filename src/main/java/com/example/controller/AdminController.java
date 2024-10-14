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
        return categoryService.addCategory(request);
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
        return categoryService.updateById(id,category);
    }


//    !For car
//    *Api related to Category like addcar,delete,update...
    @PostMapping("/addcar")
    public ResponseEntity<Object> addCar(@RequestBody CarRequest car){
        return carService.addCar(car);
    }

    @PostMapping("/increasecarcount/{id}/{byNum}")
    public ResponseEntity<Object> increseCountOfCarWithCategory(@PathVariable Long id,@PathVariable int byNum){
        return carService.increseCountOfCar(id,byNum);
    }

    @PostMapping("/increasecarcountbyname/{name}/{byNum}")
    public ResponseEntity<Object> increseCountOfCarWithName(@PathVariable String name,@PathVariable int byNum){
        return carService.increseCountOfCarByName(name,byNum);
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
        return carService.updateById(id,car);
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
        return couponService.updateById(id,coupon);
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
