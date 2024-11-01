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

import jakarta.validation.Valid;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<?> findAll(HttpServletRequest request, Pageable pageable) {
        if (!options.isFindAllAllowed()) {
            methodNotAllowed(request.getMethod());
        }

        if (!options.isPaginationEnabled()) {
            Long count = service.getRepository().count(spec);
            List<T> instances = service.getRepository().findAll(spec);

            ApiResponse<?, ?, ?> apiResponse = ApiResponse.builder()
                    .message("S")
                    .meta(getMetaObject(count))
                    .data(instances).build();

            return ResponseEntity.ok(apiResponse);
        }

        Long count = service.getRepository().count(spec);
        List<T> instances = service.getRepository().findAll(spec);
//        Page<T> dataPage = service.getRepository().findAll(pageable);
//        List<D> dtos = dataPage.stream().map(this::toDto).toList();
//
//        ApiResponse.Meta meta = new ApiResponse.Meta((int) dataPage.getTotalElements());
//        ApiResponse.Pagination pagination = new ApiResponse.Pagination(
//                dataPage.hasPrevious() ? dataPage.getNumber() - 1 : null,
//                dataPage.hasNext() ? dataPage.getNumber() + 1 : null,
//                dataPage.getTotalPages() - 1
//        );
//
//        ApiResponse<D> response = new ApiResponse<>(200, "Success", meta, pagination, dtos);

        return ResponseEntity.ok(instances);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<D> findOne(HttpServletRequest request, @PathVariable ID id) {
//        if (!options.isFindOneAllowed()) {
//            methodNotAllowed(request.getMethod());
//        }
//
//        Optional<T> entity = service.getRepository().findById(id);
//        return entity.map(value -> ResponseEntity.ok(toDto(value)))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
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