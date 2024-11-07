package com.xorigin.doctorappointmentmanagementsystem.users;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.SingleDtoMapper;
import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper extends SingleDtoMapper<User, UserDTO> {

    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    User toEntity(UserDTO dto, @Context PasswordEncoder passwordEncoder);

    @Named("encodePassword")
    default String encodePassword(String password, @Context PasswordEncoder passwordEncoder) {
        return passwordEncoder.encode(password);
    }

}
