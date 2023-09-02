package com.example.todolist.dto.enums;

import com.example.todolist.dto.response.StatusDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StatusResponses {
  REGISTER_ERROR_EMAIL(HttpStatus.SERVICE_UNAVAILABLE.value(), "ERROR, To send email"),
  REGISTER_FAILED_VERIFIED_EMAIL(HttpStatus.UNAUTHORIZED.value(), "ERROR, To send email"),
  REGISTER_BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "ERROR, Fill all values"),
  REGISTER_ALREADY_COMPLETE(HttpStatus.UNAUTHORIZED.value(), "Register, Already complete"),
  REGISTER_CREATED(HttpStatus.CREATED.value(), "CREATED, Please confirm the email"),
  REGISTER_CONFIRM(HttpStatus.OK.value(), "Success, Your account is confirmed"),
  LOGIN_BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "ERROR, Fill all values"),
  LOGIN_FAILED(HttpStatus.UNAUTHORIZED.value(), "Check your username and/or password"),
  TOKEN_NOT_VALID(HttpStatus.UNAUTHORIZED.value(), "Your current access is not valid"),
  NOTE_IS_PRIVATE(HttpStatus.FORBIDDEN.value(), "Access Denied: You do not have the necessary permissions to access this item."),
  OK(HttpStatus.OK.value(), "OK"),
  BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "Bad Request"),
  NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Not Found"),
  ROLE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Role Not Found"),
  HEADER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Header Not Found");

  private final int code;
  private final String description;

  public StatusDTO get() {
    return new StatusDTO(code, description, HttpStatus.valueOf(code));
  }
}
