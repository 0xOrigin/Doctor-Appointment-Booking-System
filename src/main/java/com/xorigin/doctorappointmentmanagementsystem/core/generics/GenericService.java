package com.xorigin.doctorappointmentmanagementsystem.core.generics;

import com.xorigin.doctorappointmentmanagementsystem.users.User;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public abstract class GenericService<T, R extends GenericRepository<T, ?>, DTO, M extends GenericMapper<T, DTO>> {

    private final AuditorAware<User> auditorAware;
    private final R repository;
    private final M mapper;

    public GenericService(AuditorAware<User> auditorAware, R repository, M mapper) {
        this.auditorAware = auditorAware;
        this.repository = repository;
        this.mapper = mapper;
    }

    protected Optional<User> getCurrentUser() {
        return auditorAware.getCurrentAuditor();
    }

    protected R getRepository() {
        return repository;
    }

    protected M getMapper() {
        return mapper;
    }


}
