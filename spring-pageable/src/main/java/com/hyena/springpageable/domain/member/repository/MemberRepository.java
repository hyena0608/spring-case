package com.hyena.springpageable.domain.member.repository;

import com.hyena.springpageable.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
