package com.example.todolist.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterUserRequestDto {
    private String username;
    private String password;
    private String email;
}
