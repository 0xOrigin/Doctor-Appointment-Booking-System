package com.xorigin.doctorappointmentmanagementsystem.core;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handleException(@NotNull Exception e) {
//        return ResponseEntity.badRequest().body(e.getMessage());
//    }
}
