package com.example.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResquestToRegister {
    private String name;
    private String email;
    private String phone;
    private Boolean fistTime;
}
