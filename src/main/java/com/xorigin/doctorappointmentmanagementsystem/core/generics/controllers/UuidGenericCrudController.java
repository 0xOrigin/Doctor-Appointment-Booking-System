package com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerOptions;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerUtils;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.GenericCrudController;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.UuidGenericRepository;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.UuidGenericService;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

public abstract class UuidGenericCrudController<
        T,
        S extends UuidGenericService<
            T,
            ? extends UuidGenericRepository<T>,
            ? extends BaseGenericMapper<T, ?, ?, CreateDTO, UpdateDTO>,
            CreateDTO,
            UpdateDTO
        >,
        CreateDTO,
        UpdateDTO
    > extends GenericCrudController<T, UUID, S, CreateDTO, UpdateDTO> {

    public UuidGenericCrudController(
            ControllerOptions options,
            ControllerUtils utils,
            UserProvider userProvider,
            Optional<? extends Specification<T>> spec,
            S service
    ) {
        super(options, utils, userProvider, spec, service);
    }
}
