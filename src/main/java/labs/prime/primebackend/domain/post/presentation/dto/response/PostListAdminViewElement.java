package labs.prime.primebackend.domain.post.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class PostListAdminViewElement {
    private String title;
    private LocalDate createdDate;
    private String email;
}
