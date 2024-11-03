package labs.prime.primebackend.domain.auth.service;

import labs.prime.primebackend.domain.auth.presentation.dto.request.SignUpRequest;
import labs.prime.primebackend.domain.user.entity.User;
import labs.prime.primebackend.domain.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SignupService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public void execute(SignUpRequest signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.email())) {
            throw new IllegalArgumentException("이 부분은 익셉션 코드로 대체 될 것 입니다.");
        }

        User user = User.builder()
                .id(UUID.randomUUID())
                .name(signUpRequest.name())
                .email(signUpRequest.email())
                .password(encodePassword(signUpRequest.password()))
                .build();

        userRepository.save(user);
    }

    private Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}