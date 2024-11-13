package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.operators;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.AbstractFilterOperator;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;

import java.util.List;

public class GreaterThan extends AbstractFilterOperator {

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Predicate apply(Path<?> path, CriteriaBuilder cb, List<?> values) {
        if (isTemporalFilter(path)) {
            TemporalGroup group = getTemporalGroup(path);
            List<? extends Comparable> jdbcTypes = getJdbcTypes(path, group, List.of(values.getFirst()));
            Expression expression = getTemporalPath(group).apply(path);
            return cb.greaterThan(expression, jdbcTypes.getFirst());
        }

        return cb.greaterThan(path.as(Comparable.class), (Comparable) values.getFirst());
    }

}
