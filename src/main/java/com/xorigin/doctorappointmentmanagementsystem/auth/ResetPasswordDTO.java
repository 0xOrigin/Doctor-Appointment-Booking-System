package com.xorigin.doctorappointmentmanagementsystem.auth;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ResetPasswordDTO {

    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmPassword;

    @AssertTrue(message = "Passwords do not match")
    public boolean isPasswordMatch() {
        return password.equals(confirmPassword);
    }

}
