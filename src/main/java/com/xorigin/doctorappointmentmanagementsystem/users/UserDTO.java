package com.xorigin.doctorappointmentmanagementsystem.users;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Data
public class UserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;

}
