package labs.prime.primebackend.domain.post.service;

import labs.prime.primebackend.domain.auth.entity.type.Role;
import labs.prime.primebackend.domain.post.entity.Post;
import labs.prime.primebackend.domain.post.entity.facade.PostFacade;
import labs.prime.primebackend.domain.post.entity.repository.PostRepository;
import labs.prime.primebackend.domain.post.presentation.dto.request.PostUpdateRequest;
import labs.prime.primebackend.domain.user.entity.User;
import labs.prime.primebackend.domain.user.entity.exception.UserAuthorizedException;
import labs.prime.primebackend.domain.user.entity.exception.UserNotCorrectException;
import labs.prime.primebackend.domain.user.entity.facade.UserFacade;
import labs.prime.primebackend.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostUpdateService {
    private final PostRepository postRepository;
    private final JwtProvider jwtProvider;
    private final PostFacade postFacade;
    private final UserFacade userFacade;

    public void updatePost(UUID id, PostUpdateRequest request, String token) {
        User user = userFacade.getCurrentUser();
        Post post = postFacade.foundPostById(id);
        String role = jwtProvider.getRole(token);

        // ADMIN 권한을 가진 경우
        if (Role.ADMIN.name().equals(role)) {
            postRepository.delete(post);
            return;
        }

        if (post.getAuthor().getEmail().equals(user.getEmail())) {
            postRepository.delete(post);
        } else {
            throw new UserAuthorizedException();
        }

        post.update(
                request.getTitle(),
                request.getContent()
        );

        postRepository.save(post);
    }
}
