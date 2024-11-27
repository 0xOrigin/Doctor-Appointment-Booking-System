package com.xorigin.doctorappointmentmanagementsystem.core.generics.responses;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class StandardResponseFactory implements ResponseFactory {

    @Override
    public <N> ApiResponse<?> createResponse(String message, N data, Long count, Page<?> page) {
        return StandardApiResponse
                .builder()
                .message(message)
                .meta(BaseMetaResponse.builder().count(count).build())
                .pagination(BasePaginationResponse.builder().page(page).build())
                .data(data)
                .build();
    }

    @Override
    public <N> ApiResponse<?> createResponse(String message, N data) {
        return createResponse(message, data, null, null);
    }

    @Override
    public ApiResponse<?> createResponse(String message) {
        return createResponse(message, null, null, null);
    }

}
