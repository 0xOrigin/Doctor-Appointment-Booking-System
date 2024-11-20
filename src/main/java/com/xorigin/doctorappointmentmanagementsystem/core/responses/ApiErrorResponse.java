package com.xorigin.doctorappointmentmanagementsystem.core.responses;

import java.util.List;
import java.util.Map;

public interface ApiErrorResponse {

    String getTimestamp();

    String getMessage();

    String getPath();

    Map<String, List<String>> getErrors();

}
