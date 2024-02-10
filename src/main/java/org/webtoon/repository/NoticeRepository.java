package org.webtoon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;
import org.webtoon.dto.NoticeDTO;
import org.webtoon.entity.NoticeBoard;

import java.util.List;


public interface NoticeRepository extends JpaRepository<NoticeBoard, Long> {

    @Query("update noticeBoard nb set nb.content = :content where nb.id = :id")
    void updateById(Long id, String content);

    @Modifying
    @Query("delete from noticeBoard nb where nb.id=:id")
    void deleteById(@Param("id") Long id);

    List<NoticeBoard> findALLByOrderByIdDesc();

    Page<NoticeBoard> findByTitleContaining(String searchKeyword, Pageable pageable);
}
