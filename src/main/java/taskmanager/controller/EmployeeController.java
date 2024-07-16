package taskmanager.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskmanager.exception.EmployeeAlreadyExistsException;
import taskmanager.exception.EmployeeDoesntExistsException;
import taskmanager.model.Employee;
import taskmanager.service.EmployeeService;

import java.util.List;

@RestController
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employees")
    ResponseEntity<String> addEmployee(@RequestBody Employee employee) throws EmployeeAlreadyExistsException {
        return employeeService.saveEmployeeToDatabase(employee);
    }

    @DeleteMapping("/employees")
    ResponseEntity<String> deleteEmployee(@RequestBody Employee employee) throws EmployeeDoesntExistsException {
        return employeeService.deleteEmployeeByNameAndSurname(employee);
    }

    @GetMapping("/employees")
    ResponseEntity<List<Employee>> getAllEmployees() throws EmployeeDoesntExistsException {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/id")
    ResponseEntity<Employee> getEmployeeById(@RequestParam Long id) throws EmployeeDoesntExistsException {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/employees")
    ResponseEntity<String> updateEmployee(@RequestBody Employee employee) throws Exception {
        return  employeeService.updateEmployee(employee);
    }
}
