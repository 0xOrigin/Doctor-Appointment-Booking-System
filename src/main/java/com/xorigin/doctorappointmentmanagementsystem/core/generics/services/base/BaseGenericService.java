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

    protected void preCreate(T instance, CreateDTO dto) {}

    protected void preUpdate(T instance, UpdateDTO dto) {}

    protected void postCreate(T instance, CreateDTO dto) {}

    protected void postUpdate(T instance, UpdateDTO dto) {}

    protected T getInstanceFromCreateDto(CreateDTO dto) {
        return mapper.toEntityFromCreateDto(dto);
    }

    protected T getInstanceFromUpdateDto(UpdateDTO dto) {
        return mapper.toEntityFromUpdateDto(dto);
    }

    protected T getUpdatedInstance(T instance) {
        return mapper.toUpdatedEntity(instance);
    }

    public T create(CreateDTO dto) {
        T instance = getInstanceFromCreateDto(dto);
        preCreate(instance, dto);
        instance = repository.save(instance);
        postCreate(instance, dto);
        return instance;
    }

    public T update(UpdateDTO dto, ID id) {
        T instance = repository.findById(id)
                .orElseThrow();
        preUpdate(instance, dto);
        mapper.update(instance, dto);
        instance = repository.save(instance);
        postUpdate(instance, dto);
        return instance;
    }

}
