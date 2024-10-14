package com.example.repo;

import com.example.model.Car;
import com.example.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {
    @Query(value = "SELECT c.* FROM car c JOIN category cat ON c.category_id = cat.id WHERE cat.id = :id", nativeQuery = true)
    Iterable<Car> findByCategoryId(Long id);
    Optional<Car> findByName(String carName);
    @Query(value = "SELECT c.* FROM car c  WHERE c.name = :carName", nativeQuery = true)
    Optional<Object> findByCarName(String carName);
}
