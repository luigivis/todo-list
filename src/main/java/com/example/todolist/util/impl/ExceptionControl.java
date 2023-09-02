package com.example.todolist.util.impl;

import com.example.todolist.dto.response.GenericResponses;
import com.example.todolist.dto.response.StatusDTO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.DeserializationException;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.example.todolist.dto.enums.StatusResponses.TOKEN_NOT_VALID;

@Slf4j
@Service
public class ExceptionControl {

  public GenericResponses<Object> extractError(Exception e) {

    log.info(new Object() {}.getClass().getEnclosingMethod().getName());

    log.info("Extracting error");
    var genericResponse = new GenericResponses<>();

    log.error("Error => ", e);

    if (e instanceof StatusException) {
      genericResponse.setStatus(((StatusException) e).getStatusDTO());
      return genericResponse;
    }

    if (e instanceof NullPointerException) {
      genericResponse.setStatus(new StatusDTO(HttpStatus.INTERNAL_SERVER_ERROR));
      return genericResponse;
    }

    if (e instanceof DataIntegrityViolationException) {
      var sqlState = ((ConstraintViolationException) e.getCause()).getSQLException().getErrorCode();

      switch (sqlState) {
        case 1062, 1022, 1060, 1061 -> genericResponse.setStatus(
            new StatusDTO(
                HttpStatus.NOT_ACCEPTABLE.value(),
                "DUPLICATE VALUES, CHECK THE EXISTING ONES.",
                HttpStatus.NOT_ACCEPTABLE));

        case 1452 -> genericResponse.setStatus(
            new StatusDTO(
                HttpStatus.NOT_ACCEPTABLE.value(),
                "ERROR ON MODIFY THE DATA.",
                HttpStatus.NOT_ACCEPTABLE));

        default -> genericResponse.setStatus(
            new StatusDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR));
      }

      return genericResponse;
    }

    if (e instanceof ExecutionException) {
      genericResponse.setStatus(new StatusDTO(HttpStatus.GATEWAY_TIMEOUT));
      return genericResponse;
    }

    if (e instanceof ExpiredJwtException
        || e instanceof MalformedJwtException
        || e instanceof DeserializationException
        || e instanceof com.fasterxml.jackson.core.JsonParseException) {
      genericResponse.setStatus(TOKEN_NOT_VALID.get());
      return genericResponse;
    }

    if (e instanceof RuntimeException) {

      if (ObjectUtils.isNotEmpty(e.getCause().getMessage())) {
        if (e.getCause().getMessage().equals("404-BATCH")) {
          genericResponse.setStatus(
              new StatusDTO(
                  HttpStatus.NOT_FOUND.value(), "INVENTORY BATCH NO EXIST", HttpStatus.NOT_FOUND));
          return genericResponse;
        }
      }
      genericResponse.setStatus(new StatusDTO(HttpStatus.INTERNAL_SERVER_ERROR));
      return genericResponse;
    }

    genericResponse.setStatus(new StatusDTO(HttpStatus.INTERNAL_SERVER_ERROR));
    return genericResponse;
  }
}
