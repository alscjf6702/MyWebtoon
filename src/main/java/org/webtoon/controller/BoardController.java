package org.webtoon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.webtoon.entity.NoticeBoard;
import org.webtoon.service.NoticeService;

import java.io.IOException;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final NoticeService noticeService;

    @GetMapping("/list")
    public String list(Model model,
                       @PageableDefault(page = 0,size = 10,sort = "id",direction = Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(required = false, value = "searchKeyword") String searchKeyword) {

        Page<NoticeBoard> list = null;

        if (searchKeyword == null) {
            list = noticeService.getList(pageable);
        }else {
            list = noticeService.getSearchList(searchKeyword, pageable);
        }

        int currentPage= list.getPageable().getPageNumber()+1;      //0부터 시작이라 + 1 해줌
        int startPage = Math.max(currentPage - 4 , 1);
        int endPage = Math.min(currentPage + 5 , list.getTotalPages());     //Math.min , Math.max (숫자, 나올수 있는 최소값, 나올 수 있는 최대값)

        model.addAttribute("list", list);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "noticeBoard/noticeList";
    }


    @GetMapping("/detail")
    public String getDetail(Model model, @RequestParam("id") Long id) {

        model.addAttribute("board", noticeService.getDetail(id));

        return "noticeBoard/noticeDetail";
    }

    @GetMapping("/modify")
    public String getModify(Model model, @RequestParam("id") Long id ) {
        model.addAttribute("board", noticeService.getDetail(id));
        return "noticeBoard/noticeModify";
    }

    @PostMapping("/modify")
    public String postModify(@RequestParam("id") Long id, @RequestParam("content") String content,Model model) {
        noticeService.modify(id, content);
        model.addAttribute("message", id+"번 글 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/detail?id=" + id);
        return "noticeBoard/message";
    }

    @PostMapping("/delete")
    public String postDelete(@RequestParam("id") Long id, Model model) {
        noticeService.delete(id);
        model.addAttribute("searchUrl", "/board/list");
        model.addAttribute("message", id + "번 글이 삭제되었습니다.");
        return "noticeBoard/message";
    }

    @GetMapping("/insert")
    public String getInsert(){

        return "noticeBoard/noticeInsert";
    }

    @PostMapping("/insert")
    public String postInsert(@RequestParam("title") String title, @RequestParam("content") String content,
                             @RequestParam("author") String author, @RequestParam(required = false,value = "file") MultipartFile file , Model model) throws IOException {

        noticeService.insert(title, content, author , file);
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "noticeBoard/message";
    }

}
