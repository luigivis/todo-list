package com.example.todolist.dto.response.task;

import java.util.Date;

public interface ListTaskResponseDto {

    String getTaskUuid();

    String getName();

    Date getStartTime();

    Date getEndTime();

    String getStatus();

    Date getCreatedAt();

}
