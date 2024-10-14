package com.example.service.servicesInterface;

import com.example.model.Category;
import com.example.request.CategoryRequest;
import org.springframework.http.ResponseEntity;

public interface CategoryServiceInterface {
    ResponseEntity<Object> addCategory(CategoryRequest request);

    Iterable<Category> allCategory();

    String deleteById(Long id);

    ResponseEntity<Object> updateById(Long id, CategoryRequest categoryDetails);
}

