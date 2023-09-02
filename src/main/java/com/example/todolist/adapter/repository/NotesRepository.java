package com.example.todolist.adapter.repository;

import com.example.todolist.adapter.entity.NotesEntity;
import com.example.todolist.dto.response.notes.ListNotesResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotesRepository extends JpaRepository<NotesEntity, Long> {

  @Query(value = """
          select n.note_uuid as note_uuid, n.name as name, n.created_at as created_at, n.updated_at as updated_at
          from notes n
                   join user u on u.us_id = n.us_id
          where u.username = ?
          """, nativeQuery = true)
  List<ListNotesResponseDto> listNotes(String username);

  @Query(
      value =
          """
          SELECT n.note_id,
                 n.note_uuid,
                 n.us_id,
                 n.name,
                 n.notes,
                 n.share,
                 n.versioned,
                 n.created_at,
                 n.updated_at
          FROM notes n
                   JOIN user u ON u.us_id = n.us_id
          WHERE (n.share = 1 AND n.note_uuid = :note_uuid)
             OR (n.note_uuid = :note_uuid AND u.username = :username)
          """,
      nativeQuery = true)
  NotesEntity findByUsernameAndNoteUuid(
      @Param("username") String username, @Param("note_uuid") String noteUuid);
}
