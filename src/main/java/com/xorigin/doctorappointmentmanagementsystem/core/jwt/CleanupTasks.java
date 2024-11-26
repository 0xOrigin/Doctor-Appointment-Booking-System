package com.xorigin.doctorappointmentmanagementsystem.core.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class CleanupTasks {

    private final IssuedRefreshTokenRepository tokenRepository;

    public CleanupTasks(IssuedRefreshTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Scheduled(fixedRate = 60, timeUnit = TimeUnit.MINUTES)
    public void deleteExpiredTokens() {
        log.info("Deleting expired jwt tokens");
        tokenRepository.deleteAllByExpiresAtBefore(Instant.now());
    }

}
