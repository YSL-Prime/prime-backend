package labs.prime.primebackend.domain.auth.presentation.dto.response;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}