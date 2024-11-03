package labs.prime.primebackend.domain.auth.service;

import labs.prime.primebackend.domain.auth.presentation.dto.response.TokenResponse;
import labs.prime.primebackend.global.security.jwt.TokenRefreshUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenRefreshService {

    private final TokenRefreshUtil tokenRefreshUtil;

    public TokenResponse execute(String refreshToken) {
        return tokenRefreshUtil.tokenResponse(refreshToken);
    }
}