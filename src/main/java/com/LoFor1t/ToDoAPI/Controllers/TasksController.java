package com.LoFor1t.ToDoAPI.Controllers;

import com.LoFor1t.ToDoAPI.Entities.Task;
import com.LoFor1t.ToDoAPI.Repositories.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final TaskRepository taskRepository;

    public TasksController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<Task>> getAllTasks() {
        long count = taskRepository.count();
        if (count > 0) {
            Iterable<Task> allTasks = taskRepository.findAll();
            return new ResponseEntity<>(allTasks, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Task> createNewTask(@Validated @RequestBody Task task) {
        taskRepository.save(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }
}
