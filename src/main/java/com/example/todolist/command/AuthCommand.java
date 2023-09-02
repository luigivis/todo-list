package com.example.todolist.command;

import static com.example.todolist.dto.enums.HeaderCatalog.JWT;
import static com.example.todolist.dto.enums.StatusResponses.*;

import com.example.todolist.dto.request.login.LoginRequestDto;
import com.example.todolist.dto.request.user.ConfirmEmailRequestDto;
import com.example.todolist.dto.request.user.RegisterUserRequestDto;
import com.example.todolist.dto.response.GenericResponses;
import com.example.todolist.logic.impl.AuthLogicImpl;
import com.example.todolist.util.impl.EnsureDataQuality;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthCommand {

  @Autowired private AuthLogicImpl logic;

  public ResponseEntity<GenericResponses<Object>> login(LoginRequestDto loginRequestDto) {
    var genericResponse = new GenericResponses<>();
    if (!EnsureDataQuality.check(loginRequestDto)) {
      genericResponse.setStatus(LOGIN_BAD_REQUEST.get());
      return genericResponse.getResponseHttp(genericResponse);
    }

    genericResponse = logic.login(loginRequestDto);

    if (ObjectUtils.isEmpty(genericResponse.getBody())){
      genericResponse.setStatus(LOGIN_FAILED.get());
      return genericResponse.getResponseHttp(genericResponse);
    }

    var headers = new HttpHeaders();
    headers.add(JWT.name(), genericResponse.getBody().toString());
    genericResponse.setBody(null);

    return genericResponse.getResponseHttpHeader(genericResponse, headers);
  }

  public ResponseEntity<GenericResponses<Object>> register(
      RegisterUserRequestDto registerUserRequestDto) {
    var genericResponse = new GenericResponses<>();
    if (!EnsureDataQuality.check(registerUserRequestDto)) {
      genericResponse.setStatus(REGISTER_BAD_REQUEST.get());
      return genericResponse.getResponseHttp(genericResponse);
    }
    genericResponse = logic.registerUser(registerUserRequestDto);
    return genericResponse.getResponseHttp(genericResponse);
  }

  public ResponseEntity<GenericResponses<Object>> confirmEmail(
      ConfirmEmailRequestDto confirmEmailRequestDto) {
    var genericResponse = new GenericResponses<>();
    if (!EnsureDataQuality.check(confirmEmailRequestDto)) {
      genericResponse.setStatus(REGISTER_BAD_REQUEST.get());
      return genericResponse.getResponseHttp(genericResponse);
    }
    genericResponse = logic.confirmRegisterEmail(confirmEmailRequestDto);
    return genericResponse.getResponseHttp(genericResponse);
  }

  public ResponseEntity<GenericResponses<Object>> verifiedToken(HttpServletRequest request) {
    var genericResponse = new GenericResponses<>();
    if (StringUtils.isBlank(request.getHeader(JWT.name()))){
      genericResponse.setStatus(TOKEN_NOT_VALID.get());
      return genericResponse.getResponseHttp(genericResponse);
    }

    genericResponse = logic.verifiedToken(request.getHeader(JWT.name()));
    return genericResponse.getResponseHttp(genericResponse);
  }
}
