package com.hyena.springpageable.domain.post.domain;

import com.hyena.springpageable.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String content;

    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Post(String title, String content, String category, Member member) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.member = member;
    }
}
