package com.LoFor1t.ToDoAPI.Entities;

import com.LoFor1t.ToDoAPI.DataClasses.Priority;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@ToString
public class Task {
    @Id
    @GeneratedValue
    private int Id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate deadLine;

    @Column(name = "is_done")
    private boolean isDone = false;
}
