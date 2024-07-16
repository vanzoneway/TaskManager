package taskmanager.service;

import org.springframework.http.ResponseEntity;
import taskmanager.dto.TaskDto;
import taskmanager.exception.EmployeeDoesntExistsException;
import taskmanager.model.Task;

import java.util.List;

public interface TaskService {

    ResponseEntity<String> addTask(TaskDto taskDto) throws Exception;
    ResponseEntity<List<Task>> getAllTasksOfEmployee(Long employeeId) throws Exception;
    ResponseEntity<String> deleteTask(Long taskId);
    ResponseEntity<String> updateTaskById(TaskDto taskDto);
}
