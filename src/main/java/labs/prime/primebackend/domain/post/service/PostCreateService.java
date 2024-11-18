package labs.prime.primebackend.domain.post.service;

import labs.prime.primebackend.domain.post.entity.Post;
import labs.prime.primebackend.domain.post.entity.repository.PostRepository;
import labs.prime.primebackend.domain.post.presentation.dto.request.PostCreateRequest;
import labs.prime.primebackend.domain.user.entity.User;
import labs.prime.primebackend.domain.user.entity.facade.UserFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostCreateService {
    private final PostRepository postRepository;
    private final UserFacade userFacade;

    public void postCreate(PostCreateRequest request) {
        User user = userFacade.getCurrentUser();

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(user)
                .allow(false)
                .build();

        postRepository.save(post);
    }
}
