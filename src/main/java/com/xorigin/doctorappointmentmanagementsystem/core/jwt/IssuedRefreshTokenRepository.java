package com.xorigin.doctorappointmentmanagementsystem.core.jwt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IssuedRefreshTokenRepository extends JpaRepository<IssuedRefreshToken, UUID> {

    Optional<IssuedRefreshToken> findByJtiAndIsRevokedIsFalse(UUID jti);

    void deleteAllByExpiresAtBefore(Instant now);

}
