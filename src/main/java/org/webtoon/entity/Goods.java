package org.webtoon.entity;

import jakarta.persistence.*;
import lombok.*;
import org.webtoon.dto.GoodsDTO;

@Entity(name = "goods")
@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@ToString
public class Goods {

    @Id
    private int productId;     //상품번호

    private String productName; //상품 이름

    private int amount;     //상품 가격

//    @Column(unique = true)
//    private String merchant_uid;    //주문번호

    @Column(columnDefinition = "integer default 0")
    private int likes;      //좋아요

    @Column(columnDefinition = "integer default 0")
    private int productsLeft;       //상품 남은 개수

    public void increaseLikes() {
        likes++;
    }

    public void decreaseProductsLeft() {
        productsLeft--;
    }

    public void updateProduct(GoodsDTO dto){
        this.productName = dto.getProductName();
        this.amount = dto.getAmount();
//        this.merchant_uid = dto.getMerchant_uid();
        this.likes = dto.getLikes();
        this.productsLeft = dto.getProductsLeft();
    }




}

