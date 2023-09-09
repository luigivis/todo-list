package com.example.todolist.command;

import static com.example.todolist.dto.enums.HeaderCatalog.JWT;

import com.example.todolist.dto.enums.TaskStatusCatalog;
import com.example.todolist.dto.request.task.CreateTaskRequestDto;
import com.example.todolist.dto.response.GenericResponses;
import com.example.todolist.logic.impl.TaskLogicImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskCommand {

  @Autowired private TaskLogicImpl logic;

  public ResponseEntity<GenericResponses<Object>> createTask(
      CreateTaskRequestDto dto, HttpServletRequest request) {
    var genericResponse = new GenericResponses<>();
    var token = request.getHeader(JWT.name());
    genericResponse = logic.createTask(dto, token);
    return genericResponse.getResponseHttp(genericResponse);
  }

  public ResponseEntity<GenericResponses<Object>> listTask(HttpServletRequest request) {
    var genericResponse = new GenericResponses<>();
    var token = request.getHeader(JWT.name());
    genericResponse = logic.listTask(token);
    return genericResponse.getResponseHttp(genericResponse);
  }

  public ResponseEntity<GenericResponses<Object>> findTask(
      String uuid, HttpServletRequest request) {
    var genericResponse = new GenericResponses<>();
    var token = request.getHeader(JWT.name());
    genericResponse = logic.findTask(uuid, token);
    return genericResponse.getResponseHttp(genericResponse);
  }

  public ResponseEntity<GenericResponses<Object>> changeStatusTask(
      String uuid, TaskStatusCatalog taskStatusCatalog, HttpServletRequest request) {
    var genericResponse = new GenericResponses<>();
    var token = request.getHeader(JWT.name());
    genericResponse = logic.changeStatusTask(uuid, taskStatusCatalog, token);
    return genericResponse.getResponseHttp(genericResponse);
  }

  public ResponseEntity<GenericResponses<Object>> shareTask(
      String uuid, HttpServletRequest request) {
    var genericResponse = new GenericResponses<>();
    var token = request.getHeader(JWT.name());
    genericResponse = logic.shareTask(uuid, token);
    return genericResponse.getResponseHttp(genericResponse);
  }

  public ResponseEntity<GenericResponses<Object>> deleteTask(
      String uuid, HttpServletRequest request) {
    var genericResponse = new GenericResponses<>();
    var token = request.getHeader(JWT.name());
    genericResponse = logic.deleteTask(uuid, token);
    return genericResponse.getResponseHttp(genericResponse);
  }
}
