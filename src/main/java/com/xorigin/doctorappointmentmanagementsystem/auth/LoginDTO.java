package com.xorigin.doctorappointmentmanagementsystem.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDTO {

    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;

}
