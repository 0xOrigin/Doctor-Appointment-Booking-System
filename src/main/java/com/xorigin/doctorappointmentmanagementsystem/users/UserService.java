package com.xorigin.doctorappointmentmanagementsystem.users;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.UuidSingleDtoGenericService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends UuidSingleDtoGenericService<User, UserRepository, UserMapper, UserDTO> {

    public UserService(UserProvider userProvider, UserRepository repository, UserMapper mapper) {
        super(userProvider, repository, mapper);
    }

}
