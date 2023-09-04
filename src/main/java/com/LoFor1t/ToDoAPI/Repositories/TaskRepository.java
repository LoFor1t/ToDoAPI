package com.LoFor1t.ToDoAPI.Repositories;

import com.LoFor1t.ToDoAPI.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
