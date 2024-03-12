package org.webtoon.controller;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.webtoon.service.GoodsService;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class GoodsController {

    private final IamportClient iamportClient;

    private GoodsService goodsService;

    public GoodsController(){
        this.iamportClient = new IamportClient("",
                "");
    }

    @GetMapping("/goods/list")
    public String list(Model model){
        model.addAttribute("goods", goodsService.list());
        return "/goods/list";
    }


    @GetMapping("/iamport")
    public String iamport(){
        return "/iamport";
    }


    @ResponseBody
    @RequestMapping("/verify/{imp_uid}")
    public IamportResponse<Payment> paymentByImpUid(@PathVariable("imp_uid") String imp_uid)
            throws IamportResponseException, IOException {
        return iamportClient.paymentByImpUid(imp_uid);
    }




}

