package com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base;

import org.mapstruct.*;

import java.util.Optional;

public interface BaseGenericMapper<Entity, ListDTO, RetrieveDTO, CreateDTO, UpdateDTO> {

    Entity toEntityFromCreateDto(CreateDTO dto);

    Entity toEntityFromUpdateDto(UpdateDTO dto);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Entity entity, UpdateDTO dto);

    Entity toUpdatedEntity(Entity entity);

    ListDTO toListDto(Entity entity);

    RetrieveDTO toRetrieveDto(Entity entity);

    default <T> Optional<T> map(T value) {
        return Optional.ofNullable(value);
    }

    default <T> T map(Optional<T> value) {
        return value.orElse(null);
    }

    default <T> T map(Optional<T> value, T defaultValue) {
        return value.orElse(defaultValue);
    }

}
