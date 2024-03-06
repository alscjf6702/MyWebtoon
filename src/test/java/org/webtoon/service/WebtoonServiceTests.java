package org.webtoon.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.webtoon.dto.WebtoonDTO;
import org.webtoon.entity.Webtoon;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@Log4j2
class WebtoonServiceTests {

    @Autowired
    private WebtoonService webtoonService;


//    @Test
//    public void getList(){
//        List<Webtoon> list = webtoonService.getList();
//        list.forEach(log::info);
//    }

    @Test
    public void getDetail(){
        Webtoon detail = webtoonService.getDetail(4L);
        log.info(detail);
    }

    @Test
    public void update(){

        WebtoonDTO dto = new WebtoonDTO();
        dto.setTitle("하이");
        dto.setContent("하이");
        dto.setAuthor("하이");
        webtoonService.update(dto);


    }

}