package com.xorigin.doctorappointmentmanagementsystem.core.generics.services.base;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.base.BaseGenericRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class BaseGenericService<
        T,
        ID,
        R extends BaseGenericRepository<T, ID>,
        M extends BaseGenericMapper<T, ?, ?, CreateDTO, UpdateDTO, PartialUpdateDTO>,
        CreateDTO,
        UpdateDTO,
        PartialUpdateDTO
    > {

    private final UserProvider userProvider;
    private final R repository;
    private final M mapper;
    private final Specification<T> spec;
    private final MessageByLocaleService messageByLocaleService;

    public BaseGenericService(
            UserProvider userProvider,
            R repository,
            M mapper,
            Specification<T> spec,
            MessageByLocaleService messageByLocaleService
    ) {
        this.userProvider = userProvider;
        this.repository = repository;
        this.mapper = mapper;
        this.spec = spec;
        this.messageByLocaleService = messageByLocaleService;
    }

    public BaseGenericService(
            UserProvider userProvider,
            R repository,
            M mapper,
            Specification<T> spec
    ) {
        this(userProvider, repository, mapper, spec, null);
    }

    public BaseGenericService(
            UserProvider userProvider,
            R repository,
            M mapper,
            MessageByLocaleService messageByLocaleService
    ) {
        this(userProvider, repository, mapper, null, messageByLocaleService);
    }

    public BaseGenericService(
            UserProvider userProvider,
            R repository,
            M mapper
    ) {
        this(userProvider, repository, mapper, null, null);
    }

    public UserProvider getUserProvider() {
        return userProvider;
    }

    public R getRepository() {
        return repository;
    }

    public M getMapper() {
        return mapper;
    }

    public Optional<Specification<T>> getSpec() {
        return Optional.ofNullable(spec);
    }

    public Optional<MessageByLocaleService> getMessageByLocaleService() {
        return Optional.ofNullable(messageByLocaleService);
    }

    protected void preCreate(T instance, CreateDTO dto) {}

    protected void preUpdate(T instance, UpdateDTO dto) {}

    protected void prePartialUpdate(T instance, PartialUpdateDTO dto) {}

    protected void postCreate(T instance, CreateDTO dto) {}

    protected void postUpdate(T instance, UpdateDTO dto) {}

    protected void postPartialUpdate(T instance, PartialUpdateDTO dto) {}

    protected EntityNotFoundException throwNotFoundException() {
        return new EntityNotFoundException("Not found");
    }

    protected List<?> mapInstancesToList(List<T> instances, Function<T, ?> mapper) {
        return instances.stream().map(mapper).toList();
    }

    protected Object mapInstanceToDto(T instance, Function<T, ?> mapper) {
        return mapper.apply(instance);
    }

    public Long getCount() {
        return getRepository().count(getSpec().orElse(null));
    }

    public Page<T> getPage(PageRequest pageRequest) {
        return getRepository().findAll(getSpec().orElse(null), pageRequest);
    }

    public List<T> findAll(Sort sort) {
        return getRepository().findAll(getSpec().orElse(null), sort);
    }

    public T findById(ID id) {
        return getRepository().findById(id)
                .orElseThrow(this::throwNotFoundException);
    }

    public List<?> getMappedFindAll(List<T> instances) {
        return mapInstancesToList(instances, getMapper()::toListDto);
    }

    public List<?> getMappedFindAll(Page<T> page) {
        return mapInstancesToList(page.getContent(), getMapper()::toListDto);
    }

    public Object getMappedFindOne(T instance) {
        return mapInstanceToDto(instance, getMapper()::toRetrieveDto);
    }

    protected T getInstanceFromCreateDto(CreateDTO dto) {
        return getMapper().toEntity(dto);
    }

    protected void updateInstanceFromUpdateDto(T instance, UpdateDTO dto) {
        getMapper().updateEntityFromUpdateDto(instance, dto);
    }

    protected void updateInstanceFromPartialUpdateDto(T instance, PartialUpdateDTO dto) {
        getMapper().updateEntityFromPartialUpdateDto(instance, dto);
    }

    public T create(CreateDTO dto) {
        T instance = getInstanceFromCreateDto(dto);
        preCreate(instance, dto);
        instance = getRepository().save(instance);
        postCreate(instance, dto);
        return instance;
    }

    public T update(T instance, UpdateDTO dto) {
        preUpdate(instance, dto);
        updateInstanceFromUpdateDto(instance, dto);
        instance = getRepository().save(instance);
        postUpdate(instance, dto);
        return instance;
    }

    public T partialUpdate(T instance, PartialUpdateDTO dto) {
        prePartialUpdate(instance, dto);
        updateInstanceFromPartialUpdateDto(instance, dto);
        instance = getRepository().save(instance);
        postPartialUpdate(instance, dto);
        return instance;
    }

    public void delete(T instance) {
        getRepository().delete(instance);
    }

}
