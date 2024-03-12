package org.webtoon.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.webtoon.entity.Goods;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDTO {


    private int productId;     //상품번호

    private String productName; //상품 이름

    private int amount;     //상품 가격

//    @Column(unique = true)
//    private String merchant_uid;    //주문번호

    private int likes;      //좋아요

    private int productsLeft;       //상품 남은 개수


    public int productIdCheck(){
        Goods goods = new Goods();
        return goods.getProductId();
    }


}

