package com.xorigin.doctorappointmentmanagementsystem.core.generics.services;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.UuidGenericRepository;

public abstract class UuidSingleDtoGenericService<
        T,
        R extends UuidGenericRepository<T>,
        M extends BaseGenericMapper<T, ?, ?, DTO, DTO>,
        DTO
    > extends UuidGenericService<T, R, M, DTO, DTO> {

    public UuidSingleDtoGenericService(UserProvider userProvider, R repository, M mapper) {
        super(userProvider, repository, mapper);
    }
}
