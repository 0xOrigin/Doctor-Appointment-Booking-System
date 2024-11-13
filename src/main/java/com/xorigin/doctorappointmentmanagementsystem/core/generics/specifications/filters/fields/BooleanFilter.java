package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.fields;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.Operator;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.AbstractFilterField;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions.InvalidFilterValueException;

import java.util.Set;

public class BooleanFilter extends AbstractFilterField<Boolean> {

    {
        this.setSupportedOperators(
            Set.of(
                Operator.EQ, Operator.NEQ, Operator.IS_NULL, Operator.IS_NOT_NULL
            )
        );
    }

    @Override
    public Boolean cast(Object value) {
        try {
            return !value.equals("null") ? Boolean.valueOf(value.toString()) : null;
        } catch (Exception e) {
            throw new InvalidFilterValueException(e.getLocalizedMessage(), e);
        }
    }

}
