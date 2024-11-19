package com.xorigin.doctorappointmentmanagementsystem.users;


import com.xorigin.doctorappointmentmanagementsystem.core.entities.BaseAuditEntity;
import com.xorigin.doctorappointmentmanagementsystem.core.filefields.annotations.UploadLocation;
import com.xorigin.doctorappointmentmanagementsystem.core.filefields.StorageAwareMultipartFile;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "AuthUser")
@AssociationOverride(
        name = "createdBy",
        joinColumns = @JoinColumn(name = "created_by", nullable = true, updatable = false)
)
public class User extends BaseAuditEntity implements UserDetails {

    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false)
    @ColumnDefault("true")
    @Builder.Default
    private Boolean isActive = true;

    private OffsetDateTime lastLogin;

    private LocalDate dateOfBirth;

    private LocalTime enTime;

    @UploadLocation("users/pictures")
    private StorageAwareMultipartFile picture;

    @Override
    public String getUsername() {
        return super.getId().toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
