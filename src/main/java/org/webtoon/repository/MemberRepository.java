package org.webtoon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webtoon.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUserId(String userId);


    Optional<Member> findByPassword(String password);

}

