package com.xorigin.doctorappointmentmanagementsystem.auth;

import com.xorigin.doctorappointmentmanagementsystem.users.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterDTO {

    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;
    private String firstName;
    private String lastName;
    @NotNull
    private Role role;

}
