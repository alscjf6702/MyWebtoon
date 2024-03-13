package org.webtoon.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.webtoon.dto.GoodsDTO;
import org.webtoon.entity.Goods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class GoodsServiceTests {

    @Autowired
    private GoodsService goodsService;

    @Test
    public void insert() throws IOException {


        GoodsDTO dto = new GoodsDTO();

        dto.setPrice(500000);
        dto.setProductName("도둑고양이");
        dto.setProductsLeft(33);


        MultipartFile file = new MockMultipartFile("test.jpg", new FileInputStream(new File("path/to/file")));

        goodsService.insertProduct(dto, file);

//
//        Goods goods = Goods.builder()
//                .productsLeft(30)
//                .productName("도둑고양이")
//                .amount(5000000)
//                .productId(product_id)
//                .build();



    }


}