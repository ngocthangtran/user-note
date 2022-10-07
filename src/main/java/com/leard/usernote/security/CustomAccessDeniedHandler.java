package com.leard.usernote.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leard.usernote.entity.ResponeObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler  {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        new ObjectMapper().writeValue(response.getOutputStream(), new ResponeObject("ng", "You are not authorized to access this API", null));
    }
}
