package com.example.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequsetRentalOrder {
    private String customerName;
    private String customerEmail;
    private String carName;
    private List<String> couponNames;
    private int noOfDaysForRent;
    private String carCategoryName;
}
