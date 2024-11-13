package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.fields;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.AbstractNumberFilterField;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions.InvalidFilterValueException;

public class LongFilter extends AbstractNumberFilterField<Long> {

    @Override
    public Long cast(Object value) {
        try {
            return Long.parseLong(value.toString());
        } catch (NumberFormatException e) {
            throw new InvalidFilterValueException(e.getLocalizedMessage(), e);
        }
    }

}
