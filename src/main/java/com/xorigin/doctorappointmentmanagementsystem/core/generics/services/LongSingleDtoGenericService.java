package com.xorigin.doctorappointmentmanagementsystem.core.generics.services;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.LongGenericRepository;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.base.MessageByLocaleService;
import org.springframework.data.jpa.domain.Specification;

public abstract class LongSingleDtoGenericService<
        T,
        R extends LongGenericRepository<T>,
        M extends BaseGenericMapper<T, ?, ?, DTO, DTO, DTO>,
        DTO
    > extends LongGenericService<T, R, M, DTO, DTO, DTO> {

    public LongSingleDtoGenericService(
            UserProvider userProvider,
            R repository,
            M mapper,
            Specification<T> spec,
            MessageByLocaleService messageByLocaleService
    ) {
        super(userProvider, repository, mapper, spec, messageByLocaleService);
    }

    public LongSingleDtoGenericService(
            UserProvider userProvider,
            R repository,
            M mapper,
            Specification<T> spec
    ) {
        super(userProvider, repository, mapper, spec);
    }

    public LongSingleDtoGenericService(
            UserProvider userProvider,
            R repository,
            M mapper,
            MessageByLocaleService messageByLocaleService
    ) {
        super(userProvider, repository, mapper, messageByLocaleService);
    }

    public LongSingleDtoGenericService(
            UserProvider userProvider,
            R repository,
            M mapper
    ) {
        super(userProvider, repository, mapper);
    }

}
