package com.example.todolist.logic;

import com.example.todolist.dto.request.login.LoginRequestDto;
import com.example.todolist.dto.request.user.ConfirmEmailRequestDto;
import com.example.todolist.dto.request.user.RegisterUserRequestDto;
import com.example.todolist.dto.response.GenericResponses;

public interface AuthLogic {

  GenericResponses<Object> registerUser(RegisterUserRequestDto dto);

  GenericResponses<Object> confirmRegisterEmail(ConfirmEmailRequestDto dto);

  GenericResponses<Object> login(LoginRequestDto dto);

    GenericResponses<Object> verifiedToken(String token);
}
