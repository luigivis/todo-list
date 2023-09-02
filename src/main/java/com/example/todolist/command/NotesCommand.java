package com.example.todolist.command;

import static com.example.todolist.dto.enums.HeaderCatalog.JWT;
import static com.example.todolist.dto.enums.StatusResponses.NOTE_IS_PRIVATE;
import static com.example.todolist.dto.enums.StatusResponses.TOKEN_NOT_VALID;
import static com.example.todolist.util.impl.DatePattern.MMMM_dd_yyyy;

import com.example.todolist.dto.request.notes.CreateNoteRequestDto;
import com.example.todolist.dto.response.GenericResponses;
import com.example.todolist.logic.impl.NotesLogicImpl;
import com.example.todolist.util.impl.DatePattern;
import com.example.todolist.util.impl.ExceptionControl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotesCommand {

  @Autowired private ExceptionControl exceptionControl;

  @Autowired private NotesLogicImpl logic;

  public ResponseEntity<GenericResponses<Object>> create(
      CreateNoteRequestDto dto, HttpServletRequest headers) {
    var genericResponse = new GenericResponses<>();

    var title = "new notes " + DatePattern.returnDate(MMMM_dd_yyyy);

    if (ObjectUtils.isEmpty(headers.getHeader(JWT.name()))) {
      genericResponse.setStatus(TOKEN_NOT_VALID.get());
      return genericResponse.getResponseHttp(genericResponse);
    }

    if (StringUtils.isBlank(dto.getTitle()) && StringUtils.isNotBlank(dto.getContent())) {
      title = dto.getContent().replace("\n", "").replace("\r", "").substring(0, 20) + "...";
      dto.setTitle(title);
    }

    if (StringUtils.isBlank(dto.getTitle())) {
      dto.setTitle(title);
    }

    genericResponse = logic.createNotes(dto, headers.getHeader(JWT.name()));
    return genericResponse.getResponseHttp(genericResponse);
  }

  public ResponseEntity<?> listNotes(HttpServletRequest request) {
    var genericResponse = logic.getNotes(request.getHeader(JWT.name()));
    return genericResponse.getResponseHttp(genericResponse);
  }

  public ResponseEntity<?> findNoteByUuid(String uuid, boolean encrypt, HttpServletRequest request) {
    var genericResponse = new GenericResponses<>();
    if (StringUtils.isBlank(uuid)) {
      genericResponse.setStatus(NOTE_IS_PRIVATE.get());
      return genericResponse.getResponseHttp(genericResponse);
    }

    genericResponse = logic.findNotes(uuid, encrypt, request.getHeader(JWT.name()));
    return genericResponse.getResponseHttp(genericResponse);
  }
}
