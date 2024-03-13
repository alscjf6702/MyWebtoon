package org.webtoon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.webtoon.entity.Goods;

public interface GoodsRepository  extends JpaRepository<Goods,Long> {

//    @Query("select g from goods g where g.productId=:productId")
    Goods findByProductId(int productId);


}

