package com.xorigin.doctorappointmentmanagementsystem.core.generics.providers;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserProvider {

    <T extends UserDetails> Optional<T> getCurrentUser();

}
