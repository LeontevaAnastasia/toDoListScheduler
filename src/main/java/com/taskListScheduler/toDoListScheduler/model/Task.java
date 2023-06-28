package com.taskListScheduler.toDoListScheduler.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tasks")
public class Task extends AbstractBaseEntity {

    @Column(name = "header")
    private String header;

    @Column(name = "description")
    private String description;

    @Column(name = "is_completed", columnDefinition = "bool default false")
    private boolean isCompleted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Column(name = "date_time_complete")
    LocalDateTime dateTimeComplete;

    public Task(Integer id, String header, String description, boolean isCompleted, User user, LocalDateTime dateTimeComplete) {
        super(id);
        this.header = header;
        this.description = description;
        this.isCompleted = isCompleted;
        this.user = user;
        this.dateTimeComplete = dateTimeComplete;
    }
}

