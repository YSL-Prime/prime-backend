package labs.prime.primebackend.domain.auth.service;

import labs.prime.primebackend.domain.auth.presentation.dto.request.SignInRequest;
import labs.prime.primebackend.domain.auth.presentation.dto.response.TokenResponse;
import labs.prime.primebackend.domain.user.entity.User;
import labs.prime.primebackend.domain.user.entity.facade.UserFacade;
import labs.prime.primebackend.global.security.auth.role.Role;
import labs.prime.primebackend.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInService {

    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;

    private final UserFacade userFacade;

    public TokenResponse execute(SignInRequest request) {
        User user = userFacade.getUserByEmail(request.email());

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다."); // 추후 커스텀 익셉션으로 변경
        }

        return jwtProvider.generateToken(user.getEmail(), Role.ROLE_USER.toString());
    }
}