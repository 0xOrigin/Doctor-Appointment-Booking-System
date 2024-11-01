package com.xorigin.doctorappointmentmanagementsystem.users;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.GenericService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User, User, UserRepo> {

    public UserService(User currentUser, UserRepo repository, User dto) {
        super(currentUser, repository, dto);
    }
}
