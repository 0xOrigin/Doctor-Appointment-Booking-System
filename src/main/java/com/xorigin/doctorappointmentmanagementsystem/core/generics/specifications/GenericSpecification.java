package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications;

import io.github._0xorigin.FilterContext;
import io.github._0xorigin.base.QueryFilterBuilder;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

public abstract class GenericSpecification<T> implements Specification<T> {

    private final HttpServletRequest request;
    private final UserProvider userProvider;
    private final QueryFilterBuilder<T> filterBuilder;

    public GenericSpecification(
            HttpServletRequest request,
            UserProvider userProvider,
            QueryFilterBuilder<T> filterBuilder
    ) {
        this.request = request;
        this.userProvider = userProvider;
        this.filterBuilder = filterBuilder;
    }

    public GenericSpecification(HttpServletRequest request, UserProvider userProvider) {
        this(request, userProvider, null);
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public UserProvider getUserProvider() {
        return userProvider;
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getRequestPathParams() {
        return (Map<String, String>) getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    }

    public QueryFilterBuilder<T> getFilterBuilder() {
        return filterBuilder;
    }

    protected Predicate getFilterPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return getFilterBuilder().buildFilterPredicate(root, query, cb, new FilterContext<>());
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.and(commonFilter(root, query, cb), getAdditionalPredicate(root, query, cb), getFilterPredicate(root, query, cb));
    }

    protected Predicate commonFilter(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        // No common filter currently, return an "always-true" predicate
        return cb.conjunction();
    }

    protected Predicate getAdditionalPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        // No additional predicate currently, return an "always-true" predicate
        return cb.conjunction();
    }

}
