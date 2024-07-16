package taskmanager.service;

import org.springframework.http.ResponseEntity;
import taskmanager.dto.EmployeeDto;
import taskmanager.exception.EmployeeAlreadyExistsException;
import taskmanager.exception.EmployeeDoesntExistsException;
import taskmanager.model.Employee;

import java.util.List;

public interface EmployeeService {

    ResponseEntity<String> saveEmployeeToDatabase(Employee employee) throws Exception;
    ResponseEntity<String> deleteEmployeeByNameAndSurname(EmployeeDto employeeDto) throws Exception;
    ResponseEntity<List<Employee>> getAllEmployees() throws Exception;
    ResponseEntity<Employee> getEmployeeById(Long id) throws Exception;
    ResponseEntity<String> updateEmployee(Employee employee) throws Exception;

}
