package com.xorigin.doctorappointmentmanagementsystem.users;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@NoArgsConstructor
@Data
public class UserDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private Boolean isActive;
    private Instant createdAt;
    private User createdBy;

}
