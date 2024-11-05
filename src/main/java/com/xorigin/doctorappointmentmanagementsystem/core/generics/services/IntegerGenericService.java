package com.xorigin.doctorappointmentmanagementsystem.core.generics.services;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.IntegerGenericRepository;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.base.BaseGenericService;

public abstract class IntegerGenericService<
        T,
        R extends IntegerGenericRepository<T>,
        M extends BaseGenericMapper<T, ?, ?, CreateDTO, UpdateDTO>,
        CreateDTO,
        UpdateDTO
    > extends BaseGenericService<T, Integer, R, M, CreateDTO, UpdateDTO> {

    public IntegerGenericService(UserProvider userProvider, R repository, M mapper) {
        super(userProvider, repository, mapper);
    }
}
