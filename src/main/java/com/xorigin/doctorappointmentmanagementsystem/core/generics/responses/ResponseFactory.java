package com.xorigin.doctorappointmentmanagementsystem.core.generics.responses;

import org.springframework.data.domain.Page;

public interface ResponseFactory {

    <N> ApiResponse<?> createResponse(String message, N data, Long count, Page<?> page);

    <N> ApiResponse<?> createResponse(String message, N data);

    ApiResponse<?> createResponse(String message);

}
