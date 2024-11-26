package com.xorigin.doctorappointmentmanagementsystem.auth;

import com.xorigin.doctorappointmentmanagementsystem.core.jwt.IssuedRefreshToken;
import com.xorigin.doctorappointmentmanagementsystem.core.jwt.IssuedRefreshTokenRepository;
import com.xorigin.doctorappointmentmanagementsystem.core.jwt.JwtService;
import com.xorigin.doctorappointmentmanagementsystem.users.User;
import com.xorigin.doctorappointmentmanagementsystem.users.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final IssuedRefreshTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository repository;
    private final AuthMapper authMapper;

    public AuthService(
            IssuedRefreshTokenRepository tokenRepository,
            UserRepository repository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager,
            AuthMapper authMapper,
            UserDetailsService userDetailsService
    ) {
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.authMapper = authMapper;
        this.userDetailsService = userDetailsService;
    }

    public UserAuthResponseDTO register(RegisterDTO dto) {
        User user = authMapper.toEntity(dto, passwordEncoder);
        user = repository.save(user);
        return authMapper.toRetrieveDto(user);
    }

    public AuthResponseDTO login(LoginDTO dto, HttpServletResponse response) {
        User user = authenticate(dto);
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        createIssuedRefreshToken(user, refreshToken);
        jwtService.setCookies(response, accessToken, refreshToken);
        return authMapper.toRetrieveDto(user, accessToken, refreshToken);
    }

    public void logout(HttpServletResponse response) {
        jwtService.revokeCookies(response);
    }

    public AuthResponseDTO refresh(String refreshToken, HttpServletRequest request, HttpServletResponse response) {
        Optional<String> token = Optional.ofNullable(refreshToken).or(() -> jwtService.resolveRefreshToken(request));
        if (token.isEmpty())
            throw new BadCredentialsException("Refresh token is missing");

        String userId;
        UUID jti;

        try {
            if (!jwtService.isRefreshToken(token.get()))
                throw new BadCredentialsException("Invalid refresh token");

            userId = jwtService.extractUsername(token.get());
            if (userId == null)
                throw new BadCredentialsException("Invalid refresh token");

            jti = UUID.fromString(jwtService.extractJti(token.get()));
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException e) {
            throw new BadCredentialsException(e.getLocalizedMessage());
        }

        Optional<IssuedRefreshToken> tokenOptional = getIssuedRefreshToken(jti);
        if (tokenOptional.isEmpty())
            throw new BadCredentialsException("Refresh token is revoked");

        revokeIssuedRefreshToken(tokenOptional.get());

        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        String accessToken = jwtService.generateAccessToken(userDetails);
        String newRefreshToken = jwtService.generateRefreshToken(userDetails);

        createIssuedRefreshToken(tokenOptional.get().getUser(), newRefreshToken);
        jwtService.setCookies(response, accessToken, newRefreshToken);
        return authMapper.toRetrieveDto(null, accessToken, newRefreshToken);
    }

    private User authenticate(LoginDTO dto) {
        // For security reasons, unify the exception message
        try {
            User user = repository.findByEmail(dto.getEmail())
                    .orElseThrow(() -> new BadCredentialsException(""));
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user,
                            dto.getPassword()
                    )
            );

            return user;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email or password");
        }
    }

    private void createIssuedRefreshToken(User user, String refreshToken) {
        IssuedRefreshToken issuedRefreshToken = IssuedRefreshToken
                .builder()
                .jti(UUID.fromString(jwtService.extractJti(refreshToken)))
                .user(user)
                .expiresAt(jwtService.extractExpiration(refreshToken).toInstant())
                .isRevoked(false)
                .build();

        tokenRepository.save(issuedRefreshToken);
    }

    private Optional<IssuedRefreshToken> getIssuedRefreshToken(UUID jti) {
        return tokenRepository.findByJtiAndIsRevokedIsFalse(jti);
    }

    private void revokeIssuedRefreshToken(IssuedRefreshToken issuedRefreshToken) {
        issuedRefreshToken.setIsRevoked(true);
        tokenRepository.save(issuedRefreshToken);
    }

}