package com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base;

public interface BaseResponseMapper<Entity, ListDTO, RetrieveDTO> extends BaseMapper {

    ListDTO toListDto(Entity entity);

    RetrieveDTO toRetrieveDto(Entity entity);

}
