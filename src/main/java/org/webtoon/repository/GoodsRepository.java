package org.webtoon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webtoon.entity.Goods;

public interface GoodsRepository  extends JpaRepository<Goods,Integer> {

    Goods findByProductId(int productId);


}

