package org.webtoon.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.webtoon.entity.Member;

import java.util.List;

@SpringBootTest
@Log4j2
class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void regMember(){

        Member member = Member.builder()
                .userId("asd6702")
                .userName("김민철")
                .password("hello")
                .email("asd@naver.com")
                .phoneNumber("010-1233-1231")
                .build();

        memberRepository.save(member);
    }

    @Test
    public void selectMember(){
        Member member = memberRepository.findById(1L).orElse(null);

        log.info(member);

    }

    @Test
    public void listMember(){

        List<Member> all = memberRepository.findAll();

        log.info(all);

    }

    @Test
    public void updateMember(){

        Member member = Member.builder()
                .id(1L)
                .email("alscjf22@naver.com")
                .password("hi")
                .phoneNumber("010-4414-1423")
                .userName("이흐이")
                .build();

        memberRepository.save(member);

    }

    @Test
    public void deleteMember(){
        memberRepository.deleteById(1L);
    }

}