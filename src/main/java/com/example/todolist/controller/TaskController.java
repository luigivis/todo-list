package com.example.todolist.controller;

import com.example.todolist.command.TaskCommand;
import com.example.todolist.dto.enums.TaskStatusCatalog;
import com.example.todolist.dto.request.task.CreateTaskRequestDto;
import com.example.todolist.dto.response.GenericResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("v1/task")
public class TaskController {

  @Autowired private TaskCommand command;

  @Autowired private HttpServletRequest request;

  @PostMapping("/create")
  ResponseEntity<GenericResponses<Object>> createTask(
      @Validated @RequestBody CreateTaskRequestDto dto) {
    return command.createTask(dto, request);
  }

  @GetMapping("/list")
  ResponseEntity<GenericResponses<Object>> listTask() {
    return command.listTask(request);
  }

  @GetMapping("/find/{uuid}")
  ResponseEntity<GenericResponses<Object>> findTask(@PathVariable String uuid) {
    return command.findTask(uuid, request);
  }

  @PutMapping("/change/status/{uuid}")
  ResponseEntity<GenericResponses<Object>> changeStatusTask(
      @PathVariable String uuid,
      @RequestParam(name = "status", defaultValue = "TODO") TaskStatusCatalog status) {
    return command.changeStatusTask(uuid, status, request);
  }

  @PutMapping("/share/{uuid}")
  ResponseEntity<GenericResponses<Object>> shareTask(@PathVariable String uuid) {
    return command.shareTask(uuid, request);
  }

  @DeleteMapping("/delete/{uuid}")
  ResponseEntity<GenericResponses<Object>> deleteTask(@PathVariable String uuid) {
    return command.deleteTask(uuid, request);
  }
}
