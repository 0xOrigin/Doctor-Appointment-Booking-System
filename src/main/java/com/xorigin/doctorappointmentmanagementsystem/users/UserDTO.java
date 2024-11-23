package com.xorigin.doctorappointmentmanagementsystem.users;

import com.xorigin.doctorappointmentmanagementsystem.core.filefields.StorageAwareMultipartFile;
import com.xorigin.doctorappointmentmanagementsystem.core.filefields.validation.ValidImage;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Data
public class UserDTO {

    private UUID id;
    private Optional<String> firstName;
    @NotNull
    private String lastName;
    private Optional<String> email;
    private String password;
    private Role role;
    private Boolean isActive;
    private Instant createdAt;
    private User createdBy;

    @ValidImage
    private StorageAwareMultipartFile picture;

}
