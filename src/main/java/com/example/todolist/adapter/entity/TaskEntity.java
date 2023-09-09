package com.example.todolist.adapter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "task")
public class TaskEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "task_id")
    private long taskId;

    @Column(name = "task_uuid")
    private String taskUuid;

    @JsonIgnore
    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "us_id")
    private UserEntity user;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "currently" ,columnDefinition = "VARCHAR(255)")
    private Object currently;

    @Column(name = "share")
    private Boolean share;

    @Column(name = "status")
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            name = "created_at",
            nullable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedAt;
}
