package labs.prime.primebackend.domain.post.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostListAdminViewResponse {
    private final List<PostListAdminViewElement> postList;
}
