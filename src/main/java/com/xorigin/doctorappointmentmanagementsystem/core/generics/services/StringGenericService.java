package com.xorigin.doctorappointmentmanagementsystem.core.generics.services;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.StringGenericRepository;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.base.BaseGenericService;
import org.springframework.data.jpa.domain.Specification;

public abstract class StringGenericService<
        T,
        R extends StringGenericRepository<T>,
        M extends BaseGenericMapper<T, ?, ?, CreateDTO, UpdateDTO, PartialUpdateDTO>,
        CreateDTO,
        UpdateDTO,
        PartialUpdateDTO
    > extends BaseGenericService<T, String, R, M, CreateDTO, UpdateDTO, PartialUpdateDTO> {

    public StringGenericService(UserProvider userProvider, R repository, M mapper, Specification<T> spec) {
        super(userProvider, repository, mapper, spec);
    }

    public StringGenericService(UserProvider userProvider, R repository, M mapper) {
        super(userProvider, repository, mapper);
    }

}
