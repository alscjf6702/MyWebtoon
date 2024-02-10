package org.webtoon.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.webtoon.dto.MemberDTO;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false, updatable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    public static Member fromDto(MemberDTO dto) {
        return Member.builder()
                .id(dto.getId())
                .userName(dto.getUserName())
                .userId(dto.getUserId())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public void change(MemberDTO dto) {
        this.userName = dto.getUserName();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.phoneNumber = dto.getPhoneNumber();
    }



}

