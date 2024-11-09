package labs.prime.primebackend.domain.post.presentation.dto.response;

import labs.prime.primebackend.domain.post.entity.Post;
import lombok.Builder;

import java.time.LocalDate;

public class PostListAdminViewElement {
    private String title;
    private LocalDate createdDate;
    private String email;  // 이메일 필드 추가

    public PostListAdminViewElement(Post post) {
        this.title = post.getTitle();
        this.createdDate = post.getCreatedDate();
        this.email = post.getAuthor().getEmail();
    }
}
