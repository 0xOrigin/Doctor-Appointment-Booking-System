package com.xorigin.doctorappointmentmanagementsystem.users;

import io.github._0xorigin.FilterContext;
import io.github._0xorigin.base.ErrorWrapper;
import io.github._0xorigin.base.Operator;
import io.github._0xorigin.base.QueryFilterBuilder;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.GenericSpecification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserSpecification extends GenericSpecification<User> {

    public UserSpecification(HttpServletRequest request, UserProvider userProvider, QueryFilterBuilder<User> filterBuilder) {
        super(request, userProvider, filterBuilder);
    }

    @Override
    protected Predicate getFilterPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return getFilterBuilder().buildFilterPredicate(
                root,
                query,
                cb,
                new FilterContext<User>()
                        .addFilter("createdBy", Operator.EQ)
                        .addFilter("isActive", Operator.IS_NULL, Operator.IS_NOT_NULL)
                        .addFilter("lastLogin", Operator.EQ, Operator.GTE, Operator.STARTS_WITH)
                        .addFilter("createdBy__lastLogin__createdBy", Operator.EQ, Operator.GTE, Operator.BETWEEN)
                        .addFilter("hie", Boolean.class, this::getIsActivePredicate)
        );
    }

    public Predicate getIsActivePredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb, List<?> values, ErrorWrapper errorWrapper) {
        return cb.equal(root.get("isActive"), values.getFirst());
    }

}
