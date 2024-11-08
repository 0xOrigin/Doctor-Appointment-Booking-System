package com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerOptions;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerUtils;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.LongGenericRepository;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.LongSingleDtoGenericService;

public abstract class LongSingleDtoGenericCrudController<
        T,
        S extends LongSingleDtoGenericService<
            T,
            ? extends LongGenericRepository<T>,
            ? extends BaseGenericMapper<T, ?, ?, DTO, DTO, DTO>,
            DTO
        >,
        DTO
    > extends LongGenericCrudController<T, S, DTO, DTO, DTO> {

    public LongSingleDtoGenericCrudController(
            ControllerOptions options,
            ControllerUtils utils,
            UserProvider userProvider,
            S service
    ) {
        super(options, utils, userProvider, service);
    }

}
