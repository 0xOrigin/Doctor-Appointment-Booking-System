package com.xorigin.doctorappointmentmanagementsystem.doctor;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.UuidSingleDtoGenericCrudController;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerOptions;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerUtils;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.responses.ResponseFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
public class DoctorController extends UuidSingleDtoGenericCrudController<Doctor, DoctorService, DoctorDTO> {

    public DoctorController(ControllerOptions options, ControllerUtils utils, ResponseFactory responseFactory, UserProvider userProvider, DoctorService service) {
        super(options, utils, responseFactory, userProvider, service);
    }

}
