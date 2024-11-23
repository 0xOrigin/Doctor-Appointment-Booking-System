package com.xorigin.doctorappointmentmanagementsystem.auth;

import com.xorigin.doctorappointmentmanagementsystem.core.filefields.StorageAwareMultipartFile;
import com.xorigin.doctorappointmentmanagementsystem.users.Role;
import com.xorigin.doctorappointmentmanagementsystem.users.User;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class UserAuthResponseDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private Boolean isActive;
    private Instant createdAt;
    private User createdBy;
    private StorageAwareMultipartFile picture;

}
