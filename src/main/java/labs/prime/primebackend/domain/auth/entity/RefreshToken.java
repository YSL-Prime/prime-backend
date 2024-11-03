package labs.prime.primebackend.domain.auth.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.util.UUID;

@Getter
@Builder
@RedisHash
public class RefreshToken {
    @Id
    private UUID id;

    @Indexed
    private String token;

    @TimeToLive
    private Long timeToLive;

    private String email;
}