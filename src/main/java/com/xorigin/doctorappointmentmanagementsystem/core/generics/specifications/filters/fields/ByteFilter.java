package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.fields;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.AbstractNumberFilterField;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions.InvalidFilterValueException;

public class ByteFilter extends AbstractNumberFilterField<Byte> {

    @Override
    public Byte cast(Object value) {
        try {
            return Byte.parseByte(value.toString());
        } catch (NumberFormatException e) {
            throw new InvalidFilterValueException(e.getLocalizedMessage(), e);
        }
    }

}
