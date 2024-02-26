package org.webtoon.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
@Entity(name = "noticeBoard")
public class NoticeBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "제목을 입력해주세요.")
    @Column(name = "title", nullable = false,length = 200)
    private String title;       //제목

    @NotBlank(message = "내용을 입력해주세요.")
    @Column(nullable = false, name = "content" ,length = 2000)
    private String content;     //내용

    @NotBlank(message = "작성자를 입력해주세요.")
    @Column(name = "author", nullable = true, length = 50)
    private String author;      //작성자

    @Column(name = "views" ,nullable = false, columnDefinition = "integer default 0")
    private int views;          //조회수

    private String fileName;


    private String filePath;

    public void change( String content) {
        this.content = content;
    }


    public void setViews(int views) {
        this.views = views+1;
    }

}
