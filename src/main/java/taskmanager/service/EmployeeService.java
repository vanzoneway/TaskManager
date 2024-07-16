package taskmanager.service;

import org.springframework.http.ResponseEntity;
import taskmanager.exception.EmployeeAlreadyExistsException;
import taskmanager.exception.EmployeeDoesntExistsException;
import taskmanager.model.Employee;

import java.util.List;

public interface EmployeeService {

    ResponseEntity<String> saveEmployeeToDatabase(Employee employee) throws EmployeeAlreadyExistsException;
    ResponseEntity<String> deleteEmployeeByNameAndSurname(Employee employee) throws EmployeeDoesntExistsException;
    ResponseEntity<List<Employee>> getAllEmployees() throws EmployeeDoesntExistsException;
    ResponseEntity<Employee> getEmployeeById(Long id) throws EmployeeDoesntExistsException;
    ResponseEntity<String> updateEmployee(Employee employee) throws EmployeeAlreadyExistsException;

}
