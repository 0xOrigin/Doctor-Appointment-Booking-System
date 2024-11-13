package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.fields;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.AbstractNumberFilterField;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions.InvalidFilterValueException;

public class IntegerFilter extends AbstractNumberFilterField<Integer> {

    @Override
    public Integer cast(Object value) {
        try {
            return Integer.parseInt(value.toString());
        } catch (NumberFormatException e) {
            throw new InvalidFilterValueException(e.getLocalizedMessage(), e);
        }
    }

}
