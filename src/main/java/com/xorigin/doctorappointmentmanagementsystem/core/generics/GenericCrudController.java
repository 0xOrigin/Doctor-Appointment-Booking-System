package com.xorigin.doctorappointmentmanagementsystem.core.generics;

import com.xorigin.doctorappointmentmanagementsystem.core.responses.ApiResponse;
import com.xorigin.doctorappointmentmanagementsystem.core.responses.BaseMetaResponse;
import com.xorigin.doctorappointmentmanagementsystem.core.responses.BasePaginationResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

public abstract class GenericCrudController<T, ID, D, S extends GenericService<T, D, ? extends GenericRepository<T, ID>>> {

    private final ControllerOptions options;
    private final Specification<T> spec;
    private final S service;

    public GenericCrudController(
            ControllerOptions options,
            Specification<T> spec,
            S service
    ) {
        this.options = options;
        this.spec = spec;
        this.service = service;
    }

    protected void methodNotAllowed(String method) {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, method + " Method not allowed");
    }

    protected Class<? extends BaseMetaResponse> getMetaClass() {
        return BaseMetaResponse.class;
    }

    protected Class<? extends BasePaginationResponse> getPaginationClass() {
        return BasePaginationResponse.class;
    }

    protected <M extends BaseMetaResponse> M getMetaObject(Long count){
        Class<? extends BaseMetaResponse> metaClass = getMetaClass();
        try {
            return (M) metaClass.getConstructor(Long.class).newInstance(count);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected <P extends BasePaginationResponse> P getPaginationObject(Page<T> page){
        Class<? extends BasePaginationResponse> paginationClass = getPaginationClass();
        try {
            return (P) paginationClass.getConstructor(Page.class).newInstance(page);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected <DATA> ApiResponse getApiResponse(
            String message,
            DATA data,
            Long count,
            Page<T> page
    ) {
        return ApiResponse
                .builder()
                .message(message)
                .meta(getMetaObject(count))
                .pagination(getPaginationObject(page))
                .data(data)
                .build();
    }

    protected Long getCount(Specification<T> spec) {
        return service.getRepository().count(spec);
    }

    private int getPageNumber(Pageable pageable) {
        return pageable.getPageNumber() > 0 ? pageable.getPageNumber() - 1 : pageable.getPageNumber();
    }

    @GetMapping
    public ResponseEntity<?> findAll(HttpServletRequest request, Pageable pageable) {
        if (!options.isFindAllAllowed()) {
            methodNotAllowed(request.getMethod());
        }

        if (!options.isPaginationEnabled()) {
            Long count = getCount(spec);
            List<T> instances = service.getRepository().findAll(spec);
            ApiResponse<?, ?, ?> apiResponse = getApiResponse("Success", instances, count, null);
            return ResponseEntity.ok().body(apiResponse);
        }

        PageRequest pageRequest = PageRequest.of(getPageNumber(pageable), options.getPageSize());
        Page<T> page = service.getRepository().findAll(spec, pageRequest);

//        Page<T> page = service.getRepository().findAll(spec, pageable);

        ApiResponse<?, ?, ?> apiResponse = getApiResponse("Success",
                page.getContent(),
                page.getTotalElements(), page);
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(HttpServletRequest request, @PathVariable ID id) {
        if (!options.isFindOneAllowed()) {
            methodNotAllowed(request.getMethod());
        }

        Optional<T> instance = service.getRepository().findById(id);

        if (instance.isPresent()){
            ApiResponse<?, ?, ?> apiResponse = getApiResponse("Success", instance, null, null);
            return ResponseEntity.ok().body(apiResponse);
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }
//
//    @PostMapping
//    public ResponseEntity<D> create(HttpServletRequest request,@Valid @RequestBody D dto) {
//        if (!options.isCreateAllowed()) {
//            methodNotAllowed(request.getMethod());
//        }
//
//        T entity = toEntity(dto);
//        return ResponseEntity.ok(toDto(repository.save(entity)));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<D> update(HttpServletRequest request, @PathVariable ID id, @Valid @RequestBody D dto) {
//        if (!options.isUpdateAllowed()) {
//            methodNotAllowed(request.getMethod());
//        }
//
//        if (!repository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
//        T entity = toEntity(dto);
//        return ResponseEntity.ok(toDto(repository.save(entity)));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(HttpServletRequest request, @PathVariable ID id) {
//        if (!options.isDeleteAllowed()) {
//            methodNotAllowed(request.getMethod());
//        }
//
//        if (!repository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
//        repository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }

    // Abstract methods for entity-DTO transformations
//    protected abstract D toDto(T entity);
//    protected abstract T toEntity(D dto);
}