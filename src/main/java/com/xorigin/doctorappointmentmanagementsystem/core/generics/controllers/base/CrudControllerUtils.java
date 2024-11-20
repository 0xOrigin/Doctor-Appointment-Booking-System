package com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.MethodNotAllowedException;

@Service
public class CrudControllerUtils implements ControllerUtils {

    public void methodNotAllowed(String method) {
        throw new MethodNotAllowedException(method, null);
    }

    public int getPageNumber(Pageable pageable) {
        return pageable.getPageNumber() > 0 ? pageable.getPageNumber() - 1 : pageable.getPageNumber();
    }

    public int getPageSize(Pageable pageable, ControllerOptions options) {
        int pageSize = options.getDefaultPageSize();

        if (pageable.getPageSize() != options.getDefaultPageSize())
            pageSize = pageable.getPageSize();
        else if (
                options.getPageSize() != null &&
                !options.getPageSize().equals(options.getDefaultPageSize())
        )
            pageSize = options.getPageSize();

        return pageSize;
    }

}
