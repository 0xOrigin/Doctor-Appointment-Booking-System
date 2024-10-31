package com.xorigin.doctorappointmentmanagementsystem.core.generics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class GenericCrudController<T, D, ID> {

    private final CrudControllerOptions options;

    public GenericCrudController(CrudControllerOptions options) {
        this.options = options;
    }

    @GetMapping
    public ResponseEntity<?> getAll(Specification<T> spec, Pageable pageable) {
        if (!options.isAllowGetAll()) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "GET all not allowed");
        }

        if (!options.isAllowPagination()) {
            pageable = PageRequest.of(options.getPageNumber(), options.getPageSize());
        }

        Page<T> dataPage = repository.findAll(spec, pageable);
        List<D> dtos = dataPage.stream().map(this::toDto).collect(Collectors.toList());

        ApiResponse.Meta meta = new ApiResponse.Meta((int) dataPage.getTotalElements());
        ApiResponse.Pagination pagination = new ApiResponse.Pagination(
                dataPage.hasPrevious() ? dataPage.getNumber() - 1 : null,
                dataPage.hasNext() ? dataPage.getNumber() + 1 : null,
                dataPage.getTotalPages() - 1
        );

        ApiResponse<D> response = new ApiResponse<>(200, "Success", meta, pagination, dtos);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> getById(@PathVariable ID id) {
        if (!options.isAllowGetById()) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "GET by ID not allowed");
        }

        Optional<T> entity = repository.findById(id);
        return entity.map(value -> ResponseEntity.ok(toDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<D> create(@Valid @RequestBody D dto) {
        if (!options.isAllowCreate()) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "POST not allowed");
        }

        T entity = toEntity(dto);
        return ResponseEntity.ok(toDto(repository.save(entity)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<D> update(@PathVariable ID id, @Valid @RequestBody D dto) {
        if (!options.isAllowUpdate()) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "PUT not allowed");
        }

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        T entity = toEntity(dto);
        return ResponseEntity.ok(toDto(repository.save(entity)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        if (!options.isAllowDelete()) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "DELETE not allowed");
        }

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Abstract methods for entity-DTO transformations
    protected abstract D toDto(T entity);
    protected abstract T toEntity(D dto);
}