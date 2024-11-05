package com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base;

import com.xorigin.doctorappointmentmanagementsystem.core.responses.BaseMetaResponse;
import com.xorigin.doctorappointmentmanagementsystem.core.responses.BasePaginationResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CrudControllerUtils implements ControllerUtils {

    public void methodNotAllowed(String method) {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, method + " Method not allowed");
    }

    public Class<? extends BaseMetaResponse> getMetaClass() {
        return BaseMetaResponse.class;
    }

    public Class<? extends BasePaginationResponse> getPaginationClass() {
        return BasePaginationResponse.class;
    }

    public int getPageNumber(Pageable pageable) {
        return pageable.getPageNumber() > 0 ? pageable.getPageNumber() - 1 : pageable.getPageNumber();
    }

    public int getPageSize(Pageable pageable, ControllerOptions options) {
        return options.getPageSize() != pageable.getPageSize() ? pageable.getPageSize() : options.getPageSize();
    }

}
