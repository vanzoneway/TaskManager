package taskmanager.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import taskmanager.dao.EmployeeRepository;
import taskmanager.exception.EmployeeAlreadyExistsException;
import taskmanager.exception.EmployeeDoesntExistsException;
import taskmanager.model.Employee;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<String> saveEmployeeToDatabase(Employee employee) throws EmployeeAlreadyExistsException {
        try {
            employeeRepository.save(employee);
        }catch (Exception e){
            throw new EmployeeAlreadyExistsException("Employee with that surname already exists in database");
        }
        return new ResponseEntity<>("Employee added successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteEmployeeByNameAndSurname(Employee employee) throws EmployeeDoesntExistsException {

        if (employeeRepository.deleteEmployeeByNameAndSurname(employee.getName(), employee.getSurname()) == 0)
            throw new EmployeeDoesntExistsException("Employee with that name and surname does not exist");

        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() throws EmployeeDoesntExistsException {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) throw new EmployeeDoesntExistsException("Employee list is empty");
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(Long id) throws EmployeeDoesntExistsException {
        try {
            Employee employee = employeeRepository.findById(id).orElseThrow();
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new EmployeeDoesntExistsException("Employee with that id does not exist");
        }
    }

    @Override
    public ResponseEntity<String> updateEmployee(Employee employee) throws EmployeeAlreadyExistsException {
        try {
            employeeRepository.save(employee);
            return new ResponseEntity<>("Employee updated successfully", HttpStatus.OK);
        } catch (Exception e){
            throw new EmployeeAlreadyExistsException("Employee with that name and surname already exists in database");
        }
    }
}
