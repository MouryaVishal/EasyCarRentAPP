package com.example.controller;

import com.example.model.Car;
import com.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;

    //Get all Cars
    @GetMapping("/allcars")
    public ResponseEntity<Iterable<Car>> allCar(){
        Iterable<Car> cars=carService.allcars();
        return ResponseEntity.ok(cars);
    }

    // * view cars as per category
    @GetMapping("/category/{category}")
    public ResponseEntity<Iterable<Car>> allCar(@PathVariable String category){
        Iterable<Car> cars=carService.searchByCategory(category);
        return ResponseEntity.ok(cars);
    }

    //search a specific car
    @GetMapping("/{name}")
    public ResponseEntity<Object> searchByCarName(@PathVariable String name){
        return  carService.searchByCarName(name);
    }

}
