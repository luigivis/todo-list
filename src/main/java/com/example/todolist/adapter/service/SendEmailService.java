package com.example.todolist.adapter.service;

import com.example.todolist.dto.EmailUserRegistrationDto;
import jakarta.mail.MessagingException;

public interface SendEmailService {

    void sendRegistrationCode(EmailUserRegistrationDto dto) throws MessagingException;

}