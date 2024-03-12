package org.webtoon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webtoon.dto.GoodsDTO;
import org.webtoon.entity.Goods;
import org.webtoon.repository.GoodsRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GoodsService {

    private GoodsRepository goodsRepository;

    public List<Goods> list(){
        return goodsRepository.findAll();
    }

    public void insertProduct(GoodsDTO dto){

        Random rnd = new Random();

        int product_id = rnd.nextInt(90000)+10000;
        while (product_id == dto.productIdCheck()) {
            product_id++;
        }

        Goods goods = Goods.builder()
                .productId(product_id)
                .amount(dto.getAmount())
                .productName(dto.getProductName())
                .productsLeft(dto.getProductsLeft())
                .build();

        goodsRepository.save(goods);

    }

    public Optional<Goods> getDetail(int id){
        return goodsRepository.findById(id);
    }

    public void deleteProduct(int id) {
        Optional<Goods> byId = goodsRepository.findById(id);

        if (byId.isPresent()) {
            Goods goods = byId.get();
            goodsRepository.delete(goods);
        }

    }

    public void updateProduct(GoodsDTO dto){

        Goods existGoods = goodsRepository.findByProductId(dto.getProductId());

        if (existGoods != null) {
            existGoods.updateProduct(dto);
            goodsRepository.save(existGoods);
        }else{
            throw new RuntimeException("굿즈 정보가 없습니다.");
        }
    }


}

