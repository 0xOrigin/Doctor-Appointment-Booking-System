package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.advices;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions.InvalidFilterConfigurationException;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions.InvalidFilterValueException;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestControllerAdvice
public class QueryFilterBuilderExceptionHandler {

    private final MessageSource messageSource;

    public QueryFilterBuilderExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private String getRequestPath(WebRequest request) {
        return request.getDescription(false).replace("uri=", "");
    }

    @ExceptionHandler(InvalidFilterValueException.class)
    public ResponseEntity<?> handleException(@NotNull InvalidFilterValueException e, WebRequest request, Locale locale) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(InvalidFilterConfigurationException.class)
    public ResponseEntity<?> handleException(@NotNull InvalidFilterConfigurationException e, WebRequest request, Locale locale) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
