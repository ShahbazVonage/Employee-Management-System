package com.ems.dto;


import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeUpdateDto {

    private String name;

    private int age;

    private Double salary;

    @Email
    private String email;
}
