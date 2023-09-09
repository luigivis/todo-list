package com.example.todolist.logic.impl;

import com.example.todolist.adapter.entity.TaskEntity;
import com.example.todolist.adapter.repository.TaskRepository;
import com.example.todolist.adapter.repository.UserRepository;
import com.example.todolist.dto.enums.TaskStatusCatalog;
import com.example.todolist.dto.request.task.CreateTaskRequestDto;
import com.example.todolist.dto.response.GenericResponses;
import com.example.todolist.dto.response.task.CurrentlyObject;
import com.example.todolist.dto.response.task.ListTaskResponseDto;
import com.example.todolist.logic.TaskLogic;
import com.example.todolist.util.impl.ExceptionControl;
import com.example.todolist.util.impl.JwtUtilsImpl;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static com.example.todolist.dto.enums.StatusResponses.*;

@Service
@Slf4j
public class TaskLogicImpl implements TaskLogic {

  @Autowired private ExceptionControl exceptionControl;

  @Autowired private JwtUtilsImpl jwtUtils;

  @Autowired private TaskRepository taskRepository;

  @Autowired private UserRepository userRepository;

  @Override
  public GenericResponses<Object> createTask(CreateTaskRequestDto dto, String token) {
    var genericResponse = new GenericResponses<>();

    try {
      var userDto = userRepository.findByUsername(jwtUtils.extractUsername(token));

      var taskEntity =
          TaskEntity.builder()
              .taskUuid(UUID.randomUUID().toString())
              .user(userDto)
              .name(dto.getName())
              .description(dto.getDescription())
              .startTime(dto.getStartTime())
              .endTime(dto.getEndTime())
              .currently(new Gson().toJson(dto.getCurrently()))
              .share(false)
              .status(dto.getStatus().getDescription())
              .createdAt(new Date())
              .build();

      var taskDto = taskRepository.save(taskEntity);
      taskDto.setCurrently(
              new Gson().fromJson(taskDto.getCurrently().toString(), CurrentlyObject.class));
      genericResponse.setStatus(CREATED_NOTE.get());
      genericResponse.setBody(taskDto);
      return genericResponse;
    } catch (Exception ex) {
      log.error("Ocurrio un error => " + ex);
      genericResponse.setStatus(ERROR_CREATING_NOTE.get());
      return genericResponse;
    }
  }

  @Override
  public GenericResponses<Object> deleteTask(String taskUuid, String token) {
    var genericResponse = new GenericResponses<>();
    try {
      var taskDto =
          taskRepository.findByTaskUuidAndUsername(taskUuid, jwtUtils.extractUsername(token));

      if (ObjectUtils.isEmpty(taskDto)) {
        genericResponse.setStatus(ITEM_IS_PRIVATE.get());
        return genericResponse;
      }

      taskRepository.delete(taskDto);
      genericResponse.setStatus(OK.get());
      return genericResponse;
    } catch (Exception e) {
      return exceptionControl.extractError(e);
    }
  }

  @Override
  public GenericResponses<Object> changeStatusTask(
      String taskUuid, TaskStatusCatalog taskStatusCatalog, String token) {
    var genericResponse = new GenericResponses<>();
    try {
      var taskDto =
          taskRepository.findByTaskUuidAndUsername(taskUuid, jwtUtils.extractUsername(token));

      if (ObjectUtils.isEmpty(taskDto)) {
        genericResponse.setStatus(ITEM_IS_PRIVATE.get());
        return genericResponse;
      }

      taskDto.setUpdatedAt(new Date());
      taskDto.setStatus(taskStatusCatalog.getDescription());
      taskRepository.save(taskDto);
      genericResponse.setStatus(OK.get());
      return genericResponse;
    } catch (Exception e) {
      return exceptionControl.extractError(e);
    }
  }

  @Override
  public GenericResponses<Object> findTask(String taskUuid, String token) {
    var genericResponse = new GenericResponses<>();
    try {
      var taskDto =
          taskRepository.findByTaskUuidAndUsernameOrShare(
              taskUuid, jwtUtils.extractUsername(token));

      if (ObjectUtils.isEmpty(taskDto)) {
        genericResponse.setStatus(ITEM_IS_PRIVATE.get());
        return genericResponse;
      }

      taskDto.setCurrently(
          new Gson().fromJson(taskDto.getCurrently().toString(), CurrentlyObject.class));
      genericResponse.setStatus(OK.get());
      genericResponse.setBody(taskDto);
      return genericResponse;
    } catch (Exception e) {
      return exceptionControl.extractError(e);
    }
  }

  @Override
  public GenericResponses<Object> listTask(String token) {
    var genericResponse = new GenericResponses<>();

    try {

      genericResponse.setStatus(OK.get());
      genericResponse.setBody(taskRepository.listTaskByUsername(jwtUtils.extractUsername(token)));
      return genericResponse;

    } catch (Exception e) {
      return exceptionControl.extractError(e);
    }
  }

  @Override
  public GenericResponses<Object> shareTask(String taskUuid, String token) {
    var genericResponse = new GenericResponses<>();
    try {
      var taskDto =
          taskRepository.findByTaskUuidAndUsername(taskUuid, jwtUtils.extractUsername(token));

      if (ObjectUtils.isEmpty(taskDto)) {
        genericResponse.setStatus(ITEM_IS_PRIVATE.get());
        return genericResponse;
      }

      taskDto.setUpdatedAt(new Date());
      taskDto.setShare(!taskDto.getShare());
      taskRepository.save(taskDto);
      genericResponse.setStatus(OK.get());
      return genericResponse;
    } catch (Exception e) {
      return exceptionControl.extractError(e);
    }
  }
}
