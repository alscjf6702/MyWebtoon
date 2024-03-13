package org.webtoon.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.webtoon.dto.GoodsDTO;
import org.webtoon.entity.Goods;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class GoodsRepositoryTests {

    @Autowired
    private GoodsRepository goodsRepository;

    @Test
    public void insert(){

        Random rnd = new Random();

        int product_id = rnd.nextInt(90000)+10000;

        Goods goods = Goods.builder()
                .productsLeft(30)
                .productName("도둑고양이")
                .seller("awdawd")
                .price(5000000)
                .productId(product_id)
                .build();

//        GoodsDTO dto = new GoodsDTO();
//        dto.setAmount(500000);
//        dto.setProductName("도둑고양이");
//        dto.setProductsLeft(33);

        goodsRepository.save(goods);

    }


}