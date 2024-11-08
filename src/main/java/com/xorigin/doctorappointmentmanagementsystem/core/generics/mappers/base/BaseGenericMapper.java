package com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base;

import org.mapstruct.*;

import java.util.Optional;

public interface BaseGenericMapper<Entity, ListDTO, RetrieveDTO, CreateDTO, UpdateDTO, PartialUpdateDTO> {

    Entity toEntity(CreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromUpdateDto(@MappingTarget Entity entity, UpdateDTO dto);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromPartialUpdateDto(@MappingTarget Entity entity, PartialUpdateDTO dto);

    ListDTO toListDto(Entity entity);

    RetrieveDTO toRetrieveDto(Entity entity);

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
