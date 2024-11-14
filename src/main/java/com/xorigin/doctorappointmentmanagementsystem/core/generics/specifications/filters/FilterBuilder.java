package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.*;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.registries.FilterOperatorRegistry;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.registries.FilterRegistry;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilterBuilder<T> implements QueryFilterBuilder<T> {

    private final Parser filterParser;
    private final PathGenerator<T> filterPathGenerator;
    private final FilterValidator filterValidator;
    private final Map<String, Set<Operator>> fieldOperators = new HashMap<>();
    private final Map<String, CustomFilterWrapper<T>> customFieldFilters = new HashMap<>();

    public FilterBuilder(FilterParser filterParser, PathGenerator<T> filterPathGenerator, FilterValidator filterValidator) {
        this.filterParser = filterParser;
        this.filterPathGenerator = filterPathGenerator;
        this.filterValidator = filterValidator;
    }

    @Override
    public QueryFilterBuilder<T> addFilter(String fieldName, Operator... operators) {
        filterValidator.validateFilterAddition(fieldName, operators);
        fieldOperators.put(fieldName, new HashSet<>(Arrays.asList(operators)));
        return this;
    }

    @Override
    public QueryFilterBuilder<T> addFilter(String fieldName, Class<? extends Comparable<?>> dataType, CustomFilterFunction<T> filterFunction) {
        filterValidator.validateCustomFilterAddition(fieldName, dataType, filterFunction);
        AbstractFilterField<?> filterField = FilterRegistry.getFieldFilter(dataType);
        customFieldFilters.put(fieldName, new CustomFilterWrapper<T>(filterField, filterFunction));
        return this;
    }

    public Predicate buildFilterPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
        List<Predicate> predicates = filterParser.parse().stream()
                .map(filterWrapper -> buildPredicateForWrapper(root, criteriaQuery, cb, filterWrapper))
                .filter(Objects::nonNull)
                .toList();

        return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate buildPredicateForWrapper(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb, FilterWrapper filterWrapper) {
        Predicate customPredicate = buildCustomFieldPredicate(root, criteriaQuery, cb, filterWrapper);
        if (customPredicate != null)
            return customPredicate;

        if (isValidFieldOperator(filterWrapper))
            return createPredicate(root, cb, filterWrapper);

        return null;
    }

    private Predicate buildCustomFieldPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb, FilterWrapper filterWrapper) {
        CustomFilterWrapper<T> customFilter = customFieldFilters.get(filterWrapper.getOriginalFieldName());
        if (customFilter == null)
            return null;

        List<?> values = filterWrapper.getValues().stream()
                .map(customFilter.getFilterField()::cast)
                .toList();
        return customFilter.getCustomFilterFunction().apply(root, criteriaQuery, cb, values);
    }

    private boolean isValidFieldOperator(FilterWrapper filterWrapper) {
        return fieldOperators.containsKey(filterWrapper.getField()) &&
                fieldOperators.get(filterWrapper.getField()).contains(filterWrapper.getOperator());
    }

    private Predicate createPredicate(Root<T> root, CriteriaBuilder builder, FilterWrapper filterWrapper) {
        Path<T> path = getPath(root, filterWrapper.getField());
        Class<? extends Comparable<?>> dataType = getFieldDataType(path);
        AbstractFilterField<?> filterClass = getFieldFilter(dataType);
        FilterOperator filterOperator = FilterOperatorRegistry.getOperator(filterWrapper.getOperator());

        filterValidator.validateFilterFieldAndOperator(filterClass, filterOperator, filterWrapper);

        List<?> values = filterWrapper.getValues().stream().map(filterClass::cast).toList();
        return filterOperator.apply(path, builder, values);
    }

    private Path<T> getPath(Root<T> root, String field) {
        return filterPathGenerator.generate(root, field);
    }

    @SuppressWarnings("unchecked")
    private Class<? extends Comparable<?>> getFieldDataType(Path<T> path) {
        return (Class<? extends Comparable<?>>) path.getJavaType();
    }

    private AbstractFilterField<?> getFieldFilter(Class<? extends Comparable<?>> dataType) {
        return FilterRegistry.getFieldFilter(dataType);
    }

}
