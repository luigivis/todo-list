package com.example.todolist.controller;

import com.example.todolist.command.NotesCommand;
import com.example.todolist.dto.request.notes.CreateNoteRequestDto;
import com.example.todolist.dto.response.GenericResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/notes")
public class NotesController {

  @Autowired private NotesCommand command;

  @Autowired private HttpServletRequest request;

  @PostMapping("/create")
  ResponseEntity<?> createNote(@RequestBody CreateNoteRequestDto dto) {
    return command.create(dto, request);
  }

  @GetMapping("/list")
  ResponseEntity<?> listNotes() {
    return command.listNotes(request);
  }

  @GetMapping("/find/{uuid}")
  ResponseEntity<?> findNoteByUuid(
      @PathVariable String uuid,
      @RequestParam(name = "encrypt", defaultValue = "true") boolean encrypt) {
    return command.findNoteByUuid(uuid, encrypt, request);
  }

  @PostMapping("/update")
  ResponseEntity<GenericResponses<Object>> updateNote() {
    return null;
  }

  @PostMapping("/delete")
  ResponseEntity<GenericResponses<Object>> deleteNote() {
    return null;
  }
}
