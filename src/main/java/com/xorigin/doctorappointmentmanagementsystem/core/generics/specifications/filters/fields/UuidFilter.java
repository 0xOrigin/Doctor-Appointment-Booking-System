package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.fields;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.Operator;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.AbstractFilterField;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions.InvalidFilterValueException;

import java.util.Set;
import java.util.UUID;

public class UuidFilter extends AbstractFilterField<UUID> {

    {
        this.setSupportedOperators(
            Set.of(
                Operator.EQ, Operator.NEQ, Operator.GT, Operator.LT, Operator.GTE, Operator.LTE,
                Operator.IS_NULL, Operator.IS_NOT_NULL, Operator.IN, Operator.NOT_IN, Operator.BETWEEN, Operator.NOT_BETWEEN
            )
        );
    }

    @Override
    public UUID cast(Object value) {
        try {
            return UUID.fromString(value.toString());
        } catch (IllegalArgumentException e) {
            throw new InvalidFilterValueException(e.getLocalizedMessage(), e);
        }
    }

}
