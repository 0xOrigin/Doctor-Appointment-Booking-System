package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.advices;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions.InvalidFilterConfigurationException;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions.InvalidFilterValueException;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class QueryFilterBuilderExceptionHandler {

    @ExceptionHandler(InvalidFilterValueException.class)
    public ResponseEntity<?> handleException(@NotNull InvalidFilterValueException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(InvalidFilterConfigurationException.class)
    public ResponseEntity<?> handleException(@NotNull InvalidFilterConfigurationException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
