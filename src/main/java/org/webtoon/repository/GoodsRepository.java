package org.webtoon.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.webtoon.dto.GoodsDTO;
import org.webtoon.entity.Goods;

public interface GoodsRepository  extends JpaRepository<Goods,Long> {

    @Query("select g from goods g where g.productId=:productId")
    Goods findByProductId(@Param("productId") int productId);


//    @Query("update goods set productName=:productName, price=:price , productInfo=:productInfo , productsLeft=:productsLeft where id=:id")
//    @Modifying
//    @Transactional
//    void updateGoodsById(@Param("id") Long id, GoodsDTO dto);

}

