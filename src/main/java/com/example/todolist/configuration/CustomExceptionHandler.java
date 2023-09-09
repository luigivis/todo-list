package com.example.todolist.configuration;

import static com.example.todolist.dto.enums.StatusResponses.INTERNAL_SERVER_ERROR;
import static com.example.todolist.dto.enums.StatusResponses.TOKEN_NOT_VALID;

import com.example.todolist.dto.response.GenericResponses;
import com.example.todolist.dto.response.StatusDTO;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler{

  @ExceptionHandler(Exception.class)
  public ResponseEntity<GenericResponses<Object>> handleAllOtherExceptions(
      Exception ex) {
    var genericResponse = new GenericResponses<>();

    if (ex instanceof ExpiredJwtException){
      genericResponse.setStatus(TOKEN_NOT_VALID.get());
      return genericResponse.getResponseHttp(genericResponse);
    }

    genericResponse.setStatus(INTERNAL_SERVER_ERROR.get());
    return genericResponse.getResponseHttp(genericResponse);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleConflict(MethodArgumentNotValidException ex, WebRequest request) {
    var genericResponse = new GenericResponses<>();
    genericResponse.setStatus(new StatusDTO(HttpStatus.BAD_REQUEST.value(), ex.getBody().getDetail(), HttpStatus.valueOf(ex.getStatusCode().value())));
    return genericResponse.getResponseHttp(genericResponse);
  }

}
