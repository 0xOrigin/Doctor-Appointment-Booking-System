package com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerOptions;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerUtils;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.GenericCrudController;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.StringGenericRepository;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.responses.ResponseFactory;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.StringGenericService;

public abstract class StringGenericCrudController<
        T,
        S extends StringGenericService<
            T,
            ? extends StringGenericRepository<T>,
            ? extends BaseGenericMapper<T, ?, ?, CreateDTO, UpdateDTO, PartialUpdateDTO>,
            CreateDTO,
            UpdateDTO,
            PartialUpdateDTO
        >,
        CreateDTO,
        UpdateDTO,
        PartialUpdateDTO
    > extends GenericCrudController<T, String, S, CreateDTO, UpdateDTO, PartialUpdateDTO> {

    public StringGenericCrudController(
            ControllerOptions options,
            ControllerUtils utils,
            ResponseFactory responseFactory,
            UserProvider userProvider,
            S service
    ) {
        super(options, utils, responseFactory, userProvider, service);
    }

}
