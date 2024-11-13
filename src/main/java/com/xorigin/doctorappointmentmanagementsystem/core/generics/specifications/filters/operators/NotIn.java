package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.operators;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.AbstractFilterOperator;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;

import java.util.List;

public class NotIn extends AbstractFilterOperator {

    @Override
    public Predicate apply(Path<?> path, CriteriaBuilder cb, List<?> values) {
        return cb.not(path.in(values));
    }

}
