package com.LoFor1t.ToDoAPI.Controllers;

import com.LoFor1t.ToDoAPI.Entities.Task;
import com.LoFor1t.ToDoAPI.Repositories.TaskRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final TaskRepository taskRepository;

    public TasksController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Operation(summary = "Get all tasks")
    @GetMapping
    public ResponseEntity<Iterable<Task>> getAllTasks() {
        long count = taskRepository.count();
        if (count > 0) {
            Iterable<Task> allTasks = taskRepository.findAll();
            return new ResponseEntity<>(allTasks, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Create a new task")
    @PostMapping
    public ResponseEntity<Task> createNewTask(@RequestBody Task task) {
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        taskRepository.save(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a task by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskByID(@PathVariable int id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(task.get(), HttpStatus.OK);
    }
}
