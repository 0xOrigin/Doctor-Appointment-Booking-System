package com.xorigin.doctorappointmentmanagementsystem.auth;

import com.xorigin.doctorappointmentmanagementsystem.users.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    private Role role;

}
