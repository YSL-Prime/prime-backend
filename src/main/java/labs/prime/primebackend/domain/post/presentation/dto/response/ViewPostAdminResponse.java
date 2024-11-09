package labs.prime.primebackend.domain.post.presentation.dto.response;

import labs.prime.primebackend.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ViewPostAdminResponse {
    private String title;
    private String email;  // 이메일 필드 추가
    private LocalDate createdDate;
    private String content;

    public ViewPostAdminResponse(Post post) {
        this.title = post.getTitle();
        this.email = post.getAuthor().getEmail();
        this.createdDate = post.getCreatedDate();
        this.content = post.getContent();
    }
}
