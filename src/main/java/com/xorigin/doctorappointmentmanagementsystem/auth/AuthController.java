package com.xorigin.doctorappointmentmanagementsystem.auth;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.responses.ApiResponse;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.responses.ResponseFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final ResponseFactory responseFactory;
    private final UserProvider userProvider;
    private final AuthService service;

    public AuthController(
            ResponseFactory responseFactory,
            UserProvider userProvider,
            AuthService authService
    ) {
        this.responseFactory = responseFactory;
        this.userProvider = userProvider;
        this.service = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO dto, HttpServletResponse response) {
        UserAuthResponseDTO responseDto = service.register(dto, response);
        ApiResponse<?> apiResponse = responseFactory.createResponse("Success", responseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO dto, HttpServletResponse response) {
        AuthResponseDTO responseDto = service.login(dto, response);
        ApiResponse<?> apiResponse = responseFactory.createResponse("Success", responseDto);
        return ResponseEntity.ok().body(apiResponse);
    }

//    @PostMapping("/refresh-token")
//    public void refreshToken(HttpServletRequest request, HttpServletResponse response, @RequestBody String refreshToken) {
//        service.refreshToken(request, response);
//    }

}