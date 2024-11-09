package labs.prime.primebackend.domain.post.presentation.controller;

import labs.prime.primebackend.domain.post.presentation.dto.request.PostAllowRequest;
import labs.prime.primebackend.domain.post.presentation.dto.request.PostCreateRequest;
import labs.prime.primebackend.domain.post.presentation.dto.request.PostUpdateRequest;
import labs.prime.primebackend.domain.post.presentation.dto.response.PostListAdminViewResponse;
import labs.prime.primebackend.domain.post.presentation.dto.response.PostListResponse;
import labs.prime.primebackend.domain.post.presentation.dto.response.ViewPostAdminResponse;
import labs.prime.primebackend.domain.post.presentation.dto.response.ViewPostResponse;
import labs.prime.primebackend.domain.post.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class PostController {
    private final PostDeleteService deleteService;
    private final PostViewService viewService;
    private final PostCreateService createService;
    private final PostAllowService allowService;
    private final PostUpdateService updateService;

    @PostMapping("/post/write")
    private void postWrite(@RequestBody PostCreateRequest request) {
        createService.postCreate(request);
    }

    @DeleteMapping("/post/{id}")
    private void postDelete(@PathVariable("id") UUID postId, @RequestHeader("Authorization") String token) {
        deleteService.postDelete(postId, token);
    }

    @GetMapping("/post/{id}")
    private ViewPostResponse postView(@PathVariable("id") UUID id) {
        return viewService.viewPostDetails(id);
    }

    @GetMapping("/post/all")
    private PostListResponse postsView() {
        return viewService.postsView();
    }

    @GetMapping("/admin/post/{id}")
    private ViewPostAdminResponse postAdminViewDetails(@PathVariable("id") UUID id, @RequestHeader("Authorization") String token) {
        return viewService.viewPostAdminDetails(id, token);
    }

    @GetMapping("/admin/post/all")
    private PostListAdminViewResponse postsAdminView(@RequestHeader("Authorization") String token) {
        return viewService.viewAdminPosts(token);
    }

    @PatchMapping("/post/update/{id}")
    private void postUpdate(@PathVariable("id") UUID id, @RequestBody PostUpdateRequest request, @RequestHeader("Authorization") String token) {
        updateService.updatePost(id, request, token);
    }

    @PatchMapping("/admin/post/{id}")
    private void postAllow(@PathVariable("id") UUID id, @RequestBody PostAllowRequest request, @RequestHeader("Authorization") String token) {
        allowService.allow(id, request, token);
    }
}
