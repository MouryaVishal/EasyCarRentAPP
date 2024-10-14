package com.example.repo;

import com.example.model.Car;
import com.example.model.RentalOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RentalOrderRepository extends JpaRepository<RentalOrder,Long> {
    @Query(value = "SELECT c.* FROM rental_order c WHERE c.customer_id = :customerId", nativeQuery = true)
    List<RentalOrder> findByCustomerId(Long customerId);
}
