package com.xorigin.doctorappointmentmanagementsystem.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class AuthResponseDTO {

    private String accessToken;
    private String refreshToken;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserAuthResponseDTO user;

}
