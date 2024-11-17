package labs.prime.primebackend.domain.post.entity.facade;

import labs.prime.primebackend.domain.post.entity.Post;
import labs.prime.primebackend.domain.post.entity.repository.PostRepository;
import labs.prime.primebackend.domain.post.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PostFacade {
    private final PostRepository postRepository;

    public Post findPostById(UUID id) {
        return postRepository.findById(id).orElseThrow(() -> PostNotFoundException.EXCEPTION);
    }
}
