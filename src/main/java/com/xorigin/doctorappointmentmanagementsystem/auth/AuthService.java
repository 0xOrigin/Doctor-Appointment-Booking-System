package com.xorigin.doctorappointmentmanagementsystem.auth;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.jwt.JwtService;
import com.xorigin.doctorappointmentmanagementsystem.users.User;
import com.xorigin.doctorappointmentmanagementsystem.users.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
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

@Service
public class AuthService {

//    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository repository;
    private final UserProvider userProvider;
    private final AuthMapper authMapper;

    public AuthService(
            UserProvider userProvider,
            UserRepository repository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager,
            AuthMapper authMapper,
            UserDetailsService userDetailsService
    ) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.userProvider = userProvider;
        this.authMapper = authMapper;
        this.userDetailsService = userDetailsService;
    }

    public UserAuthResponseDTO register(RegisterDTO dto, HttpServletResponse response) {
        User user = authMapper.toEntity(dto, passwordEncoder);
        user = repository.save(user);
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
//        saveUserToken(savedUser, jwtToken);
        return authMapper.toRetrieveDto(user);
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

    public AuthResponseDTO login(LoginDTO dto, HttpServletResponse response) {
        User user = authenticate(dto);
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        jwtService.setCookies(response, accessToken, refreshToken);
//        revokeAllUserTokens(user);
//        saveUserToken(user, jwtToken);
        return authMapper.toRetrieveDto(user, accessToken, refreshToken);
    }

    public AuthResponseDTO refresh(String refreshToken, HttpServletRequest request, HttpServletResponse response) {
        Optional<String> token = Optional.ofNullable(refreshToken).or(() -> jwtService.resolveRefreshToken(request));
        if (token.isEmpty())
            throw new BadCredentialsException("Refresh token is missing");

        String userId;

        try {
            if (!jwtService.isRefreshToken(token.get()))
                throw new BadCredentialsException("Invalid refresh token");

            userId = jwtService.extractUsername(token.get());
            if (userId == null)
                throw new BadCredentialsException("Invalid refresh token");
        } catch (ExpiredJwtException | MalformedJwtException e) {
            throw new BadCredentialsException(e.getLocalizedMessage());
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        String accessToken = jwtService.generateAccessToken(userDetails);
        String newRefreshToken = jwtService.generateRefreshToken(userDetails);
        jwtService.setCookies(response, accessToken, newRefreshToken);
        return authMapper.toRetrieveDto(null, accessToken, newRefreshToken);
    }

    public void logout(HttpServletResponse response) {
        jwtService.revokeCookies(response);
    }

//    private void saveUserToken(User user, String jwtToken) {
//        var token = Token.builder()
//                .user(user)
//                .token(jwtToken)
//                .tokenType(TokenType.BEARER)
//                .expired(false)
//                .revoked(false)
//                .build();
//        tokenRepository.save(token);
//    }

//    private void revokeAllUserTokens(User user) {
//        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
//        if (validUserTokens.isEmpty())
//            return;
//        validUserTokens.forEach(token -> {
//            token.setExpired(true);
//            token.setRevoked(true);
//        });
//        tokenRepository.saveAll(validUserTokens);
//    }

}