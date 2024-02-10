package org.webtoon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webtoon.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUserId(String userId);

}

