package com.xorigin.doctorappointmentmanagementsystem.core.generics.services;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.LongGenericRepository;

public abstract class LongSingleDtoGenericService<
        T,
        R extends LongGenericRepository<T>,
        M extends BaseGenericMapper<T, ?, ?, DTO, DTO>,
        DTO
    > extends LongGenericService<T, R, M, DTO, DTO> {

    public LongSingleDtoGenericService(UserProvider userProvider, R repository, M mapper) {
        super(userProvider, repository, mapper);
    }
}
