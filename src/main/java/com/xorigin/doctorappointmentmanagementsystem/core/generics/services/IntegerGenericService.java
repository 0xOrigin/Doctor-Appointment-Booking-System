package com.xorigin.doctorappointmentmanagementsystem.core.generics.services;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.IntegerGenericRepository;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.base.BaseGenericService;
import org.springframework.data.jpa.domain.Specification;

public abstract class IntegerGenericService<
        T,
        R extends IntegerGenericRepository<T>,
        M extends BaseGenericMapper<T, ?, ?, CreateDTO, UpdateDTO, PartialUpdateDTO>,
        CreateDTO,
        UpdateDTO,
        PartialUpdateDTO
    > extends BaseGenericService<T, Integer, R, M, CreateDTO, UpdateDTO, PartialUpdateDTO> {

    public IntegerGenericService(UserProvider userProvider, R repository, M mapper, Specification<T> spec) {
        super(userProvider, repository, mapper, spec);
    }

    public IntegerGenericService(UserProvider userProvider, R repository, M mapper) {
        super(userProvider, repository, mapper);
    }

}
