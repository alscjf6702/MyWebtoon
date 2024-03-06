package org.webtoon.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "replyWebtoon")
public class ReplyWebtoon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "내용을 입력하세요.")
    @Column(nullable = false)
    private String content;

    @NotBlank(message = "작성자를 입력하세요.")
    @Column(nullable = false)
    private String author;




}

