package com.xorigin.doctorappointmentmanagementsystem.users;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.UuidSingleDtoGenericService;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.base.MessageByLocaleService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends UuidSingleDtoGenericService<User, UserRepository, UserMapper, UserDTO> {

    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserProvider userProvider,
            UserRepository repository,
            UserMapper mapper,
            UserSpecification spec,
            PasswordEncoder passwordEncoder,
            MessageByLocaleService messageByLocaleService
    ) {
        super(userProvider, repository, mapper, spec, messageByLocaleService);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected User getInstanceFromCreateDto(UserDTO dto) {
        return getMapper().toEntity(dto, passwordEncoder);
    }

    @Override
    protected void updateInstanceFromUpdateDto(User instance, UserDTO userDTO) {
        super.updateInstanceFromUpdateDto(instance, userDTO);
    }

}
