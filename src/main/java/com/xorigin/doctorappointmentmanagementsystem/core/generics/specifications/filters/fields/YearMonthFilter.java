package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.fields;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.AbstractTemporalFilterField;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions.InvalidFilterValueException;

import java.time.YearMonth;
import java.time.format.DateTimeParseException;

public class YearMonthFilter extends AbstractTemporalFilterField<YearMonth> {

    @Override
    public YearMonth cast(Object value) {
        try {
            return YearMonth.parse(value.toString());
        } catch (DateTimeParseException e) {
            throw new InvalidFilterValueException(e.getLocalizedMessage(), e);
        }
    }

}
