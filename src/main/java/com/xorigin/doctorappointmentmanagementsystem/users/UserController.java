package com.xorigin.doctorappointmentmanagementsystem.users;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.UuidSingleDtoGenericCrudController;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerOptions;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerUtils;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController extends UuidSingleDtoGenericCrudController<User, UserService, UserDTO> {

    public UserController(
            @Qualifier("defaultPaginatedOptions") ControllerOptions options,
            ControllerUtils utils,
            UserProvider userProvider,
            Optional<Specification<User>> spec,
            UserService service
    ) {
        super(options, utils, userProvider, spec, service);
    }

}
