package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.fields;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.AbstractTemporalFilterField;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions.InvalidFilterValueException;

import java.time.Instant;
import java.time.format.DateTimeParseException;

public class InstantFilter extends AbstractTemporalFilterField<Instant> {

    @Override
    public Instant cast(Object value) {
        try {
            return Instant.parse(value.toString());
        } catch (DateTimeParseException e) {
            throw new InvalidFilterValueException(e.getLocalizedMessage(), e);
        }
    }

}
