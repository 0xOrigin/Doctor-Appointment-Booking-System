package com.xorigin.doctorappointmentmanagementsystem.users;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.UuidSingleDtoGenericCrudController;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerOptions;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerUtils;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends UuidSingleDtoGenericCrudController<User, UserService, UserDTO> {

    public UserController(
            @Qualifier("defaultPaginatedOptions") ControllerOptions options,
            ControllerUtils utils,
            UserProvider userProvider,
            UserService service
    ) {
        super(options, utils, userProvider, service);
        getOptions().setPageSize(5);
    }

}
