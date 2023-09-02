package com.example.todolist.util.impl;

import com.example.todolist.dto.response.StatusDTO;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class StatusException extends RuntimeException {
    private final StatusDTO statusDTO;

    public StatusException(HttpStatus httpStatus) {
        super(httpStatus.getReasonPhrase());
        this.statusDTO = new StatusDTO(httpStatus);
    }

    public StatusException(Integer code, String description, HttpStatus httpStatus) {
        super(description);
        this.statusDTO = new StatusDTO(code, description, httpStatus);
    }

    public StatusException(StatusDTO statusDTO) {
        this.statusDTO = new StatusDTO(statusDTO.getCode(), statusDTO.getDescription(), statusDTO.getHttpStatus());
    }

}
