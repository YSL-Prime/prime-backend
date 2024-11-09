package labs.prime.primebackend.domain.post.presentation.controller;

import labs.prime.primebackend.domain.post.presentation.dto.request.PostAllowRequest;
import labs.prime.primebackend.domain.post.presentation.dto.request.PostCreateRequest;
import labs.prime.primebackend.domain.post.presentation.dto.request.PostUpdateRequest;
import labs.prime.primebackend.domain.post.presentation.dto.response.PostListAdminViewResponse;
import labs.prime.primebackend.domain.post.presentation.dto.response.PostListResponse;
import labs.prime.primebackend.domain.post.presentation.dto.response.ViewPostResponse;
import labs.prime.primebackend.domain.post.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostDeleteService deleteService;
    private final PostViewService viewService;
    private final PostCreateService createService;
    private final PostAllowService allowService;
    private final PostUpdateService updateService;

    @PostMapping("/write")
    private void postWrite(@RequestBody PostCreateRequest request) {
        createService.postCreate(request);
    }

    @DeleteMapping("/{id}")
    private void postDelete(@PathVariable("id") UUID postId, @RequestHeader("Authorization") String token) {
        deleteService.postDelete(postId, token);
    }

    @GetMapping("/{id}")
    private ViewPostResponse postView(@PathVariable("id") UUID id) {
        return viewService.viewPostDetails(id);
    }

    @GetMapping("/posts")
    private PostListResponse postsView() {
        return viewService.postsView();
    }

    @GetMapping("/admin/posts")
    private PostListAdminViewResponse postsAllowView() {
        return viewService.postsAllowView();
    }

    @PatchMapping("/{id}")
    private void postUpdate(@PathVariable("id") UUID id, @RequestBody PostUpdateRequest request, @RequestHeader("Authorization") String token) {
        updateService.updatePost(id, request, token);
    }

    @PatchMapping("/admin/{id}")
    private void postAllow(@PathVariable("id") UUID id, @RequestBody PostAllowRequest request, @RequestHeader("Authorization") String token) {
        allowService.allow(id, request, token);
    }
}
