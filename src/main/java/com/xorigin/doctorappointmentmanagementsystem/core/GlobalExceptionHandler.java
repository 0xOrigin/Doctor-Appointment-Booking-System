package com.xorigin.doctorappointmentmanagementsystem.core;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.exceptions.ResourceNotFoundException;
import com.xorigin.doctorappointmentmanagementsystem.core.responses.ApiErrorResponse;
import com.xorigin.doctorappointmentmanagementsystem.core.responses.StandardApiErrorResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private String getRequestPath(WebRequest request) {
        return request.getDescription(false).replace("uri=", "");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(@NotNull MethodArgumentNotValidException e, WebRequest request) {
        Map<String, List<String>> groupedErrors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .filter(error -> error instanceof FieldError)
                .map(error -> (FieldError) error)
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                ));

        ApiErrorResponse errorResponse = new StandardApiErrorResponse(
                "Validation failed",
                getRequestPath(request),
                groupedErrors
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleException(@NotNull ResourceNotFoundException e, WebRequest request) {
        ApiErrorResponse errorResponse = new StandardApiErrorResponse(
                e.getLocalizedMessage(),
                getRequestPath(request),
                new HashMap<>()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleException(@NotNull NoResourceFoundException e, WebRequest request) {
        ApiErrorResponse errorResponse = new StandardApiErrorResponse(
                e.getLocalizedMessage(),
                getRequestPath(request),
                new HashMap<>()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<?> handleException(@NotNull MethodNotAllowedException e, WebRequest request) {
        ApiErrorResponse errorResponse = new StandardApiErrorResponse(
                "Method \"" + e.getHttpMethod() + "\" is not allowed.",
                getRequestPath(request),
                new HashMap<>()
        );
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(@NotNull Exception e, WebRequest request) {
        ApiErrorResponse errorResponse = new StandardApiErrorResponse(
                e.getLocalizedMessage(),
                getRequestPath(request),
                Map.of(
                    "exceptionClassName", List.of(e.getClass().getSimpleName()),
                    "stackTrace", Arrays.stream(e.getStackTrace()).map(Object::toString).toList()
                )
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

}
