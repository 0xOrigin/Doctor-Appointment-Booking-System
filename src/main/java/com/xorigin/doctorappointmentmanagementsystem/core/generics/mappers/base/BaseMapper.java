package com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base;

import java.util.Optional;

public interface BaseMapper {

    default <T> Optional<T> map(T value) {
        return Optional.ofNullable(value);
    }

    default <T> T map(Optional<T> value) {
        return value != null ? value.orElse(null) : null;
    }

    default <T> T map(Optional<T> value, T defaultValue) {
        return value != null ? value.orElse(defaultValue) : defaultValue;
    }

}
