package labs.prime.primebackend.domain.post.entity.repository;

import labs.prime.primebackend.domain.post.entity.Post;
import labs.prime.primebackend.domain.post.presentation.dto.response.PostListElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    Optional<Post> findById(UUID id);
    List<PostListElement> findAllByAllow(Boolean allow);
}
