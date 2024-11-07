package com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerOptions;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerUtils;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.StringGenericRepository;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.StringSingleDtoGenericService;

public abstract class StringSingleDtoGenericCrudController<
        T,
        S extends StringSingleDtoGenericService<
            T,
            ? extends StringGenericRepository<T>,
            ? extends BaseGenericMapper<T, ?, ?, DTO, DTO>,
            DTO
        >,
        DTO
    > extends StringGenericCrudController<T, S, DTO, DTO> {

    public StringSingleDtoGenericCrudController(
            ControllerOptions options,
            ControllerUtils utils,
            UserProvider userProvider,
            S service
    ) {
        super(options, utils, userProvider, service);
    }

}
