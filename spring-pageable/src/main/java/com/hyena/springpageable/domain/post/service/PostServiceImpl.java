package com.hyena.springpageable.domain.post.service;

import com.hyena.springpageable.domain.post.dto.PostResponse;
import com.hyena.springpageable.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Page<PostResponse> findPostsByCategory(final String category, final Pageable pageable) {

        return postRepository.findByCategoryV2(category, pageable)
                .map(PostResponse::of);
    }
}
