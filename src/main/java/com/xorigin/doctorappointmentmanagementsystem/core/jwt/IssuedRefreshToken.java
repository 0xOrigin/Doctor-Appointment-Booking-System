package com.xorigin.doctorappointmentmanagementsystem.core.jwt;

import com.xorigin.doctorappointmentmanagementsystem.core.uuid.UUIDv7;
import com.xorigin.doctorappointmentmanagementsystem.users.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.UUID;

@Table(
    name = "issued_refresh_tokens",
    indexes = {
            @Index(name = "issued_refresh_tokens_jti_user_idx", columnList = "jti, user_id"),
            @Index(name = "issued_refresh_tokens_is_revoked_idx", columnList = "is_revoked"),
            @Index(name = "issued_refresh_tokens_expires_at_idx", columnList = "expires_at")
    }
)
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class IssuedRefreshToken {

    @Id
    @GeneratedValue
    @UUIDv7
    private UUID id;

    @Column(nullable = false, unique = true)
    private UUID jti;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ColumnDefault("false")
    private Boolean isRevoked;

    @Column(nullable = false)
    private Instant expiresAt;

}
