package com.example.todolist.dto.response.notes;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface ListNotesResponseDto {
  String getNote_uuid();

  String getName();

  Date getCreated_at();

  Date getUpdated_at();
}
