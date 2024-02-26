package org.webtoon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.webtoon.entity.Member;
import org.webtoon.repository.MemberRepository;
import org.webtoon.role.MemberRole;
import org.webtoon.service.MemberService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberSecurityService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Member member = this.memberRepository.findByUserId(userId);
        System.out.println(member+"==================================================");
        if (member == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        List<GrantedAuthority> authorityList = new ArrayList<>();
        if ("admin".equals(userId)) {
            authorityList.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        }else {
            authorityList.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
        }

        return new User(member.getUserId(), member.getPassword(), authorityList);

    }
}

