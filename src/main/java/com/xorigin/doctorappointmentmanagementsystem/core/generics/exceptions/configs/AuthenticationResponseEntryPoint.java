package com.xorigin.doctorappointmentmanagementsystem.core.generics.exceptions.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.responses.ApiErrorResponse;
import com.xorigin.doctorappointmentmanagementsystem.core.responses.StandardApiErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class AuthenticationResponseEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        ApiErrorResponse apiErrorResponse = new StandardApiErrorResponse(
                "Authentication credentials were not provided.",
                request.getRequestURI(),
                new HashMap<>()
        );
        String jsonResponse = new ObjectMapper().writeValueAsString(apiErrorResponse);
        response.getWriter().write(jsonResponse);
    }

}
