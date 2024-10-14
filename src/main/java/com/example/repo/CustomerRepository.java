package com.example.repo;

import com.example.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByName(String name);
    Optional<Customer> findByNameAndEmail(String name,String email);
}
