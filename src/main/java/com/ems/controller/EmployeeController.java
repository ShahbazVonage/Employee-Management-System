package com.ems.controller;

import com.ems.dto.EmployeeUpdateDto;
import com.ems.entity.Employee;
import com.ems.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    /**
     * Post mapping for adding a new employee to the db
     * @param employee object that will be added
     * @param managerId id of manager whom the employee is assigned
     * @return Employee object
     */
    @PostMapping
    public Employee addEmployee(@Valid @RequestBody Employee employee, @RequestParam(required = false) Long managerId){
        return employeeService.addEmployee(employee , managerId);
    }

    /**
     * Get mapping to provide all Employees present in DB
     * @return list of all employees.
     */
    @GetMapping
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    /**
     * Get Mapping to get element by employee id
     * @param id employee id
     * @return Employee object of that id
     */
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    /**
     * Put Mapping for updating employee full data
     * @param id of employee
     * @param emp Employee dto , new data of employee
     * @param managerId update of employee's manager by manager's id
     * @return Updated Employee object
     */
    @PutMapping("/{id}")
    public Employee updatePartialEmployeeById(@PathVariable Long id, @Valid @RequestBody Employee emp, @RequestParam(required = false) Long managerId){
        return employeeService.fullyUpdateEmployee(id , emp, managerId);
    }
    /**
     * Patch Mapping for updating employee partial data
     * @param id of employee
     * @param emp Employee dto , new data of employee
     * @param managerId update of employee's manager by manager's id
     * @return Updated Employee object
     */
     @PatchMapping("/{id}")
     public Employee updateFullEmployeeId(@PathVariable Long id, @Valid @RequestBody EmployeeUpdateDto emp , @RequestParam(required = false) Long managerId){
        return employeeService.partialUdateEmployee(id , emp , managerId);
     }

    /**
     * Delete Mapping of Deletion of Employee
     * @param id of employee
     * @return success message
     */
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id){
        return employeeService.deleteById(id);
    }
}
