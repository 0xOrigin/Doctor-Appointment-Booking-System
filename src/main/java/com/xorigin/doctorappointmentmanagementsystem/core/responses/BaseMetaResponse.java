package com.xorigin.doctorappointmentmanagementsystem.core.responses;

public class BaseMetaResponse {

    private Long count;

    public BaseMetaResponse(Long count) {
        this.count = count;
    }

    public Long getCount() {
        return count;
    }

}
