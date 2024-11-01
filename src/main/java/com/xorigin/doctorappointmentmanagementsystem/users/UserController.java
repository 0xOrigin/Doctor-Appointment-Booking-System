package com.xorigin.doctorappointmentmanagementsystem.users;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.ControllerOptions;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.GenericCrudController;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController extends GenericCrudController<User, UUID, User, UserService> {

    public UserController(ControllerOptions options, Specification<User> spec, UserService service) {
        super(options, spec, service);
    }

}
