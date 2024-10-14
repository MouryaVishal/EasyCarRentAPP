package com.example.service;

import com.example.exception.carException.CarNotFoundException;
import com.example.model.Car;
import com.example.model.Category;
import com.example.repo.CarRepository;
import com.example.repo.CategoryRepository;
import com.example.request.CarRequest;
import com.example.service.servicesInterface.CarServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService implements CarServiceInterface {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<Object> addCar(CarRequest request){
        String name= request.getName();
        Double pricePerDay= request.getPricePerDay();
        String  carCategoryName= request.getCarCategoryName();
//        Boolean isAvailable=request.getIsAvailable();
        int countOfThatCar= request.getCountOfThatCar();
        Optional<Car> car=carRepository.findByName((name));
        if(car.isEmpty()){
            Car newCar=new Car();
            newCar.setName(name);
            newCar.setPricePerDay(pricePerDay);
            Optional<Category> category=categoryRepository.findByCategoryName(carCategoryName);
            if(category.isEmpty()){
                return new ResponseEntity<>("Car name:"+name+" with category:"+
                        carCategoryName+" is not provided by Company...",HttpStatus.NOT_FOUND);
            }
            newCar.setCategoryId(category.get());
            newCar.setCountOfThatCar(countOfThatCar);
//            newCar.setIsAvailable(isAvailable);
            carRepository.save(newCar);
            return new ResponseEntity<>(newCar,HttpStatus.OK);
        }
        return new ResponseEntity<>("Car name:"+name+" with category:"+
                carCategoryName+" is already present...",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> increseCountOfCar(Long id,int byNum){
        Optional<Car> car=carRepository.findById(id);
        if(car.isEmpty()){
            return new ResponseEntity<>("Car with id:"+id+" is not provide by Company",HttpStatus.NOT_FOUND);
        }
        int previousCOunt=car.get().getCountOfThatCar();
        car.get().setCountOfThatCar(previousCOunt+byNum);
        carRepository.save(car.get());
        return new ResponseEntity<>(car.get(),HttpStatus.OK);
    }

    public ResponseEntity<Object> increseCountOfCarByName(String name,int byNum){
        Optional<Car> car=carRepository.findByName(name);

        if(car.isEmpty()){
            return new ResponseEntity<>("Car with id:"+name+" is not provide by Company",HttpStatus.NOT_FOUND);
        }
        int previousCount=car.get().getCountOfThatCar();
        car.get().setCountOfThatCar(previousCount+byNum);
        carRepository.save(car.get());
        return new ResponseEntity<>(car.get(),HttpStatus.OK);
    }

    public Iterable<Car> allcars(){
        return carRepository.findAll();
    }

    public String deleteById(Long id){
        if(carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return "Deleted SuccessFully!!";
        };
        return "Delete Fail. No Such id found!!";
    }

    public ResponseEntity<Object> updateById(Long id, CarRequest carDetails) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()) {
            car.get().setName(carDetails.getName());
            Optional<Category> category=categoryRepository.findByCategoryName(carDetails.getCarCategoryName());
            if(category.isEmpty()){
                return new ResponseEntity<>("Category with name:"+carDetails.getCarCategoryName()+" is not provide by Company",HttpStatus.NOT_FOUND);
            }
            car.get().setCategoryId(category.get());
            car.get().setPricePerDay(carDetails.getPricePerDay());
            car.get().setCountOfThatCar(carDetails.getCountOfThatCar());
//            car.get().setIsAvailable(carDetails.getIsAvailable());
            carRepository.save(car.get());
            return new ResponseEntity<>(car, HttpStatus.OK);
        } else {
            throw new CarNotFoundException();
        }
    }

    public ResponseEntity<Object> searchByCarName( String carName){
         Optional<Object> car=carRepository.findByCarName(carName);
         if(car.isEmpty()){
             return new ResponseEntity<>("Car with name:"+carName+" is not present...",HttpStatus.NOT_FOUND);
         }
         return new ResponseEntity<>(car.get(),HttpStatus.OK);
    }

    public Iterable<Car> searchByCategory(String categoryName){
        Optional<Category> category=categoryRepository.findByCategoryName(categoryName);
        Iterable<Car> cars=carRepository.findByCategoryId(category.get().getId());
        return cars;
    }

}
