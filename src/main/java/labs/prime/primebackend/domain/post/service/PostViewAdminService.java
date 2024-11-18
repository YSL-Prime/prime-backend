package labs.prime.primebackend.domain.post.service;

import jakarta.servlet.http.HttpServletRequest;
import labs.prime.primebackend.domain.post.entity.Post;
import labs.prime.primebackend.domain.post.entity.facade.PostFacade;
import labs.prime.primebackend.domain.post.entity.repository.PostRepository;
import labs.prime.primebackend.domain.post.presentation.dto.response.PostListAdminViewElement;
import labs.prime.primebackend.domain.post.presentation.dto.response.PostListAdminViewResponse;
import labs.prime.primebackend.domain.post.presentation.dto.response.PostViewAdminResponse;
import labs.prime.primebackend.domain.user.entity.exception.UserAuthorizedException;
import labs.prime.primebackend.global.error.exception.PageNotFoundException;
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
public class PostViewAdminService {
    private final PostRepository postRepository;
    private final PostFacade postFacade;
    private final JwtProvider jwtProvider;

    @Transactional(readOnly = true)
    public PostViewAdminResponse viewPostAdminDetails(UUID postId, HttpServletRequest httpServletRequest) {
        Post post = postFacade.findPostById(postId);
        String token = jwtProvider.resolveToken(httpServletRequest);
        String role = jwtProvider.getRole(token);

        if (!Objects.equals(role, "ADMIN")) {
            throw new UserAuthorizedException();
        }

        return new PostViewAdminResponse(post);
    }

    @Transactional(readOnly = true)
    public PostListAdminViewResponse viewAdminPosts(HttpServletRequest request) {
        String token = jwtProvider.resolveToken(request);
        String role = jwtProvider.getRole(token);

        if (!Objects.equals(role, "ADMIN")) {
            throw new UserAuthorizedException();
        }

        List<PostListAdminViewElement> postList = postRepository.findAll()
                .stream()
                .map(post -> {
                    return PostListAdminViewElement.builder()
                            .title(post.getTitle())
                            .email(post.getAuthor().getEmail())
                            .createdDate(post.getCreatedDate())
                            .build();
                })
                .collect(Collectors.toList());

        return new PostListAdminViewResponse(postList);
    }


    @Transactional(readOnly = true)
    public PostListAdminViewResponse viewAdminPostsByType(String type, HttpServletRequest request) {
        String token = jwtProvider.resolveToken(request);
        String role = jwtProvider.getRole(token);
        boolean postType = false;

        if (!Objects.equals(role, "ADMIN")) {
            throw new UserAuthorizedException();
        }

        if ("allowed".equals(type)) {
            postType = true;
        } else if ("notAllowed".equals(type)) {
            postType = false;
        } else {
            throw new PageNotFoundException();
        }

        List<PostListAdminViewElement> postList = postRepository.findAllByAllow(postType)
                .stream()
                .map(post -> {
                    return PostListAdminViewElement.builder()
                            .title(post.getTitle())
                            .email(post.getAuthor().getEmail())
                            .createdDate(post.getCreatedDate())
                            .build();
                })
                .collect(Collectors.toList());

        return new PostListAdminViewResponse(postList);
    }
}
