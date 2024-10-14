package com.example.service.servicesInterface;

import com.example.model.Car;
import com.example.request.CarRequest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CarServiceInterface {
    ResponseEntity<Object> addCar(CarRequest request);

    ResponseEntity<Object> increseCountOfCar(Long id, int byNum);

    ResponseEntity<Object> increseCountOfCarByName(String name, int byNum);

    Iterable<Car> allcars();

    String deleteById(Long id);

    ResponseEntity<Object> updateById(Long id, CarRequest carDetails);

    ResponseEntity<Object> searchByCarName(String carName);

    Iterable<Car> searchByCategory(String categoryName);
}
