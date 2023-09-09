package com.example.todolist.filter;

import static com.example.todolist.dto.enums.HeaderCatalog.JWT;
import static com.example.todolist.dto.enums.RunExecutionCatalog.DEV;
import static com.example.todolist.dto.enums.StatusResponses.TOKEN_NOT_VALID;

import com.example.todolist.dto.response.GenericResponses;
import com.example.todolist.util.impl.JwtUtilsImpl;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenFilter implements Filter {

  @Autowired private JwtUtilsImpl jwt;

  @Value("${type}")
  private String envMode;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    var httpRequest = (HttpServletRequest) request;
    String token = httpRequest.getHeader(JWT.name());
    String requestURI = httpRequest.getRequestURI();

    if (envMode.equalsIgnoreCase(DEV.name()) && validUri(requestURI)) {
      chain.doFilter(request, response);
      return;
    }

    if (StringUtils.isBlank(token)) {
      log.info("validNotEmptyToken: {}", request);
      var httpResponse = (HttpServletResponse) response;

      httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      var genericResponse = new GenericResponses<>();
      genericResponse.setStatus(TOKEN_NOT_VALID.get());
      httpResponse.getWriter().write(new Gson().toJson(genericResponse));
      return;
    }

    if (!jwt.validateToken(token)) {
      log.info("validToken: {}", request);
      var httpResponse = (HttpServletResponse) response;

      httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      var genericResponse = new GenericResponses<>();
      genericResponse.setStatus(TOKEN_NOT_VALID.get());
      httpResponse.getWriter().write(new Gson().toJson(genericResponse));
      return;
    }

    chain.doFilter(request, response);
  }

  private boolean validUri(String uri) {
    return uri.equals("/api/v1/auth/login")
        || uri.contains("swagger-ui")
        || uri.contains("/actuator")
        || uri.contains("/v3/api-docs")
        || uri.equals("/favicon.ico")
        || uri.equals("/v1/auth/login")
        || uri.contains("/v1/auth/verified/token");
  }
}
