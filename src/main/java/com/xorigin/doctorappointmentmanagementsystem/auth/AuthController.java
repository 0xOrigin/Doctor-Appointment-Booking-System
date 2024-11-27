package com.xorigin.doctorappointmentmanagementsystem.auth;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.responses.ApiResponse;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.responses.ResponseFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final ResponseFactory responseFactory;
    private final AuthService service;

    public AuthController(
            ResponseFactory responseFactory,
            AuthService authService
    ) {
        this.responseFactory = responseFactory;
        this.service = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO dto, HttpServletResponse response) {
        UserAuthResponseDTO responseDto = service.register(dto);
        ApiResponse<?> apiResponse = responseFactory.createResponse("Success", responseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO dto, HttpServletResponse response) {
        AuthResponseDTO responseDto = service.login(dto, response);
        ApiResponse<?> apiResponse = responseFactory.createResponse("Success", responseDto);
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        service.logout(response);
        ApiResponse<?> apiResponse = responseFactory.createResponse("Success", null);
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordDTO dto) {
        service.forgotPassword(dto);
        ApiResponse<?> apiResponse = responseFactory.createResponse("You will receive an email to reset your password");
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam("token") String token, @Valid @RequestBody ResetPasswordDTO dto) {
        service.resetPassword(token, dto);
        ApiResponse<?> apiResponse = responseFactory.createResponse("Success");
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response, @RequestBody(required = false) Map<String, String> body) {
        String refreshToken = body == null ? null : body.get("refreshToken");
        AuthResponseDTO responseDto = service.refresh(refreshToken, request, response);
        ApiResponse<?> apiResponse = responseFactory.createResponse("Success", responseDto);
        return ResponseEntity.ok().body(apiResponse);
    }

}