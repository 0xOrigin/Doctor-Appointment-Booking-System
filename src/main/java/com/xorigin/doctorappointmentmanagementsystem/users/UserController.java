package com.xorigin.doctorappointmentmanagementsystem.users;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserRepo userRepo;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe")
                .password("password")
                .role(Role.DOCTOR)
                .build();

//        userRepo.save(user);
        return ResponseEntity.ok().body(userRepo.findAll());
    }
}
