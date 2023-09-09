package com.example.todolist.logic;

import com.example.todolist.dto.request.notes.CreateNoteRequestDto;
import com.example.todolist.dto.request.notes.UpdateNoteRequestDto;
import com.example.todolist.dto.response.GenericResponses;

public interface NotesLogic {
  GenericResponses<Object> getNotes(String token);

  GenericResponses<Object> findNotes(String noteUuid, boolean encrypt, String token);

  GenericResponses<Object> deleteNotes(String noteUuid, String token);

  GenericResponses<Object> shareNotes(String noteUuid, String token);

  GenericResponses<Object> createNotes(CreateNoteRequestDto dto, String token);

  GenericResponses<Object> editNotes(UpdateNoteRequestDto dto, String noteUuid, String token);
}
