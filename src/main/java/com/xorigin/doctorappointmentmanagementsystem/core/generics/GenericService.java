package com.xorigin.doctorappointmentmanagementsystem.core.generics;

import com.xorigin.doctorappointmentmanagementsystem.users.User;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public abstract class GenericService<T, R extends GenericRepository<T, ?>, DTO> {

    private final AuditorAware<User> currentAuditor;
    private final R repository;
    private final DTO dto;

    public GenericService(AuditorAware<User> currentAuditor, R repository, DTO dto) {
        this.currentAuditor = currentAuditor;
        this.repository = repository;
        this.dto = dto;
    }

    public Optional<User> getCurrentUser() {
        return currentAuditor.getCurrentAuditor();
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
