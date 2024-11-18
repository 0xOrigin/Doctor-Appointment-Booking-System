package com.xorigin.doctorappointmentmanagementsystem.users;

import com.xorigin.doctorappointmentmanagementsystem.core.filefields.StorageAwareMultipartFile;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@NoArgsConstructor
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

    private StorageAwareMultipartFile picture;

}
