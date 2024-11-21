package com.xorigin.doctorappointmentmanagementsystem.core.generics.services;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.UuidGenericRepository;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.base.MessageByLocaleService;
import org.springframework.data.jpa.domain.Specification;

public abstract class UuidSingleDtoGenericService<
        T,
        R extends UuidGenericRepository<T>,
        M extends BaseGenericMapper<T, ?, ?, DTO, DTO, DTO>,
        DTO
    > extends UuidGenericService<T, R, M, DTO, DTO, DTO> {

    public UuidSingleDtoGenericService(
            UserProvider userProvider,
            R repository,
            M mapper,
            Specification<T> spec,
            MessageByLocaleService messageByLocaleService
    ) {
        super(userProvider, repository, mapper, spec, messageByLocaleService);
    }

    public UuidSingleDtoGenericService(
            UserProvider userProvider,
            R repository,
            M mapper,
            Specification<T> spec
    ) {
        super(userProvider, repository, mapper, spec);
    }

    public UuidSingleDtoGenericService(
            UserProvider userProvider,
            R repository,
            M mapper,
            MessageByLocaleService messageByLocaleService
    ) {
        super(userProvider, repository, mapper, messageByLocaleService);
    }

    public UuidSingleDtoGenericService(
            UserProvider userProvider,
            R repository,
            M mapper
    ) {
        super(userProvider, repository, mapper);
    }

}
