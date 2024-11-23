package com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerOptions;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerUtils;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.GenericCrudController;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.UuidGenericRepository;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.responses.ResponseFactory;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.UuidGenericService;

import java.util.UUID;

public abstract class UuidGenericCrudController<
        T,
        S extends UuidGenericService<
            T,
            ? extends UuidGenericRepository<T>,
            ? extends BaseGenericMapper<T, ?, ?, CreateDTO, UpdateDTO, PartialUpdateDTO>,
            CreateDTO,
            UpdateDTO,
            PartialUpdateDTO
        >,
        CreateDTO,
        UpdateDTO,
        PartialUpdateDTO
    > extends GenericCrudController<T, UUID, S, CreateDTO, UpdateDTO, PartialUpdateDTO> {

    public UuidGenericCrudController(
            ControllerOptions options,
            ControllerUtils utils,
            ResponseFactory responseFactory,
            UserProvider userProvider,
            S service
    ) {
        super(options, utils, responseFactory, userProvider, service);
    }

}
