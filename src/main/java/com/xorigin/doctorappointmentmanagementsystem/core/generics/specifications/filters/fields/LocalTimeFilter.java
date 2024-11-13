package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.fields;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.AbstractTemporalFilterField;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions.InvalidFilterValueException;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class LocalTimeFilter extends AbstractTemporalFilterField<LocalTime> {

    @Override
    public LocalTime cast(Object value) {
        try {
            return LocalTime.parse(value.toString());
        } catch (DateTimeParseException e) {
            throw new InvalidFilterValueException(e.getLocalizedMessage(), e);
        }
    }

}
