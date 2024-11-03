package labs.prime.primebackend.domain.auth.presentation.dto.request;

public record SignUpRequest(
        String name,
        String email,
        String password
) {
}