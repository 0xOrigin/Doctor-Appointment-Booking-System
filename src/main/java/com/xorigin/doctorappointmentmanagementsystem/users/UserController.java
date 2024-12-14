package com.xorigin.doctorappointmentmanagementsystem.users;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.UuidSingleDtoGenericCrudController;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerOptions;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerUtils;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.responses.ResponseFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController extends UuidSingleDtoGenericCrudController<User, UserService, UserDTO> {

    public UserController(
            @Qualifier("defaultPaginatedOptions") ControllerOptions options,
            ControllerUtils utils,
            ResponseFactory responseFactory,
            UserProvider userProvider,
            UserService service
    ) {
        super(options, utils, responseFactory, userProvider, service);
        getOptions().setPageSize(5);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> findAll(HttpServletRequest request, Pageable pageable) {
        return super.findAll(request, pageable);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> findOne(HttpServletRequest request, UUID id) {
        return super.findOne(request, id);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> createJson(HttpServletRequest request, UserDTO userDTO) {
        return super.createJson(request, userDTO);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateJson(HttpServletRequest request, UUID id, UserDTO userDTO) {
        return super.updateJson(request, id, userDTO);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> delete(HttpServletRequest request, UUID id) {
        return super.delete(request, id);
    }
}
