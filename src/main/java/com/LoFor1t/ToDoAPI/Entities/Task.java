package com.LoFor1t.ToDoAPI.Entities;

import com.LoFor1t.ToDoAPI.DataClasses.Priority;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Task {
    @Id
    @GeneratedValue
    private int Id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDate deadLine;

    private boolean isDone = false;
}
