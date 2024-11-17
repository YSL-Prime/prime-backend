package labs.prime.primebackend.domain.post.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import labs.prime.primebackend.domain.post.presentation.dto.request.PostCreateRequest;
import labs.prime.primebackend.domain.post.presentation.dto.request.PostUpdateRequest;
import labs.prime.primebackend.domain.post.presentation.dto.response.PostListAdminViewResponse;
import labs.prime.primebackend.domain.post.presentation.dto.response.PostListResponse;
import labs.prime.primebackend.domain.post.presentation.dto.response.PostViewAdminResponse;
import labs.prime.primebackend.domain.post.presentation.dto.response.PostViewResponse;
import labs.prime.primebackend.domain.post.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class PostController {
    private final PostCreateService createService;
    private final PostDeleteService deleteService;
    private final PostViewService viewService;
    private final PostUpdateService updateService;


    //포스트 작성
    @PostMapping("/post/write")
    private void postWrite(@RequestBody PostCreateRequest request) {
        createService.postCreate(request);
    }

    //포스트 조회
    @GetMapping("/post/{id}")
    private PostViewResponse postView(@PathVariable("id") UUID id) {
        return viewService.viewPostDetails(id);
    }

    //포스트 리스트 조회
    @GetMapping("/posts")
    private PostListResponse postsView() {
        return viewService.postsView();
    }

    //포스트 수정
    @PatchMapping("/post/{id}")
    private void postUpdate(@PathVariable("id") UUID id, @RequestBody PostUpdateRequest request, HttpServletRequest httpRequest) {
        updateService.updatePost(id, request, httpRequest);
    }

    //포스트 삭제
    @DeleteMapping("/post/{id}")
    private void postDelete(@PathVariable("id") UUID postId, HttpServletRequest request) {
        deleteService.postDelete(postId, request);
    }
}
