package org.webtoon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webtoon.dto.GoodsDTO;
import org.webtoon.entity.Goods;
import org.webtoon.repository.GoodsRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;

    public List<Goods> list(){
        return goodsRepository.findAll();
    }

    public void insertProduct(GoodsDTO dto, MultipartFile file) throws IOException {

        if (!file.isEmpty()) {

            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

            UUID uuid = UUID.randomUUID();

            String originFileName = file.getOriginalFilename();

            String fileName = uuid + "_" + file.getOriginalFilename();
            File saveFile = new File(projectPath, fileName);

            file.transferTo(saveFile);

            Random rnd = new Random();

            int product_id = rnd.nextInt(90000) + 10000;
            while (product_id == dto.productIdCheck()) {
                product_id++;
            }

            Goods goods = Goods.builder()
                    .productId(product_id)
                    .price(dto.getPrice())
                    .seller(dto.getSeller())
                    .productName(dto.getProductName())
                    .productsLeft(dto.getProductsLeft())
                    .productInfo(dto.getProductInfo())
                    .fileName(fileName)
                    .filePath("/files/" + fileName)
                    .build();

            goodsRepository.save(goods);

        }
    }

    public Goods getDetail(Long id){
        Optional<Goods> getDetailById = goodsRepository.findById(id);

        return getDetailById.get();
    }

    public void deleteProduct(Long id) {
        Optional<Goods> byId = goodsRepository.findById(id);

        if (byId.isPresent()) {
            Goods goods = byId.get();
            goodsRepository.delete(goods);
        }

    }

    public void updateProduct(GoodsDTO dto){

        Goods existGoods = goodsRepository.findByProductId(dto.getProductId());

        if (existGoods != null) {
            existGoods.updateProduct(dto);
            goodsRepository.save(existGoods);
        }else{
            throw new RuntimeException("굿즈 정보가 없습니다.");
        }
    }


}

