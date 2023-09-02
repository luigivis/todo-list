package com.example.todolist.controller;

import com.example.todolist.command.AuthCommand;
import com.example.todolist.dto.request.login.LoginRequestDto;
import com.example.todolist.dto.request.user.ConfirmEmailRequestDto;
import com.example.todolist.dto.request.user.RegisterUserRequestDto;
import com.example.todolist.dto.response.GenericResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/auth")
public class AuthController {

  @Autowired private AuthCommand command;

  @Autowired
  private HttpServletRequest request;

  @PostMapping("/login")
  ResponseEntity<GenericResponses<Object>> login(@RequestBody LoginRequestDto loginRequestDto) {
    return command.login(loginRequestDto);
  }

  @PostMapping("/register")
  ResponseEntity<GenericResponses<Object>> register(
      @RequestBody RegisterUserRequestDto registerUserRequestDto) {
    return command.register(registerUserRequestDto);
  }

  @PostMapping("/confirm/email")
  ResponseEntity<GenericResponses<Object>> confirmEmail(
      @RequestBody ConfirmEmailRequestDto confirmEmailRequestDto) {
    return command.confirmEmail(confirmEmailRequestDto);
  }

  @GetMapping("/verified/token")
  ResponseEntity<GenericResponses<Object>> verifiedToken(String token) {
    return command.verifiedToken(request);
  }
}
