package org.webtoon.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.webtoon.dto.MemberDTO;
import org.webtoon.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUserId(String userId);

    Optional<Member> findByPassword(String password);

    Member findByEmail(String email);

    Member findByPhoneNumber(String phoneNumber);




    @Query("update Member set password=:password where userId=:userId")
    @Modifying
    @Transactional
    void updateMemberByUserId(@Param("userId") String userId, @Param("password") String password);
}

