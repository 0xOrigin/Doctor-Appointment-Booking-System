package com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base;

import org.springframework.data.domain.Pageable;

public interface ControllerUtils {

    void methodNotAllowed(String method);

    int getPageNumber(Pageable pageable);

    int getPageSize(Pageable pageable, ControllerOptions options);

}
