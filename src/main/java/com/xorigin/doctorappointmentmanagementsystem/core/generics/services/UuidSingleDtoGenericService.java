package com.xorigin.doctorappointmentmanagementsystem.core.generics.services;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.UuidGenericRepository;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.domain.Specification;

public abstract class UuidSingleDtoGenericService<
        T,
        R extends UuidGenericRepository<T>,
        M extends BaseGenericMapper<T, ?, ?, DTO, DTO, DTO>,
        DTO
    > extends UuidGenericService<T, R, M, DTO, DTO, DTO> {

    public UuidSingleDtoGenericService(UserProvider userProvider, R repository, M mapper, Specification<T> spec, MessageSource messageSource) {
        super(userProvider, repository, mapper, spec, messageSource);
    }

    public UuidSingleDtoGenericService(UserProvider userProvider, R repository, M mapper, Specification<T> spec) {
        super(userProvider, repository, mapper, spec);
    }

    public UuidSingleDtoGenericService(UserProvider userProvider, R repository, M mapper, MessageSource messageSource) {
        super(userProvider, repository, mapper, messageSource);
    }

    public UuidSingleDtoGenericService(UserProvider userProvider, R repository, M mapper) {
        super(userProvider, repository, mapper);
    }

}
