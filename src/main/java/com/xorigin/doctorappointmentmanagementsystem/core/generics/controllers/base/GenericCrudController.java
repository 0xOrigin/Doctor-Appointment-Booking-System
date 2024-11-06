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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
    private final Optional<? extends Specification<T>> spec;
    private final S service;

    public GenericCrudController(
            ControllerOptions options,
            ControllerUtils utils,
            UserProvider userProvider,
            Optional<? extends Specification<T>> spec,
            S service
    ) {
        this.options = options;
        this.utils = utils;
        this.userProvider = userProvider;
        this.spec = spec;
        this.service = service;
    }

    protected Long getCount() {
        return service.getRepository().count(spec.orElse(null));
    }

    protected Page<T> getPage(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(utils.getPageNumber(pageable), utils.getPageSize(pageable, options));
        return service.getRepository().findAll(spec.orElse(null), pageRequest);
    }

    protected List<?> mapInstancesToList(List<T> instances, Function<T, ?> mapper) {
        return instances.stream().map(mapper).toList();
    }

    @GetMapping
    public ResponseEntity<?> list(HttpServletRequest request, Pageable pageable) {
        if (!options.isFindAllAllowed()) {
            utils.methodNotAllowed(request.getMethod());
        }

        if (!options.isPaginationEnabled()) {
            Long count = getCount();
            List<?> instances = mapInstancesToList(service.getRepository().findAll(spec.orElse(null)), service.getMapper()::toListDto);
            ApiResponse<?> apiResponse = getApiResponse("Success", instances, count, null);
            return ResponseEntity.ok().body(apiResponse);
        }

        Page<T> page = getPage(pageable);
        ApiResponse<?> apiResponse = getApiResponse(
                "Success",
                mapInstancesToList(page.getContent(), service.getMapper()::toListDto),
                page.getTotalElements(),
                page
        );
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieve(HttpServletRequest request, @PathVariable ID id) {
        if (!options.isFindOneAllowed()) {
            utils.methodNotAllowed(request.getMethod());
        }

        return service.getRepository().findById(id)
                .map(instance -> ResponseEntity.ok().body(getApiResponse("Success", instance, null, null)))
                .orElse(ResponseEntity.notFound().build());
    }

//    @PostMapping
//    public ResponseEntity<?> create(HttpServletRequest request, @Valid @RequestBody D dto) {
//        if (!options.isCreateAllowed()) {
//            utils.methodNotAllowed(request.getMethod());
//        }
//
//        T entity = toEntity(dto);
//        return ResponseEntity.ok(toDto(repository.save(entity)));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(HttpServletRequest request, @PathVariable ID id, @Valid @RequestBody D dto) {
//        if (!options.isUpdateAllowed()) {
//            utils.methodNotAllowed(request.getMethod());
//        }
//
//        if (!repository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
//        T entity = toEntity(dto);
//        return ResponseEntity.ok(toDto(repository.save(entity)));
//    }

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
