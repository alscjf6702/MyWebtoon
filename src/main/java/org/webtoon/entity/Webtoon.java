package org.webtoon.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.webtoon.dto.WebtoonDTO;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity(name="webtoon")
public class Webtoon extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,length = 200)
    @NotBlank(message = "제목을 입력하세요.")
    private String title;

    @NotBlank(message = "작성자를 입력하세요.")
    private String author;

    private String fileName;

    private String filePath;

    @Column(columnDefinition = "integer default 0")
    private int views;

    @Column(nullable = true)
    private String content;


    public void change(WebtoonDTO dto){
        this.author = dto.getAuthor();
        this.content = dto.getContent();
        this.title = dto.getTitle();

    }

    public void setViews(int views) {
        this.views = views+1;
    }
}

