package com.xorigin.doctorappointmentmanagementsystem.auth;

import lombok.Data;

@Data
public class AuthResponseDTO {

    private String accessToken;
    private String refreshToken;
    private UserAuthResponseDTO user;

}
