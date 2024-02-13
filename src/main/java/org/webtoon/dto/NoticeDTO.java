package org.webtoon.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import org.webtoon.entity.NoticeBoard;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoticeDTO {

    private Long id;
    private String title;           //제목

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;         //내용
    private LocalDateTime regDate;  //등록일
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
