package com.example.todolist.util.impl;

import static com.example.todolist.dto.enums.StatusResponses.REGISTER_ERROR_EMAIL;

import io.micrometer.core.instrument.util.IOUtils;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GetResourceFileImpl {
  @Autowired private ResourceLoader resourceLoader;

  public String getEmailRegistrationCodeStore(String code) {
    try {
      var resource = resourceLoader.getResource("classpath:templates/registerNewUser.html");
      var content = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
      return content.replace("{CODE}", code);
    } catch (Exception e) {
      throw new StatusException(REGISTER_ERROR_EMAIL.get());
    }
  }
}
