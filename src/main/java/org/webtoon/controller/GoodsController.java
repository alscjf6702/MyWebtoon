package org.webtoon.controller;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.webtoon.dto.GoodsDTO;
import org.webtoon.service.GoodsService;

import java.io.IOException;

@Controller
public class GoodsController {

    private final IamportClient iamportClient;

    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
        this.iamportClient = new IamportClient("2522366255172456",
                "mQVzAT929a7ucZd6PJvKdKtkDPE4CNHHjxkYzxP6iXB2miX7Sc2ZWFLdWFTV61Ne39zZxCUnyiYz9Bfw");
    }

    @GetMapping("/goods/list")
    public String list(Model model) {
        model.addAttribute("goods", goodsService.list());
        return "/goods/list";
    }

    @GetMapping("/goods/detail")
    public String detail(Model model, GoodsDTO dto) {
        model.addAttribute("detail", goodsService.getDetail(dto.getId()));
        return "/goods/detail";
    }

    @GetMapping("/goods/productSell")
    public String regSellProduct() {
        return "/goods/productSell";
    }

    @PostMapping("/goods/regProduct")
    public String regSellProductPost(GoodsDTO dto, @RequestParam("file") MultipartFile file) throws IOException {
        goodsService.insertProduct(dto, file);
        return "redirect:/goods/list";
    }


    @GetMapping("/iamport")
    public String iamport() {
        return "/iamport";
    }


    @ResponseBody
    @RequestMapping("/verify/{imp_uid}")
    public IamportResponse<Payment> paymentByImpUid(@PathVariable("imp_uid") String imp_uid)
            throws IamportResponseException, IOException {
        return iamportClient.paymentByImpUid(imp_uid);
    }


    @GetMapping("/goods/productUpdate")
    public String productUpdate(@RequestParam("id") Long id , Model model){
        model.addAttribute("detail", goodsService.getDetail(id));
        return "/goods/productUpdate";
    }

    @PostMapping("/goods/productUpdate")
    public String productUpdatePost(GoodsDTO dto){

        goodsService.updateProduct(dto);

        return "redirect:/goods/list";
    }

    @PostMapping("/goods/delete")
    public String deleteProduct(@RequestParam("id") Long id){
        goodsService.deleteProduct(id);
        return "redirect:/goods/list";
    }
}

