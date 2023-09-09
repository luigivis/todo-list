package com.example.todolist.dto.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum RunExecutionCatalog {
  DEV,
  PROD,
  UAT,
  CONT;
}
