package com.example.todolist.adapter.service.impl;

import com.example.todolist.adapter.service.SendEmailService;
import com.example.todolist.dto.EmailUserRegistrationDto;
import com.example.todolist.util.impl.GetResourceFileImpl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class SendEmailServiceImpl implements SendEmailService {

  @Autowired private JavaMailSender mailSender;

  @Autowired private GetResourceFileImpl getResourcesFile;

  @Value("${spring.mail.username}")
  private String emailFrom;
  
  @Async("threadPoolTaskExecutor")
  public void sendRegistrationCode(EmailUserRegistrationDto dto) throws MessagingException {
    log.info("email address {}", dto.getEmail());
    var message = mailSender.createMimeMessage();
    var emailText = getResourcesFile.getEmailRegistrationCodeStore(dto.getCode());
    message.setFrom(emailFrom);
    message.setRecipients(MimeMessage.RecipientType.TO, dto.getEmail());
    message.setSubject("Registration, ORGANOTES ");
    message.setContent(emailText, "text/html; charset=utf-8");

    mailSender.send(message);
    log.info("email {}: message {}", dto, message);
  }
}
