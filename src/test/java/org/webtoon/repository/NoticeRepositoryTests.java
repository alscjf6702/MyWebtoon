package org.webtoon.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.webtoon.dto.NoticeDTO;
import org.webtoon.entity.NoticeBoard;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Log4j2
class NoticeRepositoryTests {

    @Autowired
    private NoticeRepository noticeRepository;

    @Test
    public void testInsert(){

//        for (int i = 0; i < 130; i++) {
//
//            NoticeBoard noticeBoard = NoticeBoard.builder()
//                    .content("내용3"+i)
//                    .author("작성자452"+i)
//                    .title("제목22"+i)
//                    .build();
//
//            NoticeBoard result = noticeRepository.save(noticeBoard);
//            log.info(result.getId()+"번 글이 등록되었습니다.");
//        }

//        IntStream.range(1, 100).forEach(i->{
//            NoticeBoard board = NoticeBoard.builder()
//                    .content("내용" + i)
//                    .author("작성자" + i)
//                    .title("제목" + i)
//                    .build();
//            NoticeBoard result = noticeRepository.save(board);
//            log.info(result.getId()+"번 글이 등록되었다.");
//        });
    }

    @Test
    public void testSelectOne() {
        Optional<NoticeBoard> list = noticeRepository.findById(5L);

        log.info(list);
    }

    @Test
    public void testSelectAll(){

        Pageable pageable = PageRequest.of(1, 5, Sort.by("id").descending());

        Page<NoticeBoard> result = noticeRepository.findAll(pageable);

        List<NoticeBoard> content = result.getContent();

        content.forEach(log::info);

//        content.forEach(board -> log.info(board));

//        for (NoticeBoard list : result) {
//            log.info(list);
//        }

    }

    @Test
    public void testUpdate(){

        Optional<NoticeBoard> result = noticeRepository.findById(5L);

        NoticeBoard noticeBoard = result.orElseThrow();

        noticeBoard.change( "꿀꿀");

        noticeRepository.save(noticeBoard);
    }

    @Test
    public void testDelete(){

        Long id = 5L;
        noticeRepository.deleteById(id);
    }
}



