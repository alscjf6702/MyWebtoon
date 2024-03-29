package org.webtoon.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.webtoon.dto.MemberDTO;
import org.webtoon.entity.Member;
import org.webtoon.service.MemberService;

import javax.naming.Binding;
import java.util.Optional;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/regMember")
    public String getRegMember(MemberDTO dto) {
        return "/member/regMember";
    }

    @PostMapping("/regMember")
    public String regMember(@Valid MemberDTO dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/member/regMember";
        }

        Member getUserId = memberService.findByUserId(dto.getUserId());

        if (getUserId != null) {
            bindingResult.rejectValue("userId", "existId", "이미 사용 중인 ID입니다.");
            return "/member/regMember";
        }

        memberService.regMember(dto);


        return "redirect:/member/list";

    }

    @GetMapping("/login")
    public String login() {
        System.out.println("로그인 시도");
        return "/member/login_form";
    }


    @GetMapping("/list")
    public String memberList(Model model) {
        model.addAttribute("list", memberService.memberList());

        return "/member/list";
    }

    @PostMapping("/delete")
    public String deleteMember(@RequestParam("id") Long id, Model model) {
        memberService.deleteMember(id);
        model.addAttribute("message", id + "번 아이디가 삭제되었습니다.");
        return "redirect:/member/list";
    }

    @GetMapping("/update")
    public String getUpdateMember(Long id, Model model) {
        Member member = memberService.getMember(id);

        model.addAttribute("member", member);

        return "/member/detail";
    }

    @PostMapping("/update")
    public String updateMember(MemberDTO dto, Model model) {
        try {
            memberService.updateMember(dto);
        } catch (RuntimeException e) {
            model.addAttribute("message", "오류입니다.");
            return "redirect:/member/update/" + dto.getId();
        }


        return "redirect:/member/list";
    }

    @GetMapping("/detail")
    public String getMemberDetail(@RequestParam("id") Long id, Model model) {
        Member member = memberService.getMember(id);
        model.addAttribute("member", member);
        return "/member/detail";
    }

    @GetMapping("/memberFind")
    public String getMemberFind() {
        return "/member/memberFind";
    }


    @PostMapping("/memberFind")
    public String postMemberFind(@RequestParam("email") String email, @RequestParam("phoneNumber") String phoneNumber, Model model) {

        Member byEmail = memberService.findByEmail(email);
        Member byPhoneNumber = memberService.findByPhoneNumber(phoneNumber);

        if (byEmail == byPhoneNumber) {
            model.addAttribute("member", byEmail);
            return "/member/memberFound";
        } else {
            model.addAttribute("message", "해당하는 정보가 없습니다.");
            return "redirect:/member/memberFind";
        }
    }

    @PostMapping("/resetPassword")
    public String postResetPassword(@RequestParam("userId") String userId, @RequestParam("password") String password) {

        memberService.updateMemberByUserId(userId, password);

        return "/member/login_form";
    }

    @GetMapping("/myInfo")
    public String myInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        Member member = memberService.findByUserId(userId);
        model.addAttribute("member", member);

        return "/member/myInfo";
    }

    @PostMapping("/updateInfo")
    public String updateMyInfo(@Valid MemberDTO dto, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return "/member/myInfo";
        }

        memberService.updateMember(dto);

        return "redirect:/board/list";

    }
}

