package labs.prime.primebackend.domain.auth.presentation.dto.request;

public record SignInRequest(
        String email,
        String password
) {
}
