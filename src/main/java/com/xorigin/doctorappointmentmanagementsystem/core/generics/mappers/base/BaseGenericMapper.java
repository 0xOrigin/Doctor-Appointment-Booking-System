package com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base;

import org.mapstruct.MappingTarget;

public interface BaseGenericMapper<Entity, ListDTO, RetrieveDTO, CreateDTO, UpdateDTO> {

    Entity toEntity(CreateDTO dto);

    void updateEntityFromDto(UpdateDTO dto, @MappingTarget Entity entity);

    ListDTO toListDto(Entity entity);

    RetrieveDTO toRetrieveDto(Entity entity);

}
