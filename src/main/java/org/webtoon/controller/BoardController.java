package org.webtoon.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.webtoon.dto.NoticeDTO;
import org.webtoon.entity.Member;
import org.webtoon.entity.NoticeBoard;
import org.webtoon.service.MemberService;
import org.webtoon.service.NoticeService;

import java.io.IOException;
import java.security.Principal;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final NoticeService noticeService;

    private final MemberService memberService;

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
    public String getDetail(Model model, NoticeDTO dto) {

        model.addAttribute("board", noticeService.getDetail(dto.getId()));

        return "noticeBoard/noticeDetail";
    }

    @GetMapping("/modify")
    public String getModify(Model model, NoticeDTO dto) {
        model.addAttribute("board", noticeService.getDetail(dto.getId()));
        return "noticeBoard/noticeModify";
    }

    @PostMapping("/modify")
    public String postModify(NoticeDTO dto,Model model) {
        noticeService.modify(dto.getId(), dto.getContent());
        model.addAttribute("message", dto.getId()+"번 글 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/detail?id=" + dto.getId());
        return "noticeBoard/message";
    }


    @PostMapping("/delete")
    public String postDelete(NoticeDTO dto, Model model) {
        noticeService.delete(dto.getId());
        model.addAttribute("searchUrl", "/board/list");
        model.addAttribute("message", dto.getId() + "번 글이 삭제되었습니다.");
        return "noticeBoard/message";
    }

    @GetMapping("/insert")
    public String getInsert(NoticeDTO dto, Principal principal){

        return "noticeBoard/noticeInsert";
    }

    @PostMapping("/insert")
    public String postInsert(Model model,@Valid NoticeDTO dto, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            return "noticeBoard/noticeInsert";
        }


        noticeService.insert(dto.getTitle(), dto.getContent(), dto.getAuthor() , dto.getFile());

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "noticeBoard/message";
    }


}
