package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.QueryFilterBuilder;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public abstract class QueryFilterBuilderSpecification<T> implements Specification<T> {

    private final QueryFilterBuilder<T> queryFilterBuilder;

    public QueryFilterBuilderSpecification(QueryFilterBuilder<T> queryFilterBuilder) {
        this.queryFilterBuilder = queryFilterBuilder;

        if (queryFilterBuilder != null)
            initializeFilterBuilder();
    }

    protected abstract void initializeFilterBuilder();

    public QueryFilterBuilder<T> getQueryFilterBuilder() {
        return queryFilterBuilder;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (queryFilterBuilder == null)
            return criteriaBuilder.conjunction();

        return queryFilterBuilder.buildFilterPredicate(root, criteriaBuilder);
    }

}
