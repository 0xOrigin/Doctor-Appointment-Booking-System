package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions;

public class InvalidFilterConfigurationException extends RuntimeException {

    public InvalidFilterConfigurationException(String message) {
        super(message);
    }

    public InvalidFilterConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

}
