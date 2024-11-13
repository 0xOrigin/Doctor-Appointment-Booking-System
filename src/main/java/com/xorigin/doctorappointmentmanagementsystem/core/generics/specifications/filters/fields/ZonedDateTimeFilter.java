package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.fields;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.AbstractTemporalFilterField;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions.InvalidFilterValueException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

public class ZonedDateTimeFilter extends AbstractTemporalFilterField<ZonedDateTime> {

    @Override
    public ZonedDateTime cast(Object value) {
        try {
            return ZonedDateTime.parse(value.toString());
        } catch (DateTimeParseException e) {
            throw new InvalidFilterValueException(e.getLocalizedMessage(), e);
        }
    }

}
