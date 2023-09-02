package com.example.todolist.adapter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "notes_version")
public class NotesVersionEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "note_ver_id")
  private long noteVerId;

  @Column(name = "note_ver_uuid")
  private String noteVerUuid;

  @Column(name = "note_id")
  private long noteId;

  @Column(name = "content")
  private String content;

  @Column(name = "version")
  private long version;
  
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
