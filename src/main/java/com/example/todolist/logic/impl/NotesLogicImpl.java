package com.example.todolist.logic.impl;

import static com.example.todolist.dto.enums.StatusResponses.NOTE_IS_PRIVATE;
import static com.example.todolist.dto.enums.StatusResponses.OK;

import com.example.todolist.adapter.entity.NotesEntity;
import com.example.todolist.adapter.repository.NotesRepository;
import com.example.todolist.adapter.repository.UserRepository;
import com.example.todolist.dto.request.notes.CreateNoteRequestDto;
import com.example.todolist.dto.request.notes.UpdateNoteRequestDto;
import com.example.todolist.dto.response.GenericResponses;
import com.example.todolist.logic.NotesLogic;
import com.example.todolist.util.impl.Encrypt;
import com.example.todolist.util.impl.ExceptionControl;
import com.example.todolist.util.impl.JwtUtilsImpl;
import java.util.Date;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotesLogicImpl implements NotesLogic {

  @Autowired private JwtUtilsImpl jwtUtils;

  @Autowired private NotesRepository notesRepository;

  @Autowired private UserRepository userRepository;

  @Autowired private ExceptionControl exceptionControl;

  @Autowired private Encrypt encrypt;

  @Override
  public GenericResponses<Object> getNotes(String token) {
    var genericResponse = new GenericResponses<>();

    try {
      log.info("CUSTOM OBJECT {}", notesRepository.listNotes(jwtUtils.extractUsername(token)));
      genericResponse.setStatus(OK.get());
      genericResponse.setBody(notesRepository.listNotes(jwtUtils.extractUsername(token)));
      return genericResponse;
    } catch (Exception e) {
      return exceptionControl.extractError(e);
    }
  }

  public GenericResponses<Object> findNotes(String noteUuid, boolean encrypt, String token) {
    var genericResponse = new GenericResponses<>();

    try {

      var username = jwtUtils.extractUsername(token);

      var noteDto = notesRepository.findByUsernameAndNoteUuid(username, noteUuid);

      if (ObjectUtils.isEmpty(noteDto)) {
        genericResponse.setStatus(NOTE_IS_PRIVATE.get());
        return genericResponse;
      }

      genericResponse.setStatus(OK.get());

      if (encrypt) {
        noteDto.setNotes(this.encrypt.desencrypt(noteDto.getNotes()));
      }

      genericResponse.setBody(noteDto);
      return genericResponse;

    } catch (Exception e) {
      return exceptionControl.extractError(e);
    }
  }

  @Override
  public GenericResponses<Object> deleteNotes(String noteUuid, String token) {
    return null;
  }

  @Override
  public GenericResponses<Object> shareNotes(String noteUuid, String token) {
    return null;
  }

  @Override
  public GenericResponses<Object> createNotes(CreateNoteRequestDto dto, String token) {
    var genericResponse = new GenericResponses<>();

    try {
      var userDto = userRepository.findByUsername(jwtUtils.extractUsername(token));
      var notesEntity =
          NotesEntity.builder()
              .noteUuid(UUID.randomUUID().toString())
              .name(dto.getTitle())
              .notes(encrypt.encrypt(dto.getContent()))
              .user(userDto)
              .share((byte) 0)
              .createdAt(new Date())
              .build();

      var noteDto = notesRepository.save(notesEntity);
      noteDto.setNotes(dto.getContent());
      genericResponse.setStatus(OK.get());
      genericResponse.setBody(noteDto);
      return genericResponse;

    } catch (Exception e) {
      return exceptionControl.extractError(e);
    }
  }

  @Override
  public GenericResponses<Object> editNotes(UpdateNoteRequestDto dto, String token) {
    return null;
  }
}
