package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.operators;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.AbstractFilterOperator;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.Operator;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions.InvalidFilterValueException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;

import java.util.List;

public class Between extends AbstractFilterOperator {

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Predicate apply(Path<?> path, CriteriaBuilder cb, List<?> values) {
        if (values.size() != 2) {
            throw new InvalidFilterValueException(
                    "Value must be a List with exactly 2 elements for " + Operator.BETWEEN.getValue() + " operator."
            );
        }

        if (isTemporalFilter(path)) {
            TemporalGroup group = getTemporalGroup(path);
            List<? extends Comparable> jdbcTypes = getJdbcTypes(path, group, values);
            Expression expression = getTemporalPath(group).apply(path);
            return cb.between(expression, jdbcTypes.getFirst(), jdbcTypes.getLast());
        }

        return cb.between(path.as(Comparable.class), (Comparable) values.getFirst(), (Comparable) values.getLast());
    }

}
