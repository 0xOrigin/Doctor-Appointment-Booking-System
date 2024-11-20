package com.xorigin.doctorappointmentmanagementsystem.core.generics.services;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.LongGenericRepository;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.base.BaseGenericService;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.domain.Specification;

public abstract class LongGenericService<
        T,
        R extends LongGenericRepository<T>,
        M extends BaseGenericMapper<T, ?, ?, CreateDTO, UpdateDTO, PartialUpdateDTO>,
        CreateDTO,
        UpdateDTO,
        PartialUpdateDTO
    > extends BaseGenericService<T, Long, R, M, CreateDTO, UpdateDTO, PartialUpdateDTO> {

    public LongGenericService(UserProvider userProvider, R repository, M mapper, Specification<T> spec, MessageSource messageSource) {
        super(userProvider, repository, mapper, spec, messageSource);
    }

    public LongGenericService(UserProvider userProvider, R repository, M mapper, Specification<T> spec) {
        super(userProvider, repository, mapper, spec);
    }

    public LongGenericService(UserProvider userProvider, R repository, M mapper, MessageSource messageSource) {
        super(userProvider, repository, mapper, messageSource);
    }

    public LongGenericService(UserProvider userProvider, R repository, M mapper) {
        super(userProvider, repository, mapper);
    }

}
