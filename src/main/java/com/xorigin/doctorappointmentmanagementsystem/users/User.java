package com.xorigin.doctorappointmentmanagementsystem.users;


import com.xorigin.doctorappointmentmanagementsystem.core.entities.BaseAuditEntity;
import com.xorigin.doctorappointmentmanagementsystem.core.filefields.listeners.FileDeletionListener;
import com.xorigin.doctorappointmentmanagementsystem.core.filefields.annotations.UploadLocation;
import com.xorigin.doctorappointmentmanagementsystem.core.filefields.StorageAwareMultipartFile;
import com.xorigin.doctorappointmentmanagementsystem.doctor.Doctor;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@EntityListeners(FileDeletionListener.class)
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

    @OneToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private Doctor doctor;

    public boolean isDoctor() {
        return role == Role.DOCTOR && doctor != null;
    }

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
