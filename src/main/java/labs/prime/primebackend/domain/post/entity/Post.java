package labs.prime.primebackend.domain.post.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import labs.prime.primebackend.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.awt.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private boolean allow;

    @Size(min = 5, max = 15) // title을 최소 5자, 최대 15자로 제한
    @Column(nullable = false, length = 15)
    private String title;

    @Size(min = 10, max = 2000) // content를 최소 10자, 최대 2000자로 제한
    @Column(nullable = false, length = 2000)
    private String content;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    public void update(String title, String content, Boolean allow) {
        this.title = title;
        this.content = content;
        this.allow = allow;
    }

    public void allowAdmin(Boolean allow) {
        this.allow = allow;
    }
}
