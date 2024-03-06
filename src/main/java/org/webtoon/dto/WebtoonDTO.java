package org.webtoon.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import org.webtoon.entity.Webtoon;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebtoonDTO {

    private Long id;

    @NotBlank(message = "제목을 입력하세요.")
    private String title;

    private String author;

    private String content;

    private int views;

    private MultipartFile file;


    public void dtoToEntity(WebtoonDTO dto) {
        Webtoon webtoon = Webtoon.builder()
                .title(this.title)
                .author(this.author)
                .content(this.content)
                .build();
    }


}


