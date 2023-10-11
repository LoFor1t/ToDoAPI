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
    public ResponseEntity getTaskByID(@PathVariable int id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete a task by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTaskById(@PathVariable int id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        taskRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task task) {
        Optional<Task> taskToUpdate = taskRepository.findById(id);
        if (taskToUpdate.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (task.getTitle() != null && !task.getTitle().isEmpty()) {
            taskToUpdate.get().setTitle(task.getTitle());
        }
        if (task.getDescription() != null && !task.getDescription().isEmpty()) {
            taskToUpdate.get().setDescription(task.getDescription());
        }
        if (task.getPriority() != null) {
            taskToUpdate.get().setPriority(task.getPriority());
        }
        if (task.getDeadLine() != null) {
            taskToUpdate.get().setDeadLine(task.getDeadLine());
        }
        taskToUpdate.get().setDone(task.isDone());
        taskRepository.save(taskToUpdate.get());
        return new ResponseEntity<>(taskToUpdate.get(), HttpStatus.OK);
    }
}
