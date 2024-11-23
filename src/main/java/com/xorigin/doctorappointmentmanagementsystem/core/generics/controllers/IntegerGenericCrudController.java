package com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerOptions;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerUtils;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.GenericCrudController;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.IntegerGenericRepository;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.responses.ResponseFactory;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.IntegerGenericService;

public abstract class IntegerGenericCrudController<
        T,
        S extends IntegerGenericService<
            T,
            ? extends IntegerGenericRepository<T>,
            ? extends BaseGenericMapper<T, ?, ?, CreateDTO, UpdateDTO, PartialUpdateDTO>,
            CreateDTO,
            UpdateDTO,
            PartialUpdateDTO
        >,
        CreateDTO,
        UpdateDTO,
        PartialUpdateDTO
    > extends GenericCrudController<T, Integer, S, CreateDTO, UpdateDTO, PartialUpdateDTO> {

    public IntegerGenericCrudController(
            ControllerOptions options,
            ControllerUtils utils,
            ResponseFactory responseFactory,
            UserProvider userProvider,
            S service
    ) {
        super(options, utils, responseFactory, userProvider, service);
    }

}
