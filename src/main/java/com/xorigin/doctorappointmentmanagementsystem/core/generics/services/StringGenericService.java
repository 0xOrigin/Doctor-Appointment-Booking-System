package com.xorigin.doctorappointmentmanagementsystem.core.generics.services;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.StringGenericRepository;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.base.BaseGenericService;

public abstract class StringGenericService<
        T,
        R extends StringGenericRepository<T>,
        M extends BaseGenericMapper<T, ?, ?, CreateDTO, UpdateDTO>,
        CreateDTO,
        UpdateDTO
    > extends BaseGenericService<T, String, R, M, CreateDTO, UpdateDTO> {

    public StringGenericService(UserProvider userProvider, R repository, M mapper) {
        super(userProvider, repository, mapper);
    }
}
