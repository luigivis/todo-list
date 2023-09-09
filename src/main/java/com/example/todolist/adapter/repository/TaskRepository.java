package com.example.todolist.adapter.repository;

import com.example.todolist.adapter.entity.TaskEntity;
import com.example.todolist.dto.response.notes.ListNotesResponseDto;
import com.example.todolist.dto.response.task.ListTaskResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

  @Query(
      value =
          """
          select t.task_id,
                 t.task_uuid,
                 t.us_id,
                 t.name,
                 t.description,
                 t.start_time,
                 t.end_time,
                 t.currently,
                 t.share,
                 t.status,
                 t.created_at,
                 t.updated_at
          from task t join user u on u.us_id = t.us_id
          where t.task_uuid = ?
            and u.username = ?
          """,
      nativeQuery = true)
  TaskEntity findByTaskUuidAndUsername(String taskUuid, String username);

  @Query(
      value =
          """
          select t.task_id,
                 t.task_uuid,
                 t.us_id,
                 t.name,
                 t.description,
                 t.start_time,
                 t.end_time,
                 t.currently,
                 t.share,
                 t.status,
                 t.created_at,
                 t.updated_at
          from task t
                   join user u on u.us_id = t.us_id
          WHERE (t.share = 1 AND t.task_uuid = :task_uuid)
             or (t.task_uuid = :task_uuid and u.username = :username)
          """,
      nativeQuery = true)
  TaskEntity findByTaskUuidAndUsernameOrShare(
      @Param("task_uuid") String taskUuid, @Param("username") String username);

  @Query(value = """
          select t.task_uuid as taskUuid,
                 t.name,
                 t.start_time as startTime,
                 t.end_time as endTime,
                 t.status,
                 t.created_at as createdAt
          from task t
                   join user u on u.us_id = t.us_id
          where u.username = ?
          """, nativeQuery = true)
  List<ListTaskResponseDto> listTaskByUsername(String username);
}
