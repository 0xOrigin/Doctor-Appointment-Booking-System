package com.xorigin.doctorappointmentmanagementsystem.core.generics.services;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.StringGenericRepository;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.domain.Specification;

public abstract class StringSingleDtoGenericService<
        T,
        R extends StringGenericRepository<T>,
        M extends BaseGenericMapper<T, ?, ?, DTO, DTO, DTO>,
        DTO
    > extends StringGenericService<T, R, M, DTO, DTO, DTO> {

    public StringSingleDtoGenericService(UserProvider userProvider, R repository, M mapper, Specification<T> spec, MessageSource messageSource) {
        super(userProvider, repository, mapper, spec, messageSource);
    }

    public StringSingleDtoGenericService(UserProvider userProvider, R repository, M mapper, Specification<T> spec) {
        super(userProvider, repository, mapper, spec);
    }

    public StringSingleDtoGenericService(UserProvider userProvider, R repository, M mapper, MessageSource messageSource) {
        super(userProvider, repository, mapper, messageSource);
    }

    public StringSingleDtoGenericService(UserProvider userProvider, R repository, M mapper) {
        super(userProvider, repository, mapper);
    }

}
