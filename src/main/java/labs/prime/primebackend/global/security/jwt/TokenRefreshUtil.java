package labs.prime.primebackend.global.security.jwt;

import labs.prime.primebackend.domain.auth.entity.RefreshToken;
import labs.prime.primebackend.domain.auth.entity.repository.RefreshTokenRepository;
import labs.prime.primebackend.domain.auth.presentation.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenRefreshUtil {

    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtProvider jwtProvider;

    public TokenResponse tokenResponse(String token) {
        if(jwtProvider.isRefreshToken(token)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }

        RefreshToken refreshToken = refreshTokenRepository.findByToken(token);

        String email  = refreshToken.getEmail();
        String role = jwtProvider.getRole(token);

        return jwtProvider.generateToken(email, role);
    }
}