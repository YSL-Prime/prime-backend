package labs.prime.primebackend.domain.post.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import labs.prime.primebackend.domain.post.presentation.dto.response.PostListAdminViewResponse;
import labs.prime.primebackend.domain.post.presentation.dto.response.PostViewAdminResponse;
import labs.prime.primebackend.domain.post.service.PostAllowService;
import labs.prime.primebackend.domain.post.service.PostViewAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class PostAdminController {
    private final PostViewAdminService adminService;
    private final PostAllowService allowService;


    //포스트 상세 조회 (어드민)
    @GetMapping("/post/{id}")
    private PostViewAdminResponse postAdminViewDetails(@PathVariable("id") UUID id, HttpServletRequest httpServletRequest) {
        return adminService.viewPostAdminDetails(id, httpServletRequest);
    }

    //포스트 목록 조회 (어드민)
    @GetMapping("/posts")
    private PostListAdminViewResponse postsAdminView(@RequestParam("type") String type, HttpServletRequest request) {
        if("all".equals(type)) {
            return adminService.viewAdminPosts(request);
        }
        else {
            return adminService.viewAdminPostsByType(type, request);
        }

    }

    //포스트 노출 수락 여부 (어드민)
    @PatchMapping("/post/{id}")
    private void postAllow(@PathVariable("id") UUID id, HttpServletRequest request) {
        allowService.allow(id, request);
    }
}
