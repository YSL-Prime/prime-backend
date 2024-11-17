package labs.prime.primebackend.domain.post.presentation.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class PostListResponse {
    private final List<PostListElement> postList;

    public PostListResponse(List<PostListElement> postList) {
        this.postList = postList;
    }
}
