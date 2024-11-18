package labs.prime.primebackend.domain.post.service;

import jakarta.servlet.http.HttpServletRequest;
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

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostUpdateService {
    private final PostRepository postRepository;
    private final JwtProvider jwtProvider;
    private final PostFacade postFacade;
    private final UserFacade userFacade;

    public void updatePost(UUID id, PostUpdateRequest request, HttpServletRequest httpServletRequest) {
        User user = userFacade.getCurrentUser();
        Post post = postFacade.findPostById(id);
        String token = jwtProvider.resolveToken(httpServletRequest);
        String role = jwtProvider.getRole(token);


        post.update(
                request.getTitle(),
                request.getContent(),
                false
        );

        System.out.println("이메일 " + post.getAuthor().getEmail() + "post " + post.getTitle());

        if (Role.ADMIN.name().equals(role) || user.getEmail().equals(post.getAuthor().getEmail())) {
            postRepository.save(post);
        }

        throw new UserAuthorizedException();

    }
}
