package com.xorigin.doctorappointmentmanagementsystem.core.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

public class StandardApiResponse<M extends BaseMetaResponse, P extends BasePaginationResponse, D> implements ApiResponse<D> {

    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final M meta;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final P pagination;

    private final D data;

    public StandardApiResponse(String message, M meta, P pagination, D data) {
        this.message = message;
        this.meta = (meta != null && meta.getCount() != null) ? meta : null;
        this.pagination = (pagination != null && pagination.getLast() != null) ? pagination : null;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public M getMeta() {
        return meta;
    }

    public P getPagination() {
        return pagination;
    }

    public D getData() {
        return data;
    }

    public static <M extends BaseMetaResponse, P extends BasePaginationResponse, D> Builder<M, P, D> builder() {
        return new Builder<>();
    }

    public static class Builder<M extends BaseMetaResponse, P extends BasePaginationResponse, D> {

        private String message = "Success";
        private M meta;
        private P pagination;
        private D data;

        public Builder<M, P, D> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<M, P, D> meta(M meta) {
            this.meta = meta;
            return this;
        }

        public Builder<M, P, D> pagination(P pagination) {
            this.pagination = pagination;
            return this;
        }

        public Builder<M, P, D> data(D data) {
            this.data = data;
            return this;
        }

        public StandardApiResponse<M, P, D> build() {
            return new StandardApiResponse<>(message, meta, pagination, data);
        }
    }

}
