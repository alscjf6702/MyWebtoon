package org.webtoon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webtoon.dto.MemberDTO;
import org.webtoon.entity.Member;
import org.webtoon.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public void regMember(MemberDTO dto){

        Member member = Member.builder()
                .userId(dto.getUserId())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .userName(dto.getUserName())
                .build();

        memberRepository.save(member);

    }

    public List<Member> memberList(){
        List<Member> list = memberRepository.findAll();

        return list;
    }

    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }

    public void updateMember(MemberDTO memberDto){

        Member existingMember = memberRepository.findByUserId(memberDto.getUserId());

        if (existingMember != null) {
            existingMember.change(memberDto);
            memberRepository.save(existingMember);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public Member getMember(Long id){
        return memberRepository.findById(id).orElse(null);
    }


    public Member findByUserId(String userId) {

        return memberRepository.findByUserId(userId);
    }
}

