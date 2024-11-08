package com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerOptions;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerUtils;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.UuidGenericRepository;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.UuidSingleDtoGenericService;

public abstract class UuidSingleDtoGenericCrudController<
        T,
        S extends UuidSingleDtoGenericService<
            T,
            ? extends UuidGenericRepository<T>,
            ? extends BaseGenericMapper<T, ?, ?, DTO, DTO, DTO>,
            DTO
        >,
        DTO
    > extends UuidGenericCrudController<T, S, DTO, DTO, DTO> {

    public UuidSingleDtoGenericCrudController(
            ControllerOptions options,
            ControllerUtils utils,
            UserProvider userProvider,
            S service
    ) {
        super(options, utils, userProvider, service);
    }

}
