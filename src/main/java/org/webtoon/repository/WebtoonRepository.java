package org.webtoon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webtoon.entity.Webtoon;



public interface WebtoonRepository extends JpaRepository<Webtoon ,Long> {


}

