package com.example.service.servicesInterface;

import com.example.model.Customer;
import com.example.request.CustomerResquestToRegister;
import org.springframework.http.ResponseEntity;

public interface CustomerServiceInterface {
    ResponseEntity<Object> addCustomer(CustomerResquestToRegister customer);
    Iterable<Customer> allCustomer();
    String deleteById(Long id);
    ResponseEntity<Object> updateById(Long id, CustomerResquestToRegister customer);
}
