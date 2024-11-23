package com.xorigin.doctorappointmentmanagementsystem.auth;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base.BaseMapper;
import com.xorigin.doctorappointmentmanagementsystem.users.User;
import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthMapper extends BaseMapper {

    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    User toEntity(RegisterDTO dto, @Context PasswordEncoder passwordEncoder);

    @Named("encodePassword")
    default String encodePassword(String password, @Context PasswordEncoder passwordEncoder) {
        return passwordEncoder.encode(password);
    }

    AuthResponseDTO toRetrieveDto(User user, String accessToken, String refreshToken);

    UserAuthResponseDTO toRetrieveDto(User user);

}
