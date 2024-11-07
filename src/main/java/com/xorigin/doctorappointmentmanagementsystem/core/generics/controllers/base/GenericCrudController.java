package com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseGenericMapper;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.base.BaseGenericRepository;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.base.BaseGenericService;
import com.xorigin.doctorappointmentmanagementsystem.core.responses.ApiResponse;
import com.xorigin.doctorappointmentmanagementsystem.core.responses.BaseMetaResponse;
import com.xorigin.doctorappointmentmanagementsystem.core.responses.BasePaginationResponse;
import com.xorigin.doctorappointmentmanagementsystem.core.responses.StandardApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class GenericCrudController<
        T,
        ID,
        S extends BaseGenericService<
                T,
                ID,
                ? extends BaseGenericRepository<T, ID>,
                ? extends BaseGenericMapper<T, ?, ?, CreateDTO, UpdateDTO>,
                CreateDTO,
                UpdateDTO
        >,
        CreateDTO,
        UpdateDTO
    > {

    private final ControllerOptions options;
    private final ControllerUtils utils;
    private final UserProvider userProvider;
    private final S service;

    public GenericCrudController(
            ControllerOptions options,
            ControllerUtils utils,
            UserProvider userProvider,
            S service
    ) {
        this.options = options;
        this.utils = utils;
        this.userProvider = userProvider;
        this.service = service;
    }

    public ControllerOptions getOptions() {
        return options;
    }

    public ControllerUtils getUtils() {
        return utils;
    }

    public UserProvider getUserProvider() {
        return userProvider;
    }

    public S getService() {
        return service;
    }

    public PageRequest getPageRequest(Pageable pageable) {
        return PageRequest.of(getUtils().getPageNumber(pageable), getUtils().getPageSize(pageable, getOptions()));
    }

    protected T performCreate(CreateDTO dto) {
        return getService().create(dto);
    }

    protected T performUpdate(ID id, UpdateDTO dto) {
        T instance = getService().findById(id);
        return getService().update(instance, dto);
    }

    protected void performDelete(ID id) {
        T instance = getService().findById(id);
        getService().delete(instance);
    }

    @GetMapping
    public ResponseEntity<?> findAll(HttpServletRequest request, Pageable pageable) {
        if (!getOptions().isFindAllAllowed()) {
            getUtils().methodNotAllowed(request.getMethod());
        }

        if (!getOptions().isPaginationEnabled()) {
            Long count = getService().getCount();
            List<T> instances = getService().findAll();
            List<?> mappedInstances = getService().getMappedFindAll(instances);
            ApiResponse<?> apiResponse = getApiResponse("Success", mappedInstances, count, null);
            return ResponseEntity.ok().body(apiResponse);
        }

        Page<T> page = getService().getPage(getPageRequest(pageable));
        ApiResponse<?> apiResponse = getApiResponse(
                "Success",
                getService().getMappedFindAll(page),
                page.getTotalElements(),
                page
        );
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(HttpServletRequest request, @PathVariable ID id) {
        if (!getOptions().isFindOneAllowed()) {
            getUtils().methodNotAllowed(request.getMethod());
        }

        T instance = getService().findById(id);
        ApiResponse<?> apiResponse = getApiResponse("Success", getService().getMappedFindOne(instance), null, null);
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping
    public ResponseEntity<?> create(HttpServletRequest request, @Valid @RequestBody CreateDTO dto) {
        if (!getOptions().isCreateAllowed()) {
            getUtils().methodNotAllowed(request.getMethod());
        }

        T instance = performCreate(dto);
        ApiResponse<?> apiResponse = getApiResponse("Success", getService().getMappedFindOne(instance), null, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(HttpServletRequest request, @PathVariable ID id, @Valid @RequestBody UpdateDTO dto) {
        if (!getOptions().isUpdateAllowed()) {
            getUtils().methodNotAllowed(request.getMethod());
        }

        T instance = performUpdate(id, dto);
        ApiResponse<?> apiResponse = getApiResponse("Success", getService().getMappedFindOne(instance), null, null);
        return ResponseEntity.ok().body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(HttpServletRequest request, @PathVariable ID id) {
        if (!getOptions().isDeleteAllowed()) {
            getUtils().methodNotAllowed(request.getMethod());
        }

        performDelete(id);
        return ResponseEntity.noContent().build();
    }

    protected <N> ApiResponse<N> getApiResponse(
            String message,
            N data,
            Long count,
            Page<T> page
    ) {
        return (ApiResponse<N>) StandardApiResponse
                .builder()
                .message(message)
                .meta(BaseMetaResponse.builder().count(count).build())
                .pagination(BasePaginationResponse.builder().page(page).build())
                .data(data)
                .build();
    }

}
