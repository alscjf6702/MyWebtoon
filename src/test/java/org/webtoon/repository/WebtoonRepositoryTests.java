package org.webtoon.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.webtoon.dto.WebtoonDTO;
import org.webtoon.entity.Webtoon;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class WebtoonRepositoryTests {

    @Autowired
    private WebtoonRepository webtoonRepository;

    @Test
    public void insertWebtoon(){

        for (int i = 0; i < 100; i++) {
            Webtoon webtoon = Webtoon.builder()
                    .title("제목"+i)
                    .content("내용"+i)
                    .author("작성자"+i)
                    .build();
            webtoonRepository.save(webtoon);
        }

    }

    @Test
    public void getList(){
        List<Webtoon> all = webtoonRepository.findAll();

        log.info(all);

    }

    @Test
    public void delete(){
        webtoonRepository.deleteById(3L);

    }

    @Test
    public void update(){
        Webtoon webtoon = Webtoon.builder()
                .id(2L)
                .content("aaaaa")
                .title("aaaa")
                .author("aaa")
                .build();

        webtoonRepository.save(webtoon);
    }

    @Test
    public void getDetail(){
        Optional<Webtoon> byId = webtoonRepository.findById(2L);
        log.info(byId);

    }
}