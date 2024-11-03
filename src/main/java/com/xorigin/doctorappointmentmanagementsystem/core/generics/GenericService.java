package com.xorigin.doctorappointmentmanagementsystem.core.generics;

import com.xorigin.doctorappointmentmanagementsystem.users.User;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public abstract class GenericService<T, R extends GenericRepository<T, ?>, DTO> {

    private final AuditorAware<User> auditorAware;
    private final R repository;
    private final DTO dto;

    public GenericService(AuditorAware<User> auditorAware, R repository, DTO dto) {
        this.auditorAware = auditorAware;
        this.repository = repository;
        this.dto = dto;
    }

    public Optional<User> getCurrentUser() {
        return auditorAware.getCurrentAuditor();
    }

    public R getRepository() {
        return repository;
    }

    public DTO getDto() {
        return dto;
    }

    public void create() {
    }

}
