package com.example.todolist.adapter.repository;

import com.example.todolist.adapter.entity.NotesVersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesVersionRepository extends JpaRepository<NotesVersionEntity, Long> {}