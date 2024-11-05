package com.xorigin.doctorappointmentmanagementsystem.core.configs;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.users.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpringSecurityAuditorAware implements AuditorAware<UserDetails> {

    private final UserProvider userProvider;

    public SpringSecurityAuditorAware(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    @Override
    public Optional<UserDetails> getCurrentAuditor() {
        return userProvider.getCurrentUser()
                .filter(User.class::isInstance)
                .map(User.class::cast);
    }
}
