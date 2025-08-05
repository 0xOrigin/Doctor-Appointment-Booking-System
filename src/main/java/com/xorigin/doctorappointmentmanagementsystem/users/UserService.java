package com.xorigin.doctorappointmentmanagementsystem.users;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.UuidSingleDtoGenericService;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.base.MessageByLocaleService;
import io.github._0xorigin.queryfilterbuilder.FilterContext;
import io.github._0xorigin.queryfilterbuilder.QueryFilterBuilder;
import io.github._0xorigin.queryfilterbuilder.SortContext;
import io.github._0xorigin.queryfilterbuilder.base.dtos.FilterRequest;
import io.github._0xorigin.queryfilterbuilder.base.filteroperator.Operator;
import io.github._0xorigin.queryfilterbuilder.base.wrappers.FilterErrorWrapper;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService extends UuidSingleDtoGenericService<User, UserRepository, UserMapper, UserDTO> {

    private final PasswordEncoder passwordEncoder;
    private final QueryFilterBuilder<User> queryFilterBuilder;

    public UserService(
            UserProvider userProvider,
            UserRepository repository,
            UserMapper mapper,
            UserSpecification spec,
            PasswordEncoder passwordEncoder,
            MessageByLocaleService messageByLocaleService,
            QueryFilterBuilder<User> queryFilterBuilder
    ) {
        super(userProvider, repository, mapper, spec, messageByLocaleService);
        this.passwordEncoder = passwordEncoder;
        this.queryFilterBuilder = queryFilterBuilder;
    }

    public List<User> findAll(HttpServletRequest request, Sort sort) {
        return super.findAll(request, null);
    }

    @Override
    public Optional<Specification<User>> getSpec(HttpServletRequest request) {
        FilterContext<User> filterContext = FilterContext.buildForType(User.class)
                .queryParam(request, builder -> {
                    builder.addFilter("createdBy", Operator.EQ)
                    .addFilter("firstName", (root, cq, cb) -> root.get("firstName"), Operator.EQ, Operator.BETWEEN)
                    .addFilter("isActive", Operator.IS_NULL, Operator.IS_NOT_NULL, Operator.GTE)
                    .addFilter("lastLogin", Operator.EQ, Operator.GTE, Operator.STARTS_WITH)
                    .addFilter("createdAt", Operator.EQ, Operator.GTE, Operator.LTE)
                    .addFilter("createdBy.lastLogin", Operator.EQ, Operator.GTE, Operator.BETWEEN);
                })
                .requestBody(List.of(new FilterRequest("firstName", Operator.EQ.getValue(), "Ali")), builder -> {
                    builder.addCustomFilter("search", String.class, this::search)
                    .addFilter("isActive", Operator.IS_NULL, Operator.IS_NOT_NULL);
                })
                .build();

        SortContext<User> sortContext = SortContext.buildForType(User.class)
                .queryParam(request, builder -> {
                    builder.addSorts("firstName")
                    .addDescSort("createdBy.firstName");
                })
                .build();

        return Optional.of(queryFilterBuilder.buildFilterSpecification(filterContext).and(queryFilterBuilder.buildSortSpecification(sortContext)));
    }

    private Optional<Predicate> search(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb, List<?> values, FilterErrorWrapper errorWrapper) {
        return Optional.ofNullable(cb.or(cb.equal(root.get("firstName"), values.get(0)), cb.equal(root.get("lastName"), values.get(0))));
    }

    @Override
    protected User getInstanceFromCreateDto(UserDTO dto) {
        return getMapper().toEntity(dto, passwordEncoder);
    }

    @Override
    protected void updateInstanceFromUpdateDto(User instance, UserDTO userDTO) {
        super.updateInstanceFromUpdateDto(instance, userDTO);
    }

}
