package labs.prime.primebackend.domain.auth.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import labs.prime.primebackend.domain.auth.presentation.dto.request.SignInRequest;
import labs.prime.primebackend.domain.auth.presentation.dto.request.SignUpRequest;
import labs.prime.primebackend.domain.auth.presentation.dto.response.TokenResponse;
import labs.prime.primebackend.domain.auth.service.SignInService;
import labs.prime.primebackend.domain.auth.service.SignOutService;
import labs.prime.primebackend.domain.auth.service.SignupService;
import labs.prime.primebackend.domain.auth.service.TokenRefreshService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final SignInService signInService;
    private final SignupService signupService;
    private final SignOutService signOutService;

    private final TokenRefreshService tokenRefreshService;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody SignUpRequest signUpRequest) {
        signupService.execute(signUpRequest);
    }

    @PostMapping("/sign-in")
    public TokenResponse signIn(@RequestBody SignInRequest signinRequest) {
        return signInService.execute(signinRequest);
    }

    @PostMapping("/sign-out")
    public void signOut(HttpServletRequest httpServletRequest) {
        signOutService.execute(httpServletRequest);
    }

    @PostMapping("/refresh")
    public TokenResponse tokenRefresh(@RequestHeader("X-Refresh-Token") String refreshToken) {
        return tokenRefreshService.execute(refreshToken);
    }
}