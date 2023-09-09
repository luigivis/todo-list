package com.example.todolist.configuration;

import com.example.todolist.dto.response.GenericResponses;
import com.example.todolist.dto.response.StatusDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController implements ErrorController {

  private static final String ERROR_PATH = "/error";

  @RequestMapping("/error")
  public ResponseEntity<GenericResponses<Object>> handleError(HttpServletRequest request) {
    var genericResponse = new GenericResponses<>();
    var status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    if (status != null) {
      var statusCode = Integer.parseInt(status.toString());
      genericResponse.setStatus(new StatusDTO(HttpStatus.valueOf(statusCode)));
    }
    return genericResponse.getResponseHttp(genericResponse);
  }
}
