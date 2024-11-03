package labs.prime.primebackend.domain.auth.entity.repository;

import labs.prime.primebackend.domain.auth.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, UUID> {
    RefreshToken findByToken(String token);
    void deleteByToken(String email);
}