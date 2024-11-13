package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.fields;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.AbstractTemporalFilterField;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions.InvalidFilterValueException;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

public class OffsetTimeFilter extends AbstractTemporalFilterField<OffsetDateTime> {

    @Override
    public OffsetDateTime cast(Object value) {
        try {
            return OffsetDateTime.parse(value.toString());
        } catch (DateTimeParseException e) {
            throw new InvalidFilterValueException(e.getLocalizedMessage(), e);
        }
    }

}
