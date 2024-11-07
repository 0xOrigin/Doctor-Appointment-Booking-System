package com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerOptions;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerUtils;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.IntegerGenericRepository;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.IntegerSingleDtoGenericService;

public abstract class IntegerSingleDtoGenericCrudController<
        T,
        S extends IntegerSingleDtoGenericService<
            T,
            ? extends IntegerGenericRepository<T>,
            ? extends BaseGenericMapper<T, ?, ?, DTO, DTO>,
            DTO
        >,
        DTO
    > extends IntegerGenericCrudController<T, S, DTO, DTO> {

    public IntegerSingleDtoGenericCrudController(
            ControllerOptions options,
            ControllerUtils utils,
            UserProvider userProvider,
            S service
    ) {
        super(options, utils, userProvider, service);
    }

}
