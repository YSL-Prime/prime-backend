package labs.prime.primebackend.domain.post.service;

import labs.prime.primebackend.domain.post.entity.Post;
import labs.prime.primebackend.domain.post.entity.facade.PostFacade;
import labs.prime.primebackend.domain.post.entity.repository.PostRepository;
import labs.prime.primebackend.domain.post.presentation.dto.response.*;
import labs.prime.primebackend.domain.user.entity.exception.UserAuthorizedException;
import labs.prime.primebackend.global.security.jwt.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostViewService {
    private final PostRepository postRepository;
    private final PostFacade postFacade;

    @Transactional(readOnly = true)
    public PostViewResponse viewPostDetails(UUID postId) {
        Post post = postFacade.findPostById(postId);

        if (Objects.equals(post.isAllow(), false)) {
            throw new UserAuthorizedException();
        }

        return new PostViewResponse(
                post.getTitle(),
                post.getCreatedDate(),
                post.getContent()
        );
    }

    @Transactional(readOnly = true)
    public PostListResponse postsView() {
        List<PostListElement> postList = postRepository.findAllByAllow(true)
                .stream()
                // 익명함수, 람다식
                .map(post -> {
                    return PostListElement.builder()
                            .title(post.getTitle())
                            .createdDate(post.getCreatedDate())
                            .build();
                })
                .collect(Collectors.toList());

        return new PostListResponse(postList);
    }

}
