package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.fields;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.AbstractNumberFilterField;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions.InvalidFilterValueException;

public class ShortFilter extends AbstractNumberFilterField<Short> {

    @Override
    public Short cast(Object value) {
        try {
            return Short.parseShort(value.toString());
        } catch (NumberFormatException e) {
            throw new InvalidFilterValueException(e.getLocalizedMessage(), e);
        }
    }

}
