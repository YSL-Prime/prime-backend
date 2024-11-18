package labs.prime.primebackend.domain.post.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PostViewResponse {
    private String title;
    private LocalDate creationDate;
    private String content;
}
