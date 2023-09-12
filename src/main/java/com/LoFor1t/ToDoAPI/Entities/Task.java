package com.LoFor1t.ToDoAPI.Entities;

import com.LoFor1t.ToDoAPI.DataClasses.Priority;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @Column(nullable = false)
    @NotBlank
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDate deadLine;

    @Column(name = "is_done")
    private boolean isDone = false;
}
