package labs.prime.primebackend.domain.post.presentation.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class PostListAdminViewResponse {
    private final List<PostListAdminViewElement> postList;

    public PostListAdminViewResponse(List<PostListAdminViewElement> postList) {
        this.postList = postList;
    }
}
