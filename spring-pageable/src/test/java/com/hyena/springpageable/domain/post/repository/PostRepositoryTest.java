package com.hyena.springpageable.domain.post.repository;

import com.hyena.springpageable.domain.member.domain.Member;
import com.hyena.springpageable.domain.member.repository.MemberRepository;
import com.hyena.springpageable.domain.post.domain.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    /**
     * Hibernate:
     *     select
     *         p1_0.id,
     *         p1_0.category,
     *         p1_0.content,
     *         p1_0.member_id,
     *         p1_0.title
     *     from
     *         post p1_0
     *     left join
     *         member m1_0
     *             on p1_0.member_id=m1_0.id offset ? rows fetch first ? rows only
     * Hibernate:
     *     select
     *         count(p1_0.id)
     *     from
     *         post p1_0
     *     left join
     *         member m1_0
     *             on p1_0.member_id=m1_0.id
     */
    @Test
    void 게시글_페이징_카운트_쿼리() {
        Member member = memberRepository.save(new Member("홍길동"));
        postRepository.save(new Post("안녕1", "안녕 애들아1", "잡담", member));
        postRepository.save(new Post("안녕2", "안녕 애들아2", "잡담", member));
        postRepository.save(new Post("안녕3", "안녕 애들아3", "잡담", member));
        postRepository.save(new Post("안녕4", "안녕 애들아4", "잡담", member));
        postRepository.save(new Post("안녕5", "안녕 애들아5", "잡담", member));
        postRepository.save(new Post("안녕6", "안녕 애들아6", "잡담", member));

        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Post> posts = postRepository.findByCategoryV1("잡담", pageRequest);

        assertThat(posts.getTotalElements()).isEqualTo(6);
        assertThat(posts.getTotalPages()).isEqualTo(2);
    }

    /**
     * Hibernate:
     *     select
     *         p1_0.id,
     *         p1_0.category,
     *         p1_0.content,
     *         p1_0.member_id,
     *         p1_0.title
     *     from
     *         post p1_0
     *     left join
     *         member m1_0
     *             on p1_0.member_id=m1_0.id offset ? rows fetch first ? rows only
     * Hibernate:
     *     select
     *         count(p1_0.id)
     *     from
     *         post p1_0
     */
    @Test
    void 게시글_페이징_카운트_쿼리_최적화() {
        Member member = memberRepository.save(new Member("홍길동"));
        postRepository.save(new Post("안녕1", "안녕 애들아1", "잡담", member));
        postRepository.save(new Post("안녕2", "안녕 애들아2", "잡담", member));
        postRepository.save(new Post("안녕3", "안녕 애들아3", "잡담", member));
        postRepository.save(new Post("안녕4", "안녕 애들아4", "잡담", member));
        postRepository.save(new Post("안녕5", "안녕 애들아5", "잡담", member));
        postRepository.save(new Post("안녕6", "안녕 애들아6", "잡담", member));

        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Post> posts = postRepository.findByCategoryV2("잡담", pageRequest);

        assertThat(posts.getTotalElements()).isEqualTo(6);
        assertThat(posts.getTotalPages()).isEqualTo(2);
    }
}