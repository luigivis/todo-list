package com.example.todolist.dto.request.task;

import com.example.todolist.dto.enums.DaysCatalog;
import com.example.todolist.dto.enums.TaskStatusCatalog;
import com.example.todolist.dto.response.task.CurrentlyObject;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateTaskRequestDto {

  @NotBlank
  @Size(max = 20)
  private String name;

  private String description;

  @FutureOrPresent
  private Date startTime;

  @Future
  private Date endTime;

  @NotNull
  private TaskStatusCatalog status;

  private CurrentlyObject currently;

}
