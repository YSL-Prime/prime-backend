package labs.prime.primebackend.domain.post.service;

import jakarta.servlet.http.HttpServletRequest;
import labs.prime.primebackend.domain.post.entity.Post;
import labs.prime.primebackend.domain.post.entity.facade.PostFacade;
import labs.prime.primebackend.domain.post.entity.repository.PostRepository;
import labs.prime.primebackend.domain.user.entity.exception.UserAuthorizedException;
import labs.prime.primebackend.global.security.jwt.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostAllowService {
    private final PostRepository postRepository;
    private final JwtProvider jwtProvider;
    private final PostFacade postFacade;

    public void allow(UUID id, HttpServletRequest request) {
        String token = jwtProvider.resolveToken(request);
        String role = jwtProvider.getRole(token);
        Post post = postFacade.findPostById(id);

        if (!Objects.equals(role, "ADMIN")) {
            throw new UserAuthorizedException();
        }

        post.allowAdmin(!post.isAllow());

        postRepository.save(post);

    }
}
