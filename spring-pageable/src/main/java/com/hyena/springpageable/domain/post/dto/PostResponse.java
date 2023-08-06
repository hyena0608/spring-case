package com.hyena.springpageable.domain.post.dto;

import com.hyena.springpageable.domain.member.domain.Member;
import com.hyena.springpageable.domain.post.domain.Post;

public record PostResponse(String title,
                           String content,
                           String category,
                           Member member) {

    public static PostResponse of(Post post) {
        return new PostResponse(
                post.getTitle(),
                post.getContent(),
                post.getCategory(),
                post.getMember()
        );
    }
}
