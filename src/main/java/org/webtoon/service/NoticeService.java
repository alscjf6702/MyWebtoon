package org.webtoon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.webtoon.entity.NoticeBoard;
import org.webtoon.repository.NoticeRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private NoticeBoard noticeBoard;
    private Pageable pageable;
    public Page<NoticeBoard> getList(Pageable pageable) {
//        return noticeRepository.findALLByOrderByIdDesc();
        return noticeRepository.findAll(pageable);
    }

    public Page<NoticeBoard> getSearchList(String searchKeyword, Pageable pageable) {
        return noticeRepository.findByTitleContaining(searchKeyword, pageable);
    }

    public NoticeBoard getDetail(Long id){
        Optional<NoticeBoard> noticeboard = noticeRepository.findById(id);
        if (noticeboard.isPresent()) {
            NoticeBoard noticeBoard1 = noticeboard.get();
            noticeBoard1.setViews(noticeBoard1.getViews());
            this.noticeRepository.save(noticeBoard1);
            return noticeBoard1;
        }
        return noticeboard.get();
    }

    public void modify(Long id, String content) {
        Optional<NoticeBoard> result = noticeRepository.findById(id);

        NoticeBoard noticeBoard = result.orElseThrow();

        noticeBoard.change(content);

        noticeRepository.save(noticeBoard);
    }

    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }

    public void insert(String title, String content, String author, MultipartFile file) throws IOException {

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        NoticeBoard noticeBoard = null;

        if (file != null) {
            noticeBoard = NoticeBoard.builder()
                    .fileName(fileName)
                    .filePath("/files/" + fileName)
                    .title(title)
                    .content(content)
                    .author(author)
                    .build();
        }else {
            noticeBoard = NoticeBoard.builder()
                    .title(title)
                    .content(content)
                    .author(author)
                    .build();
        }

        noticeRepository.save(noticeBoard);
    }


}
