package com.hyena.springpageable.domain.member.domain;

import com.hyena.springpageable.domain.post.domain.Post;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "member")
    List<Post> posts = new ArrayList<>();

    public Member(String name) {
        this.name = name;
    }
}
