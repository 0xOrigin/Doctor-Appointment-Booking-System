package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.fields;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.AbstractNumberFilterField;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions.InvalidFilterValueException;

public class FloatFilter extends AbstractNumberFilterField<Float> {

    @Override
    public Float cast(Object value) {
        try {
            return Float.parseFloat(value.toString());
        } catch (NumberFormatException e) {
            throw new InvalidFilterValueException(e.getLocalizedMessage(), e);
        }
    }

}
