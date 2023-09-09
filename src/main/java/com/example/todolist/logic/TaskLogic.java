package com.example.todolist.logic;

import com.example.todolist.dto.enums.TaskStatusCatalog;
import com.example.todolist.dto.request.task.CreateTaskRequestDto;
import com.example.todolist.dto.response.GenericResponses;

public interface TaskLogic {

  GenericResponses<Object> createTask(CreateTaskRequestDto dto, String token);

  GenericResponses<Object> deleteTask(String taskUuid, String token);

  GenericResponses<Object> changeStatusTask(
      String taskUuid, TaskStatusCatalog taskStatusCatalog, String token);

  GenericResponses<Object> findTask(String taskUuid, String token);

  GenericResponses<Object> listTask(String token);

  GenericResponses<Object> shareTask(String taskUuid, String token);
}
