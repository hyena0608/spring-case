package com.hyena.springpageable.controller;

import com.hyena.springpageable.response.PostResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
public class PostController {

    @GetMapping
    public List<PostResponse> getPosts(
            @PageableDefault(size = 30, sort = "id", direction = DESC) Pageable pageable
    ) {
        // ...
        return List.of();
    }
}
