package taskmanager.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskmanager.dto.TaskDto;
import taskmanager.exception.EmployeeDoesntExistsException;
import taskmanager.model.Task;
import taskmanager.service.TaskService;

import java.util.List;

@RestController
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @PostMapping("/tasks")
    public ResponseEntity<String> save(@RequestBody TaskDto taskDto) throws Exception {
        return taskService.addTask(taskDto);
    }

    @GetMapping("/tasks/user")
    public ResponseEntity<List<Task>> getTasksByUser(@RequestParam Long id) throws Exception {
        return taskService.getAllTasksOfEmployee(id);
    }

    @PutMapping("/tasks")
    public ResponseEntity<String> update(@RequestBody TaskDto taskDto) {
        return taskService.updateTaskById(taskDto);
    }

    @DeleteMapping("/tasks")
    public ResponseEntity<String> delete(@RequestParam Long id) {
        return taskService.deleteTask(id);
    }

}
