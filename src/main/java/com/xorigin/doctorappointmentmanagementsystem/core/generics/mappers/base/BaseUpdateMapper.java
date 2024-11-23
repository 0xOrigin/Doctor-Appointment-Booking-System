package com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface BaseUpdateMapper<Entity, UpdateDTO, PartialUpdateDTO> extends BaseMapper {

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromUpdateDto(@MappingTarget Entity entity, UpdateDTO dto);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromPartialUpdateDto(@MappingTarget Entity entity, PartialUpdateDTO dto);

}
