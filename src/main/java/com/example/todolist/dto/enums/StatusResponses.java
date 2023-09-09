package com.example.todolist.dto.enums;

import com.example.todolist.dto.response.StatusDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StatusResponses {
  REGISTER_ERROR_EMAIL(HttpStatus.SERVICE_UNAVAILABLE.value(), "ERROR: UNABLE TO SEND EMAIL."),
  REGISTER_FAILED_VERIFIED_EMAIL(
      HttpStatus.UNAUTHORIZED.value(), "ERROR: EMAIL VERIFICATION FAILED."),
  REGISTER_BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "ERROR: FILL ALL VALUES."),
  REGISTER_ALREADY_COMPLETE(HttpStatus.UNAUTHORIZED.value(), "REGISTER: ALREADY COMPLETE."),
  REGISTER_CREATED(HttpStatus.CREATED.value(), "CREATED: PLEASE CONFIRM THE EMAIL."),
  REGISTER_CONFIRM(HttpStatus.OK.value(), "SUCCESS: YOUR ACCOUNT IS CONFIRMED."),
  LOGIN_BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "ERROR: FILL ALL VALUES."),
  LOGIN_FAILED(HttpStatus.UNAUTHORIZED.value(), "CHECK YOUR USERNAME AND/OR PASSWORD."),
  TOKEN_NOT_VALID(HttpStatus.UNAUTHORIZED.value(), "YOUR CURRENT ACCESS IS NOT VALID."),
  ITEM_IS_PRIVATE(
      HttpStatus.FORBIDDEN.value(),
      "ACCESS DENIED: YOU DO NOT HAVE THE NECESSARY PERMISSIONS TO ACCESS THIS ITEM."),
  NOTE_FAILED_DELETE(HttpStatus.INTERNAL_SERVER_ERROR.value(), "FAILED TO DELETE NOTE."),
  ERROR_GETTING_NOTE(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ERROR: FAILED TO FETCH NOTE"),
  ERROR_CREATING_NOTE(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ERROR: CREATING THE TASK"),
  CREATED_NOTE(HttpStatus.CREATED
          .value(), "CREATED: SUCCESS CREATING THE TASK"),
  OK(HttpStatus.OK.value(), "OK"),
  BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "BAD REQUEST"),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ERROR: UNKNOWN ERROR"),
  NOT_FOUND(HttpStatus.NOT_FOUND.value(), "NOT FOUND"),
  ROLE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ROLE NOT FOUND"),
  TASK_STATUS_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "TASK STATUS NOT FOUND"),
  DAY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "DAY NOT FOUND"),
  HEADER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "HEADER NOT FOUND");

  private final int code;
  private final String description;

  public StatusDTO get() {
    return new StatusDTO(code, description, HttpStatus.valueOf(code));
  }
}
