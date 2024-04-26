package org.webtoon.controller;

import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.webtoon.dto.WebtoonDTO;
import org.webtoon.entity.NoticeBoard;
import org.webtoon.entity.Webtoon;
import org.webtoon.service.WebtoonService;

import java.io.IOException;


@Controller
@RequiredArgsConstructor
@RequestMapping("/webtoon")
public class WebtoonController {

    private final WebtoonService webtoonService;

    @GetMapping("/list")
    public String getList(Model model,
                          @PageableDefault(page = 0, size = 9, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//        model.addAttribute("webtoon", webtoonService.getList(pageable));
        Page<Webtoon> list = null;
        list = webtoonService.getList(pageable);
        int currentPage = list.getPageable().getPageNumber() + 1;      //0부터 시작이라 + 1 해줌
        int startPage = Math.max(currentPage - 4, 1);
        int endPage = Math.min(currentPage + 5, list.getTotalPages());     //Math.min , Math.max (숫자, 나올수 있는 최소값, 나올 수 있는 최대값)

        model.addAttribute("webtoon", list);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/webtoon/list";
    }

    @GetMapping("/detail")
    public String getDetail(Model model, @RequestParam("id") Long id) {
        model.addAttribute("webtoon", webtoonService.getDetail(id));

        return "/webtoon/detail";
    }

    @PostMapping("/insert")
    public String insert(WebtoonDTO dto, @RequestParam("file") MultipartFile file) throws IOException {

        webtoonService.insertWebtoon(dto, file);

        return "redirect:/webtoon/list";
    }

    @GetMapping("/insert")
    public String insert() {
        return "/webtoon/insert";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        webtoonService.delete(id);
        return "redirect:/webtoon/list";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") Long id, Model model) {
        model.addAttribute("webtoon", webtoonService.getDetail(id));
        return "/webtoon/update";
    }

    @PostMapping("/update")
    public String updatePost(WebtoonDTO dto, @RequestParam("id") Long id) {
        webtoonService.update(dto);
        return "redirect:/webtoon/detail?id=" + id;
    }
}

