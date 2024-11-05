package com.xorigin.doctorappointmentmanagementsystem.core.generics.services;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.IntegerGenericRepository;

public abstract class IntegerSingleDtoGenericService<
        T,
        R extends IntegerGenericRepository<T>,
        M extends BaseGenericMapper<T, ?, ?, DTO, DTO>,
        DTO
    > extends IntegerGenericService<T, R, M, DTO, DTO> {

    public IntegerSingleDtoGenericService(UserProvider userProvider, R repository, M mapper) {
        super(userProvider, repository, mapper);
    }
}
