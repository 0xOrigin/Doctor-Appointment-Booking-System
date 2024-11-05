package com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerOptions;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerUtils;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.UuidGenericRepository;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.UuidSingleDtoGenericService;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public abstract class UuidSingleDtoGenericCrudController<
        T,
        S extends UuidSingleDtoGenericService<
            T,
            ? extends UuidGenericRepository<T>,
            ? extends BaseGenericMapper<T, ?, ?, DTO, DTO>,
            DTO
        >,
        DTO
    > extends UuidGenericCrudController<T, S, DTO, DTO> {

    public UuidSingleDtoGenericCrudController(
            ControllerOptions options,
            ControllerUtils utils,
            UserProvider userProvider,
            Optional<? extends Specification<T>> spec,
            S service
    ) {
        super(options, utils, userProvider, spec, service);
    }
}
