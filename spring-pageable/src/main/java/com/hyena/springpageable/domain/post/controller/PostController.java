package com.hyena.springpageable.domain.post.controller;

import com.hyena.springpageable.base.domain.PageCustomResponse;
import com.hyena.springpageable.domain.post.dto.PostResponse;
import com.hyena.springpageable.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/v1/posts")
    public PageCustomResponse<PostResponse> getPostsByCategoryV1(
            String category,
            @PageableDefault(size = 30, sort = "id", direction = DESC) Pageable pageable
    ) {
        Page<PostResponse> page = postService.findPostsByCategory(category, pageable);
        return new PageCustomResponse<>(page.getContent(), page.getPageable(), page.getTotalElements());

    }

    @GetMapping("/v2/posts")
    public PageCustomResponse<PostResponse> getPostsByCategoryV2(
            String category,
            Pageable pageable
    ) {
        Page<PostResponse> page = postService.findPostsByCategory(category, pageable);
        return new PageCustomResponse<>(page.getContent(), page.getPageable(), page.getTotalElements());
    }
}
