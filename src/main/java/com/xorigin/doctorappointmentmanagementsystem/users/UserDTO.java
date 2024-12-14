package com.xorigin.doctorappointmentmanagementsystem.users;

import io.github._0xorigin.storageawaremultipartfile.StorageAwareMultipartFile;
import io.github._0xorigin.storageawaremultipartfile.annotations.ValidImage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
    @Email
    @NotEmpty
    private String email;
    private String password;
    private Role role;
    private Boolean isActive;
    private Instant createdAt;
//    private User createdBy;

    @ValidImage
    private StorageAwareMultipartFile picture;

//    private Doctor doctor;

}
