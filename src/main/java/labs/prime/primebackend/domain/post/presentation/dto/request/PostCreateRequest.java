package labs.prime.primebackend.domain.post.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostCreateRequest {
    private String title;
    private String content;
}
