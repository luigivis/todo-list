package com.example.todolist.dto.enums;

import com.example.todolist.util.impl.StatusException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.example.todolist.dto.enums.StatusResponses.ROLE_NOT_FOUND;
import static com.example.todolist.dto.enums.StatusResponses.TASK_STATUS_NOT_FOUND;

@Getter
@AllArgsConstructor
public enum TaskStatusCatalog {
  TODO("TODO"),
  IN_PROGRESS("IN-PROGRESS"),
  COMPLETED("COMPLETED");

  private final String description;

  public static TaskStatusCatalog getByValue(String description) {
    for (TaskStatusCatalog role : TaskStatusCatalog.values()) {
      if (role.name().equals(description)) {
        return role;
      }
    }
    throw new StatusException(TASK_STATUS_NOT_FOUND.get());
  }
}
