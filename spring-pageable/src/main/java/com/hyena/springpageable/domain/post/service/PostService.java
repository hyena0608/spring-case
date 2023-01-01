package com.hyena.springpageable.domain.post.service;

import com.hyena.springpageable.domain.post.dto.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    Page<PostResponse> findPostsByCategory(String category, Pageable pageable);
}
