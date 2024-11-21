package com.xorigin.doctorappointmentmanagementsystem.core.generics.exceptions.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.responses.ApiErrorResponse;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.responses.StandardApiErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class AccessDeniedResponseHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        ApiErrorResponse apiErrorResponse = new StandardApiErrorResponse(
            "You do not have permission to perform this action.",
            request.getRequestURI(),
            new HashMap<>()
        );
        String jsonResponse = new ObjectMapper().writeValueAsString(apiErrorResponse);
        response.getWriter().write(jsonResponse);
    }

}
