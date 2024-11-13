package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public abstract class AbstractFilterOperator implements FilterOperator {

    protected enum TemporalGroup {
        Date, Time, Timestamp;
    }

    private final Map<Class<? extends Temporal>, TemporalGroup> temporalGroup = new HashMap<>();
    private final Map<Class<? extends Temporal>, Function<List<?>, ?>> temporalConverter = new HashMap<>();
    private final Map<TemporalGroup, Function<Path<?>, Expression<?>>> temporalPath = new HashMap<>();
    private final Map<TemporalGroup, Function<List<?>, List<? extends Comparable<?>>>> temporalToJdbcType = new HashMap<>();

    {
        addTemporalGroup(Instant.class, TemporalGroup.Timestamp);
        addTemporalGroup(OffsetDateTime.class, TemporalGroup.Timestamp);
        addTemporalGroup(ZonedDateTime.class, TemporalGroup.Timestamp);
        addTemporalGroup(LocalDateTime.class, TemporalGroup.Timestamp);
        addTemporalGroup(LocalDate.class, TemporalGroup.Date);
        addTemporalGroup(YearMonth.class, TemporalGroup.Date);
        addTemporalGroup(Year.class, TemporalGroup.Date);
        addTemporalGroup(LocalTime.class, TemporalGroup.Time);
        addTemporalGroup(OffsetTime.class, TemporalGroup.Time);

        addTemporalPath(TemporalGroup.Date, path -> path.as(Date.class));
        addTemporalPath(TemporalGroup.Time, path -> path.as(Time.class));
        addTemporalPath(TemporalGroup.Timestamp, path -> path.as(Timestamp.class));

        addTemporalConverter(Instant.class, values -> values.stream().toList());
        addTemporalConverter(OffsetDateTime.class, values -> values.stream().map(o -> ((OffsetDateTime) o).toInstant()).toList());
        addTemporalConverter(ZonedDateTime.class, values -> values.stream().map(o -> ((ZonedDateTime) o).toInstant()).toList());
        addTemporalConverter(LocalDateTime.class, values -> values.stream().map(o -> Instant.parse(((LocalDateTime) o).toString())).toList());
        addTemporalConverter(LocalDate.class, values -> values.stream().map(o -> ((LocalDate) o).toString()).toList());
        addTemporalConverter(YearMonth.class, values -> values.stream().map(o -> ((YearMonth) o).toString()).toList());
        addTemporalConverter(Year.class, values -> values.stream().map(o -> ((Year) o).toString()).toList());
        addTemporalConverter(LocalTime.class, values -> values.stream().map(o -> ((LocalTime) o).toString()).toList());
        addTemporalConverter(OffsetTime.class, values -> values.stream().map(o -> ((OffsetTime) o).toString()).toList());

        addTemporalToJdbcType(TemporalGroup.Date, values -> values.stream().map(o -> Date.valueOf(o.toString())).toList());
        addTemporalToJdbcType(TemporalGroup.Time, values -> values.stream().map(o -> Time.valueOf(o.toString())).toList());
        addTemporalToJdbcType(TemporalGroup.Timestamp, values -> values.stream().map(o -> Timestamp.from((Instant) o)).toList());
    }

    private void addTemporalGroup(Class<? extends Temporal> temporalClass, TemporalGroup group) {
        temporalGroup.put(temporalClass, group);
    }

    private void addTemporalConverter(Class<? extends Temporal> temporalClass, Function<List<?>, ?> converter) {
        temporalConverter.put(temporalClass, converter);
    }

    private void addTemporalPath(TemporalGroup group, Function<Path<?>, Expression<?>> path) {
        temporalPath.put(group, path);
    }

    private void addTemporalToJdbcType(TemporalGroup group, Function<List<?>, List<? extends Comparable<?>>> converter) {
        temporalToJdbcType.put(group, converter);
    }

    protected TemporalGroup getTemporalGroup(Path<?> path) {
        return temporalGroup.get(path.getJavaType());
    }

    protected Function<Path<?>, Expression<?>> getTemporalPath(TemporalGroup group) {
        return temporalPath.get(group);
    }

    protected Function<List<?>, ?> getTemporalConverter(Path<?> path) {
        return temporalConverter.get(path.getJavaType());
    }

    protected Function<List<?>, List<? extends Comparable<?>>> getTemporalToJdbcTypes(TemporalGroup group) {
        return temporalToJdbcType.get(group);
    }

    protected List<? extends Comparable<?>> getJdbcTypes(Path<?> path, TemporalGroup group, List<?> values) {
        List<?> convertedTemporal = (List<?>) getTemporalConverter(path).apply(values);
        return getTemporalToJdbcTypes(group).apply(convertedTemporal);
    }

    protected Boolean isTemporalFilter(Path<?> path) {
        return Arrays.stream(path.getJavaType().getInterfaces()).anyMatch(i -> i == Temporal.class);
    }

}
