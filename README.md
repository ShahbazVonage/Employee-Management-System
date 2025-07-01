# Employee Management System (Spring Boot)

This is a Spring Boot REST API project for managing **Employees** and **Managers**. It uses **Spring Data JPA**, **PostgreSQL**, and a layered architecture with DTOs, validations, and entity relationships.

---

## 🚀 Tech Stack

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Hibernate Validator**
- **Maven**

---

## 🏗️ Project Structure

src/main/java/com/yourcompany/project/
│
├── controller          # Handles HTTP requests and routes them to services
│   ├── EmployeeController.java
│   └── ManagerController.java
│
├── service             # Contains business logic and orchestration
│   ├── EmployeeService.java
│   └── ManagerService.java
│
├── entity              # JPA entity classes mapped to database tables
│   ├── Employee.java
│   └── Manager.java
│
├── repository          # Interfaces extending JpaRepository for DB access
│   ├── EmployeeRepository.java
│   └── ManagerRepository.java
│
├── dto                 # Data Transfer Objects for API request/response
│   ├── EmployeeDTO.java
│   ├── EmployeeCreateDTO.java
│   ├── EmployeeUpdateDTO.java
│   ├── ManagerDTO.java
│   └── ManagerCreateDTO.java
│
└── exception           # Global exception handling and custom exceptions
    ├── GlobalExceptionHandler.java
    └── ResourceNotFoundException.java



```sql
CREATE DATABASE employee_db;
✅ application.properties
properties
spring.datasource.url=jdbc:postgresql://localhost:5432/employee_db
spring.datasource.username=your_username
spring.datasource.password=your_password

📦 Features
✅ Create, Read, Update, Delete Employees

✅ Assign Manager to Employee

✅ Get Manager and their Team

✅ Field validation using @NotNull, @Email, @Min, etc.

✅ DTO usage for clean request structure

✅ Full update (PUT) and partial update (PATCH)

✅ Global Exception Handling using @ControllerAdvice


🧪 API Endpoints
👤 Employee
Method	Endpoint	Description
POST	/employees	Add new employee
GET	/employees	Get all employees
GET	/employees/{id}	Get employee by ID
PUT	/employees/{id}	Full update of employee
PATCH	/employees/{id}	Partial update of employee
DELETE	/employees/{id}	Delete employee

👨‍💼 Manager
Method	Endpoint	Description
POST	/managers	Add new manager
GET	/managers/{id}/team	Get manager and their team

📌 Sample JSON Payloads
🔹 Create Employee
json
{
  "name": "Shahbaz",
  "age": 25,
  "salary": 50000,
  "email": "shahbaz@example.com",
  "managerId": 1
}
🔹 Update Employee (PATCH)
json
{
  "name": "Updated Name"
}
❗ Validations
@NotBlank, @Min, @Email used for input DTOs

Returns proper 400 error response on validation failure

⚠️ Error Handling
Handled via @ControllerAdvice:

ResourceNotFoundException

MethodArgumentNotValidException

DuplicateEmailException

Returns structured error messages.
