package org.webtoon.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.webtoon.dto.NoticeDTO;
import org.webtoon.entity.NoticeBoard;

import java.time.LocalDateTime;


@SpringBootTest

class NoticeServiceTests {

    @Autowired
    private NoticeService noticeService;

    @Test
    public void insertNotice(){
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setTitle("호박고구마2");
        noticeDTO.setContent("잘 팔려요2");
        noticeDTO.setAuthor("관리자13");
        noticeDTO.setRegDate(LocalDateTime.now());

        NoticeBoard noticeBoard = noticeDTO.DtoToEntity();
//        noticeService.insert(noticeBoard.getTitle(),noticeBoard.getContent(), noticeBoard.getAuthor());
    }



}