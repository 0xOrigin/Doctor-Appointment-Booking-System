package com.xorigin.doctorappointmentmanagementsystem.core.responses;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

public class StandardApiErrorResponse implements ApiErrorResponse {

    private final OffsetDateTime timestamp = OffsetDateTime.now();
    private final String message;
    private final String path;
    private final Map<String, List<String>> errors;

    public StandardApiErrorResponse(String message, String path, Map<String, List<String>> errors) {
        this.message = message;
        this.path = path;
        this.errors = errors;
    }

    @Override
    public String getTimestamp() {
        return timestamp.toString();
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Map<String, List<String>> getErrors() {
        return errors;
    }

}
