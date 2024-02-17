package org.webtoon.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import org.webtoon.entity.NoticeBoard;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {

    private Long id;

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;           //제목

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;         //내용

    private LocalDateTime regDate;  //등록일

    @NotBlank(message = "다시 로그인 해주세요.")
    private String author;          //작성자

    private int views;              //조회수

    private MultipartFile file;


    public NoticeBoard DtoToEntity() {
        return NoticeBoard.builder()
                .author(this.author)
                .content(this.content)
                .views(this.views)
                .title(this.title)
                .build();
    }

}
