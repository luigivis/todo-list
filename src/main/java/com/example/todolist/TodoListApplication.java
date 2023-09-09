package com.example.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TodoListApplication {

  public static void main(String[] args) {
    SpringApplication.run(TodoListApplication.class, args);
  }
}
