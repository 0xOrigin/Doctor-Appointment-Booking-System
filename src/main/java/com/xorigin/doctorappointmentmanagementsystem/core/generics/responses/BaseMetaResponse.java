package com.xorigin.doctorappointmentmanagementsystem.core.generics.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

public class BaseMetaResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Long count;

    public BaseMetaResponse(Long count) {
        this.count = count;
    }

    public Long getCount() {
        return count;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long count;

        public Builder() {}

        public Builder count(Long count) {
            this.count = count;
            return this;
        }

        public BaseMetaResponse build() {
            return new BaseMetaResponse(count);
        }

    }

}
