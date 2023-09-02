package com.example.todolist.logic.impl;

import static com.example.todolist.dto.enums.RoleCatalog.EXTERNAL_CLIENT;
import static com.example.todolist.dto.enums.StatusResponses.*;

import com.example.todolist.adapter.entity.UserEntity;
import com.example.todolist.adapter.repository.UserRepository;
import com.example.todolist.adapter.service.impl.SendEmailServiceImpl;
import com.example.todolist.dto.EmailUserRegistrationDto;
import com.example.todolist.dto.request.login.LoginRequestDto;
import com.example.todolist.dto.request.user.ConfirmEmailRequestDto;
import com.example.todolist.dto.request.user.RegisterUserRequestDto;
import com.example.todolist.dto.response.GenericResponses;
import com.example.todolist.logic.AuthLogic;
import com.example.todolist.util.impl.ExceptionControl;
import com.example.todolist.util.impl.JwtUtilsImpl;
import com.example.todolist.util.impl.ShortUuid;

import java.util.Collections;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthLogicImpl implements AuthLogic {

  @Autowired private UserRepository userRepository;

  @Autowired private JwtUtilsImpl jwtUtils;

  @Autowired private ExceptionControl exceptionControl;

  @Autowired private SendEmailServiceImpl sendEmailService;

  private final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

  @Override
  public GenericResponses<Object> registerUser(RegisterUserRequestDto dto) {
    var genericResponse = new GenericResponses<>();

    try {
      var userEntity =
          UserEntity.builder()
              .username(dto.getUsername())
              .email(dto.getEmail())
              .password(bCrypt.encode(dto.getPassword()))
              .status(false)
              .createdAt(new Date())
              .build();

      var userDto = userRepository.save(userEntity);

      userDto.setConfirmCode(ShortUuid.generateLong(userDto.getUsId()));
      userDto = userRepository.save(userEntity);

      sendEmailService.sendRegistrationCode(
          EmailUserRegistrationDto.builder()
              .code(userDto.getConfirmCode())
              .email(userDto.getEmail())
              .build());

      genericResponse.setStatus(REGISTER_CREATED.get());
    } catch (Exception e) {
      genericResponse = exceptionControl.extractError(e);
    }

    return genericResponse;
  }

  @Override
  public GenericResponses<Object> confirmRegisterEmail(ConfirmEmailRequestDto dto) {
    var genericResponse = new GenericResponses<>();

    try {

      var userDto = userRepository.findByUsername(dto.getUsername());

      if (ObjectUtils.isEmpty(userDto)) {
        genericResponse.setStatus(LOGIN_FAILED.get());
        return genericResponse;
      }

      if (userDto.getStatus()) {
        genericResponse.setStatus(REGISTER_ALREADY_COMPLETE.get());
        return genericResponse;
      }

      if (!BCrypt.checkpw(dto.getPassword(), userDto.getPassword())) {
        genericResponse.setStatus(LOGIN_FAILED.get());
        return genericResponse;
      }

      if (ObjectUtils.notEqual(userDto.getConfirmCode(), dto.getCode())) {
        genericResponse.setStatus(REGISTER_FAILED_VERIFIED_EMAIL.get());
        return genericResponse;
      }

      userDto.setStatus(Boolean.TRUE);
      userRepository.save(userDto);

      genericResponse.setStatus(REGISTER_CONFIRM.get());
      return genericResponse;
    } catch (Exception e) {
      return exceptionControl.extractError(e);
    }
  }

  @Override
  public GenericResponses<Object> login(LoginRequestDto dto) {
    var genericResponse = new GenericResponses<>();

    try {

      var userDto = userRepository.findByUsername(dto.getUsername());
      if (userDto == null
          || !userDto.getStatus()
          || !BCrypt.checkpw(dto.getPassword(), userDto.getPassword())) {
        genericResponse.setStatus(LOGIN_FAILED.get());
        return genericResponse;
      }

      genericResponse.setStatus(OK.get());
      genericResponse.setBody(
          jwtUtils.generateToken(
              userDto.getUsername(), Collections.singletonList(EXTERNAL_CLIENT.getDescription())));

    } catch (Exception e) {
      return exceptionControl.extractError(e);
    }

    return genericResponse;
  }

  @Override
  public GenericResponses<Object> verifiedToken(String token) {
    var genericResponse = new GenericResponses<>();
    try {
      if (!jwtUtils.validateToken(token)) {
        genericResponse.setStatus(TOKEN_NOT_VALID.get());
        return genericResponse;
      }

      var username = jwtUtils.extractUsername(token);
      var userDto = userRepository.findByUsername(username);

      if (ObjectUtils.isEmpty(userDto) || !userDto.getStatus()) {
        genericResponse.setStatus(TOKEN_NOT_VALID.get());
        return genericResponse;
      }

      genericResponse.setStatus(OK.get());
      return genericResponse;
    } catch (Exception e) {
      log.error("Extract Error => {}", exceptionControl.extractError(e));
      genericResponse.setStatus(TOKEN_NOT_VALID.get());
      return genericResponse;
    }
  }
}
