package labs.prime.primebackend.global.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import labs.prime.primebackend.domain.auth.entity.RefreshToken;
import labs.prime.primebackend.domain.auth.entity.repository.RefreshTokenRepository;
import labs.prime.primebackend.domain.auth.presentation.dto.response.TokenResponse;
import labs.prime.primebackend.global.security.auth.AuthDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private static final String AUTHORITIES_KEY = "access_token";
    private static final String REFRESH_AUTHORITIES_KEY = "refresh_token";
    private static final String TOKEN_TYPE = "typ";
    private static final String TOKEN_ROLE = "role";
    private static final String SUBJECT = "sub";

    private final JwtProperties jwtProperties;
    private final AuthDetailService authDetailService;
    private final RefreshTokenRepository refreshTokenRepository;

    private SecretKey signingKey;

    public TokenResponse generateToken(String email, String role) {
        String accessToken = createToken(email, role, AUTHORITIES_KEY, jwtProperties.getExpiration());
        String refreshToken = createToken(email, role, REFRESH_AUTHORITIES_KEY, jwtProperties.getRefreshExpiration());

        saveRefreshToken(email, refreshToken);

        return new TokenResponse(accessToken, refreshToken);
    }

    private String createToken(String email, String role, String type, Long expirationSeconds) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(email)
                .setHeaderParam(TOKEN_TYPE, type)
                .claim(TOKEN_ROLE, role)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(expirationSeconds)))
                .signWith(getSigningKey())
                .compact();
    }

    private void saveRefreshToken(String email, String refreshToken) {
        RefreshToken tokenEntity = RefreshToken.builder()
                .id(UUID.randomUUID())
                .email(email)
                .token(refreshToken)
                .timeToLive(jwtProperties.getRefreshExpiration() * 1000)
                .build();

        refreshTokenRepository.save(tokenEntity);
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith(jwtProperties.getPrefix())) {
            return bearerToken.substring(jwtProperties.getPrefix().length()).trim();
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isRefreshToken(String token) {
        Claims claims = getClaims(token);
        return REFRESH_AUTHORITIES_KEY.equals(claims.get(TOKEN_TYPE));
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        UserDetails userDetails = authDetailService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Key getSigningKey() {
        if (signingKey == null) {
            byte[] keyBytes = jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8);
            signingKey = Keys.hmacShaKeyFor(keyBytes);
        }
        return signingKey;
    }

    public Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new JwtException("Token expired", e);
        } catch (Exception e) {
            throw new JwtException("Invalid token", e);
        }
    }

    public String getRole(String token) {
        return getClaims(token).get(TOKEN_ROLE, String.class);
    }
}