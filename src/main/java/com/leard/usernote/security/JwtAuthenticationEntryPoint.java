package com.leard.usernote.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leard.usernote.entity.ResponeObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        new ObjectMapper().writeValue(response.getOutputStream(), new ResponeObject(
                "ng", "You are not authorized to access this API", null)
        );
    }
}
