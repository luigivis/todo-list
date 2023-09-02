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
@Table(name = "notes")
public class NotesEntity {
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "note_id")
    private long noteId;

    @Column(name = "note_uuid")
    private String noteUuid;

    @JsonIgnore
    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "us_id")
    private UserEntity user;

    @Column(name = "name")
    private String name;

    @Column(name = "notes")
    private String notes;

    @Column(name = "share")
    private Byte share;

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
