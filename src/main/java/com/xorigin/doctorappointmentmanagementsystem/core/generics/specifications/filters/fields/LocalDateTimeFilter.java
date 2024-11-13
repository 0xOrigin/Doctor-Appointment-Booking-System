package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.fields;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.AbstractTemporalFilterField;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions.InvalidFilterValueException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class LocalDateTimeFilter extends AbstractTemporalFilterField<LocalDateTime> {

    @Override
    public LocalDateTime cast(Object value) {
        try {
            return LocalDateTime.parse(value.toString());
        } catch (DateTimeParseException e) {
            throw new InvalidFilterValueException(e.getLocalizedMessage(), e);
        }
    }

}
