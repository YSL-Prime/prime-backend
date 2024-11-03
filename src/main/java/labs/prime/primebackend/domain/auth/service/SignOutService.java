package labs.prime.primebackend.domain.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import labs.prime.primebackend.domain.auth.entity.repository.RefreshTokenRepository;
import labs.prime.primebackend.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignOutService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtProvider jwtProvider;

    public void execute(HttpServletRequest request) {

        String token = jwtProvider.resolveToken(request);

        if (token != null && jwtProvider.validateToken(token)) {
            refreshTokenRepository.deleteByToken(token);
        }
    }
}