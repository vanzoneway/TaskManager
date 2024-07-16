package taskmanager.service;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import taskmanager.dao.EmployeeRepository;
import taskmanager.dao.TaskRepository;
import taskmanager.dto.TaskDto;
import taskmanager.exception.EmployeeDoesntExistsException;
import taskmanager.model.Employee;
import taskmanager.model.Task;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<String> addTask(TaskDto taskDto) throws EmployeeDoesntExistsException {

        Employee employee = employeeRepository.findById(taskDto.getEmployeeId())
                .orElseThrow(() -> new EmployeeDoesntExistsException("No employee with such ID"));

        Task task = modelMapper.map(taskDto, Task.class);
        task.setEmployee(employee);
        taskRepository.save(task);
        return new ResponseEntity<>("Task saved successfully to user with id = " + employee.getId(), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<Task>> getAllTasksOfEmployee(Long employeeId) throws EmployeeDoesntExistsException {
        List<Task> tasks = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeDoesntExistsException("No employee with such ID"))
                .getTasks();

        return new ResponseEntity<>(tasks, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> deleteTask(Long taskId){
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
            return new ResponseEntity<>("Task with such ID " + taskId + " deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Task with such ID " + taskId + " not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> updateTaskById(TaskDto taskDto){
        Task task = modelMapper.map(taskDto, Task.class);
        if(taskDto.getEmployeeId() == null || !employeeRepository.existsById(taskDto.getEmployeeId())){
            return new ResponseEntity<>("Employee with such ID " + taskDto.getEmployeeId() + " not found",
                    HttpStatus.NOT_FOUND);
        }
        if(taskRepository.existsById(taskDto.getId())) {
            taskRepository.save(task);
            return new ResponseEntity<>("Task with such ID " + taskDto.getId() + " updated",
                    HttpStatus.OK);
        }
        return new ResponseEntity<>("Task with such ID " + taskDto.getId() + " not found",
                HttpStatus.NOT_FOUND);

    }
}
