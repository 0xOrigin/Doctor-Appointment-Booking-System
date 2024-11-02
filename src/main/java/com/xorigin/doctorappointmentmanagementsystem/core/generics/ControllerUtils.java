package com.xorigin.doctorappointmentmanagementsystem.core.generics;

import com.xorigin.doctorappointmentmanagementsystem.core.responses.BaseMetaResponse;
import com.xorigin.doctorappointmentmanagementsystem.core.responses.BasePaginationResponse;
import org.springframework.data.domain.Pageable;

public interface ControllerUtils {

    void methodNotAllowed(String method);

    Class<? extends BaseMetaResponse> getMetaClass();

    Class<? extends BasePaginationResponse> getPaginationClass();

    int getPageNumber(Pageable pageable);

    int getPageSize(Pageable pageable, ControllerOptions options);

}
