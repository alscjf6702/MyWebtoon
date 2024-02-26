package org.webtoon.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "이름을 입력해주세요.")
    private String userName;

    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 4, max = 16,message = "4~16자리의 아이디를 입력하세요.")
    private String userId;

    @Size(min = 6, max = 14, message = "6~14자리의 암호를 입력하세요.")
    private String password;

    @Email
    @NotBlank(message = "이메일을 입력하세요.")
    private String email;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 양식을 지켜주세요. 01x-xxx(x)-xxxx")
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

