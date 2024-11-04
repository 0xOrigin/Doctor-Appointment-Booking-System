package com.xorigin.doctorappointmentmanagementsystem.users;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.GenericService;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User, UserRepo, UserDTO, UserMapper> {

    public UserService(AuditorAware<User> auditorAware, UserRepo repository, UserMapper mapper) {
        super(auditorAware, repository, mapper);
    }

}
