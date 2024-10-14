package com.example.request;

import com.example.model.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRequest {
    private String name;
    private Double pricePerDay;
    private String  carCategoryName;
    private int countOfThatCar;
}
