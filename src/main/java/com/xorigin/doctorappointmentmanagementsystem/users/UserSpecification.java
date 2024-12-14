package com.xorigin.doctorappointmentmanagementsystem.users;

import io.github._0xorigin.queryfilterbuilder.FilterContext;
import io.github._0xorigin.queryfilterbuilder.base.ErrorWrapper;
import io.github._0xorigin.queryfilterbuilder.base.Operator;
import io.github._0xorigin.queryfilterbuilder.base.QueryFilterBuilder;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSpecification implements Specification<User> {

    private final QueryFilterBuilder<User> queryFilterBuilder;

    public UserSpecification(QueryFilterBuilder<User> queryFilterBuilder) {
        this.queryFilterBuilder = queryFilterBuilder;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.and(getQueryFilterPredicate(root, query, cb));
    }

    private Predicate getQueryFilterPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        FilterContext<User> filterContext = new FilterContext<>();
        filterContext.addFilter("createdBy", Operator.EQ)
                .addFilter("isActive", Operator.IS_NULL, Operator.IS_NOT_NULL)
                .addFilter("lastLogin", Operator.EQ, Operator.GTE, Operator.STARTS_WITH)
                .addFilter("createdBy__lastLogin", Operator.EQ, Operator.GTE, Operator.BETWEEN)
                .addFilter("search", String.class, this::search);

        return queryFilterBuilder.buildFilterPredicate(root, query, cb, filterContext);
    }

    private Predicate search(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb, List<?> values, ErrorWrapper errorWrapper) {
        return cb.or(cb.equal(root.get("firstName"), values.get(0)), cb.equal(root.get("lastName"), values.get(0)));
    }

}
