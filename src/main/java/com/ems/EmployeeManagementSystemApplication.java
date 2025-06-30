package com.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	It is a spring boot application for EMS handling create , update , delete and get Employee data
	and also manage employee's manager mapping
 */

@SpringBootApplication
public class  EmployeeManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
		System.out.println("Server is up");
	}

}
