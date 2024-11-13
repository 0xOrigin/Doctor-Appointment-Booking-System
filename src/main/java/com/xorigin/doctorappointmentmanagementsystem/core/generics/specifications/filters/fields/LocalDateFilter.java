package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.fields;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.AbstractTemporalFilterField;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions.InvalidFilterValueException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class LocalDateFilter extends AbstractTemporalFilterField<LocalDate> {

    @Override
    public LocalDate cast(Object value) {
        try {
            return LocalDate.parse(value.toString());
        } catch (DateTimeParseException e) {
            throw new InvalidFilterValueException(e.getLocalizedMessage(), e);
        }
    }

}
