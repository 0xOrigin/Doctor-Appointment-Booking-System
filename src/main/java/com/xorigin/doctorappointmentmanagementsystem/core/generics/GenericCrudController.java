package com.xorigin.doctorappointmentmanagementsystem.core.generics;

import com.xorigin.doctorappointmentmanagementsystem.core.responses.ApiResponse;
import com.xorigin.doctorappointmentmanagementsystem.core.responses.BaseMetaResponse;
import com.xorigin.doctorappointmentmanagementsystem.core.responses.BasePaginationResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class GenericCrudController<T, ID, D, S extends GenericService<T, D, ? extends GenericRepository<T, ID>>> {

    private final ControllerOptions options;
    private final ControllerUtils utils;
    private final Specification<T> spec;
    private final S service;

    public GenericCrudController(
            ControllerOptions options,
            ControllerUtils utils,
            Specification<T> spec,
            S service
    ) {
        this.options = options;
        this.utils = utils;
        this.spec = spec;
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> findAll(HttpServletRequest request, Pageable pageable) {
        if (!options.isFindAllAllowed()) {
            utils.methodNotAllowed(request.getMethod());
        }

        if (!options.isPaginationEnabled()) {
            Long count = service.getRepository().count(spec);;
            List<T> instances = service.getRepository().findAll(spec);
            ApiResponse<?, ?, ?> apiResponse = getApiResponse("Success", instances, count, null);
            return ResponseEntity.ok().body(apiResponse);
        }

        PageRequest pageRequest = PageRequest.of(utils.getPageNumber(pageable), utils.getPageSize(pageable, options));
        Page<T> page = service.getRepository().findAll(spec, pageRequest);
        ApiResponse<?, ?, ?> apiResponse = getApiResponse("Success", page.getContent(), page.getTotalElements(), page);
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(HttpServletRequest request, @PathVariable ID id) {
        if (!options.isFindOneAllowed()) {
            utils.methodNotAllowed(request.getMethod());
        }

        return service.getRepository().findById(id)
                .map(instance -> ResponseEntity.ok().body(getApiResponse("Success", instance, null, null)))
                .orElse(ResponseEntity.notFound().build());
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(HttpServletRequest request, @PathVariable ID id) {
        if (!options.isDeleteAllowed()) {
            utils.methodNotAllowed(request.getMethod());
        }

        if (!service.getRepository().existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        service.getRepository().deleteById(id);
        return ResponseEntity.noContent().build();
    }

    protected <M extends BaseMetaResponse> M getMetaObject(Long count){
        Class<? extends BaseMetaResponse> metaClass = utils.getMetaClass();
        try {
            return (M) metaClass.getConstructor(Long.class).newInstance(count);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected <P extends BasePaginationResponse> P getPaginationObject(Page<T> page){
        Class<? extends BasePaginationResponse> paginationClass = utils.getPaginationClass();
        try {
            return (P) paginationClass.getConstructor(Page.class).newInstance(page);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected <N> ApiResponse getApiResponse(
            String message,
            N data,
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

}
