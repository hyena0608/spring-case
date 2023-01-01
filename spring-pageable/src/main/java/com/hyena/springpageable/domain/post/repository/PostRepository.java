package com.hyena.springpageable.domain.post.repository;

import com.hyena.springpageable.domain.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select p from Post p left join Member m on p.member.id = m.id")
    Page<Post> findByCategoryV1(String category, Pageable pageable);

    @Query(value = "select p from Post p left join Member m on p.member.id = m.id",
            countQuery = "select count(p) from Post p")
    Page<Post> findByCategoryV2(String category, Pageable pageable);
}
