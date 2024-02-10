package org.webtoon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.webtoon.entity.Member;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Long id;
    private String userName;
    private String userId;
    private String password;
    private String email;
    private String phoneNumber;


    public MemberDTO toDto(Member member) {
        MemberDTO dto = new MemberDTO();
        dto.setId(member.getId());
        dto.setUserName(member.getUserName());
        dto.setUserId(member.getUserId());
        dto.setPassword(member.getPassword());
        dto.setEmail(member.getEmail());
        dto.setPhoneNumber(member.getPhoneNumber());
        return dto;
    }


}

