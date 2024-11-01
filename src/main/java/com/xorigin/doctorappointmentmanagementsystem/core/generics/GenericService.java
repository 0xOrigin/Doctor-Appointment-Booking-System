package com.xorigin.doctorappointmentmanagementsystem.core.generics;

import com.xorigin.doctorappointmentmanagementsystem.users.User;

public abstract class GenericService<T, D, R extends GenericRepository<T, ?>> {

    private final User currentUser;
    private final R repository;
    private final D dto;

    public GenericService(User currentUser, R repository, D dto) {
        this.currentUser = currentUser;
        this.repository = repository;
        this.dto = dto;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public R getRepository() {
        return repository;
    }

    public D getDto() {
        return dto;
    }

    public void create() {
    }

}
