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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private int productId;     //상품번호

    private String productName; //상품 이름

    private int price;     //상품 가격

//    @Column(unique = true)
//    private String merchant_uid;    //주문번호

    @Column(columnDefinition = "integer default 0")
    private int likes;      //좋아요

    @Column(columnDefinition = "integer default 0")
    private int productsLeft;       //상품 남은 개수

//    @ManyToOne
//    @Column(nullable = false)
//    @JoinColumn(name = "user_name")
    private String seller;          //판매자

    private String productInfo;      //상품설명

    private String fileName;

    private String filePath;

    public void increaseLikes() {
        likes++;
    }

    public void updateProduct(GoodsDTO dto){
        this.productName = dto.getProductName();
        this.price = dto.getPrice();
//        this.merchant_uid = dto.getMerchant_uid();
        this.productsLeft = dto.getProductsLeft();
        this.productInfo = dto.getProductInfo();
    }




}

