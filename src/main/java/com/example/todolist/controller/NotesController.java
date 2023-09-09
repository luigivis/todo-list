package com.example.todolist.controller;

import com.example.todolist.command.NotesCommand;
import com.example.todolist.dto.request.notes.CreateNoteRequestDto;
import com.example.todolist.dto.request.notes.UpdateNoteRequestDto;
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

  @PutMapping("/update/{uuid}")
  ResponseEntity<GenericResponses<Object>> updateNote(@PathVariable String uuid, @RequestBody UpdateNoteRequestDto dto) {
    return command.updateNote(dto, uuid, request);
  }


  @PutMapping("/share/{uuid}")
  ResponseEntity<GenericResponses<Object>> shareNote(@PathVariable String uuid) {
    return command.shareNote(uuid, request);
  }

  @DeleteMapping("/delete/{uuid}")
  ResponseEntity<GenericResponses<Object>> deleteNote(@PathVariable String uuid) {
    return command.deleteNote(uuid, request);
  }
}
