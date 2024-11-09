package labs.prime.primebackend.domain.post.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ViewPostResponse {
    private String title;
    private LocalDate creationDate;
    private String content;
}
