package com.ems.service;

import com.ems.dto.EmployeeUpdateDto;
import com.ems.entity.Employee;
import com.ems.entity.Manager;
import com.ems.exception.DuplicateEmailException;
import com.ems.exception.ResourceNotFoundException;
import com.ems.repository.EmployeeRepository;
import com.ems.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;
    /**
     *Adds a new Employee to the database
     * @param employee The employee object to be added
     * @param managerId employee will be assigned to this manager id.
     * @return new Added employee
     */
    public Employee addEmployee(Employee employee, Long managerId){
        log.info("Starting to Add Employee");
        try{
            if(managerId != null){
                Manager manager = managerRepository.findById(managerId)
                        .orElseThrow(() -> new ResourceNotFoundException("Manager not found with ID: " + managerId));

                employee.setManager(manager);
                if(manager.getTeam() == null || manager.getTeam().isEmpty()){
                    manager.setTeam(new ArrayList<>());
                }
                manager.getTeam().add(employee);
            }
            return employeeRepository.save(employee);
        } catch (DataIntegrityViolationException e){
            log.error("Error in Adding Employee");
            throw  new DuplicateEmailException("Email already exist: " + employee.getEmail());
        }
    }

    // Get All Employees in the database
    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    /**
     * Get Employee by id if not present then it will throw custom exception
     * @param id of the employee user want to see
     * @return Employee object of the id user asked
     */
    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: "+ id));
    }

    /**
     * Full Employees data is updated here
     * @param id of the employee user want to update
     * @param updatedEmployee , new data of the employee that user want to change
     * @param managerId if user want to assign this employee to manager
     * @return Employee object of the updated Employee
     */
    public Employee fullyUpdateEmployee(Long id , Employee updatedEmployee, Long managerId){
        log.info("Starting to Fully Update Employee");
        Employee existing = getEmployeeById(id);
        try {
            existing.setName(updatedEmployee.getName());
            existing.setAge(updatedEmployee.getAge());
            existing.setSalary(updatedEmployee.getSalary());
            existing.setEmail(updatedEmployee.getEmail());

            assignManagerToEmployee(existing , managerId);

            return employeeRepository.save(existing);
        } catch (DataIntegrityViolationException e){
            log.error("Error in fully updating Employee");
            throw new DuplicateEmailException("Email you setting is already assigned to another employee: " + updatedEmployee.getEmail());
        }
    }
    /**
     * Partial changes in employee data
     * @param id of the employee user want to update
     * @param updatedEmployee , new data of the employee that user want to change
     * @param managerId if user want to assign this employee to manager
     * @return Employee object of the updated Employee
     */
    public Employee partialUdateEmployee(Long id , EmployeeUpdateDto updatedEmployee , Long managerId){
        log.info("Starting to Partial Update Employee");
        Employee existing = getEmployeeById(id);
        try {
            if(updatedEmployee.getName()!=null)existing.setName(updatedEmployee.getName());
            if(updatedEmployee.getAge()!=0)existing.setAge(updatedEmployee.getAge());
            if(updatedEmployee.getSalary()!=null)existing.setSalary(updatedEmployee.getSalary());
            if(updatedEmployee.getEmail()!=null)existing.setEmail(updatedEmployee.getEmail());

            assignManagerToEmployee(existing , managerId);

            return employeeRepository.save(existing);
        } catch (DataIntegrityViolationException e){
            log.error("Error in partial updating Employee");
            throw new DuplicateEmailException("Email you setting is already assigned to another employee: " + updatedEmployee.getEmail());
        }
    }

    /**
     * method to update manager to the given existing employee
     * @param employee the employee whom the manager will change
     * @param managerId the manger which will assigned to the employee
     */
    public  void assignManagerToEmployee(Employee employee , Long managerId){
        if(managerId != null){
            Manager manager = managerRepository.findById(managerId).orElseThrow(()-> new ResourceNotFoundException("Manager not found with Id: " + managerId));

            employee.setManager(manager);
            if(manager.getTeam()==null || manager.getTeam().isEmpty()){
                manager.setTeam(new ArrayList<>());
            }
            if(!manager.getTeam().contains(employee)) manager.getTeam().add(employee);
        }
    }

    /**
     * Employee deletion
     * @param id of the employee user want to delete
     * @return string message if successfully deleted the employee
     */
    public String deleteById(Long id){
        getEmployeeById(id);
        employeeRepository.deleteById(id);
        return "Employee Deleted Successfully!";
    }
}
