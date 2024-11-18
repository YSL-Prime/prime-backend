package labs.prime.primebackend.domain.post.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class PostListElement {
    private String title;
    private LocalDate createdDate;
}
