package org.webtoon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webtoon.dto.WebtoonDTO;
import org.webtoon.entity.NoticeBoard;
import org.webtoon.entity.Webtoon;
import org.webtoon.repository.WebtoonRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WebtoonService {

    private final WebtoonRepository webtoonRepository;


    public Page<Webtoon> getList(Pageable pageable){
        return webtoonRepository.findAll(pageable);
    }

    public void insertWebtoon(WebtoonDTO dto, MultipartFile file) throws IOException {

        if (!file.isEmpty()) {

            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

            UUID uuid = UUID.randomUUID();

            String originFileName = file.getOriginalFilename();

            String fileName = uuid + "_" + file.getOriginalFilename();
            File saveFile = new File(projectPath, fileName);

            file.transferTo(saveFile);

            Webtoon webtoon = Webtoon.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .author(dto.getAuthor())
                    .fileName(fileName)
                    .filePath("/files/" + fileName)
                    .build();

            webtoonRepository.save(webtoon);
        }else{
            Webtoon webtoon = Webtoon.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .author(dto.getAuthor())
                    .build();
            webtoonRepository.save(webtoon);
        }
    }

    public Webtoon getDetail(Long id){
        Optional<Webtoon> detail = webtoonRepository.findById(id);

        if (detail.isPresent()) {
            Webtoon webtoonDetail = detail.get();
            webtoonDetail.setViews(webtoonDetail.getViews());
            webtoonRepository.save(webtoonDetail);
            return webtoonDetail;
        }
        return detail.get();
    }

    public void update(WebtoonDTO dto) {

        Optional<Webtoon> byId = webtoonRepository.findById(dto.getId());

        if (byId.isPresent()) {
            Webtoon webtoon = byId.get();
            webtoon.change(dto);
            webtoonRepository.save(webtoon);
        }

    }


    public void delete(Long id) {

        webtoonRepository.deleteById(id);

    }
}

