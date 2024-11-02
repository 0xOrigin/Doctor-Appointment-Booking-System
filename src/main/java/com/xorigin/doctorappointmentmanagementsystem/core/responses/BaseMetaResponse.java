package com.xorigin.doctorappointmentmanagementsystem.core.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

public class BaseMetaResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long count;

    public BaseMetaResponse(Long count) {
        this.count = count;
    }

    public Long getCount() {
        return count;
    }

}
