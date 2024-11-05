package com.xorigin.doctorappointmentmanagementsystem.core.generics.services;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.LongGenericRepository;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.base.BaseGenericService;

public abstract class LongGenericService<
        T,
        R extends LongGenericRepository<T>,
        M extends BaseGenericMapper<T, ?, ?, CreateDTO, UpdateDTO>,
        CreateDTO,
        UpdateDTO
    > extends BaseGenericService<T, Long, R, M, CreateDTO, UpdateDTO> {

    public LongGenericService(UserProvider userProvider, R repository, M mapper) {
        super(userProvider, repository, mapper);
    }
}
