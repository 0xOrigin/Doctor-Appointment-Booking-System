package com.xorigin.doctorappointmentmanagementsystem.core.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    private final String secretKey;
    // In seconds
    private final Integer accessTokenExpiration;
    // In seconds
    private final Integer refreshTokenExpiration;
    private final String accessTokenCookieName;
    private final String refreshTokenCookieName;
    private final String accessTokenCookiePath;
    private final String refreshTokenCookiePath;
    private final Boolean cookiesHttpOnly;
    private final Boolean cookiesSecure;
    private final String cookiesSameSite;
    private final String TOKEN_HEADER = HttpHeaders.AUTHORIZATION;
    private final String TOKEN_PREFIX = "Bearer ";
    private final String CLAIM_KEY_TOKEN_TYPE = "tokenType";
    private final JwtParser jwtParser;

    public JwtService(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.expiration.access-token:10}") Integer accessTokenExpiration,
            @Value("${jwt.expiration.refresh-token:604800}") Integer refreshTokenExpiration,
            @Value("${jwt.cookie.access-token.name:accessToken}") String accessTokenCookieName,
            @Value("${jwt.cookie.access-token.path:/}") String accessTokenCookiePath,
            @Value("${jwt.cookie.refresh-token.name:refreshToken}") String refreshTokenCookieName,
            @Value("${jwt.cookie.refresh-token.path:/}") String refreshTokenCookiePath,
            @Value("${jwt.cookie.http-only:true}") Boolean cookiesHttpOnly,
            @Value("${jwt.cookie.secure:true}") Boolean cookiesSecure,
            @Value("${jwt.cookie.same-site:Lax}") String cookiesSameSite
    ) {
        this.secretKey = secretKey;
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
        this.accessTokenCookieName = accessTokenCookieName;
        this.refreshTokenCookieName = refreshTokenCookieName;
        this.accessTokenCookiePath = accessTokenCookiePath;
        this.refreshTokenCookiePath = refreshTokenCookiePath;
        this.cookiesHttpOnly = cookiesHttpOnly;
        this.cookiesSecure = cookiesSecure;
        this.cookiesSameSite = cookiesSameSite;

        this.jwtParser = Jwts.parser()
            .verifyWith(getSignInKey())
            .build();
    }

    private SecretKey getSignInKey() {
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    public Optional<String> resolveTokenFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX))
            return Optional.of(bearerToken.substring(TOKEN_PREFIX.length()));

        return Optional.empty();
    }

    public Optional<String> resolveTokenFromCookie(HttpServletRequest request, String cookieName) {
        if (request.getCookies() == null)
            return Optional.empty();

        Cookie cookie = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals(cookieName))
                .findFirst()
                .orElse(null);

        if (cookie != null)
            return Optional.of(cookie.getValue());

        return Optional.empty();
    }

    public Optional<String> resolveAccessToken(HttpServletRequest request) {
        return resolveTokenFromHeader(request)
                .or(() -> resolveTokenFromCookie(request, accessTokenCookieName));
    }

    public Optional<String> resolveRefreshToken(HttpServletRequest request) {
        return resolveTokenFromCookie(request, refreshTokenCookieName);
    }

    private Claims extractAllClaims(String token) {
        return jwtParser
            .parseSignedClaims(token)
            .getPayload();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public TokenType extractTokenType(String token) {
        return TokenType.fromValue(extractClaim(token, claims -> claims.get(CLAIM_KEY_TOKEN_TYPE, String.class)));
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, TokenType tokenType, Integer expiration) {
        extraClaims.put(CLAIM_KEY_TOKEN_TYPE, tokenType.getValue());
        return Jwts
            .builder()
            .claims(extraClaims)
            .subject(userDetails.getUsername())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + (expiration * 1000)))
            .signWith(getSignInKey())
            .compact();
    }

    public String generateAccessToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails, TokenType.ACCESS, accessTokenExpiration);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails, TokenType.REFRESH, refreshTokenExpiration);
    }

    public boolean isAccessToken(String token) {
        return extractTokenType(token) == TokenType.ACCESS;
    }

    public boolean isRefreshToken(String token) {
        return extractTokenType(token) == TokenType.REFRESH;
    }

    public Cookie createAccessTokenCookie(String accessToken, int accessTokenExpiration) {
        Cookie accessTokenCookie = new Cookie(accessTokenCookieName, accessToken);
        accessTokenCookie.setHttpOnly(cookiesHttpOnly);
        accessTokenCookie.setSecure(cookiesSecure);
        accessTokenCookie.setMaxAge(accessTokenExpiration);
        accessTokenCookie.setPath(accessTokenCookiePath);
        accessTokenCookie.setAttribute("SameSite", cookiesSameSite);
        return accessTokenCookie;
    }

    public Cookie createAccessTokenCookie(String accessToken) {
        return createAccessTokenCookie(accessToken, accessTokenExpiration);
    }

    public Cookie createRefreshTokenCookie(String refreshToken, int refreshTokenExpiration) {
        Cookie refreshTokenCookie = new Cookie(refreshTokenCookieName, refreshToken);
        refreshTokenCookie.setHttpOnly(cookiesHttpOnly);
        refreshTokenCookie.setSecure(cookiesSecure);
        refreshTokenCookie.setMaxAge(refreshTokenExpiration);
        refreshTokenCookie.setPath(refreshTokenCookiePath);
        refreshTokenCookie.setAttribute("SameSite", cookiesSameSite);
        return refreshTokenCookie;
    }

    public Cookie createRefreshTokenCookie(String refreshToken) {
        return createRefreshTokenCookie(refreshToken, refreshTokenExpiration);
    }

    public void setCookies(HttpServletResponse response, String accessToken, String refreshToken) {
        response.addCookie(createAccessTokenCookie(accessToken));
        response.addCookie(createRefreshTokenCookie(refreshToken));
    }

    public void revokeCookies(HttpServletResponse response) {
        Cookie accessTokenCookie = createAccessTokenCookie(null, 0);
        Cookie refreshTokenCookie = createRefreshTokenCookie(null, 0);

        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);
    }

}
