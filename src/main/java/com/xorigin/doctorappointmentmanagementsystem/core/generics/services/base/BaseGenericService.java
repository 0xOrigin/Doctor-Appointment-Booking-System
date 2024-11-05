package com.xorigin.doctorappointmentmanagementsystem.core.generics.services.base;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.base.BaseGenericRepository;

public abstract class BaseGenericService<
        T,
        ID,
        R extends BaseGenericRepository<T, ID>,
        M extends BaseGenericMapper<T, ?, ?, CreateDTO, UpdateDTO>,
        CreateDTO,
        UpdateDTO
    > {

    private final UserProvider userProvider;
    private final R repository;
    private final M mapper;

    public BaseGenericService(UserProvider userProvider, R repository, M mapper) {
        this.userProvider = userProvider;
        this.repository = repository;
        this.mapper = mapper;
    }

    public R getRepository() {
        return repository;
    }

    public M getMapper() {
        return mapper;
    }

    protected T preCreate(T instance, CreateDTO dto) {
        return instance;
    }

    protected T preUpdate(T instance, UpdateDTO dto) {
        return instance;
    }

    protected T postCreate(T instance, CreateDTO dto) {
        return instance;
    }

    protected T postUpdate(T instance, UpdateDTO dto) {
        return instance;
    }

    public T create(CreateDTO dto) {
        T instance = mapper.toEntity(dto);
        instance = preCreate(instance, dto);
        instance = repository.save(instance);
        instance = postCreate(instance, dto);
        return instance;
    }

    public T update(UpdateDTO dto, ID id) {
        T instance = repository.findById(id)
                .orElseThrow();
        instance = preUpdate(instance, dto);
        mapper.updateEntityFromDto(dto, instance);
        instance = repository.save(instance);
        instance = postUpdate(instance, dto);
        return instance;
    }

}
