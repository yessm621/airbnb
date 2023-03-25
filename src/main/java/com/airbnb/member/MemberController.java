package com.airbnb.member;

import com.airbnb.member.dto.RegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("dto", new RegisterDto());
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@Validated @ModelAttribute("dto") RegisterDto dto, BindingResult result, Model model) {

        if (!dto.getPassword().equals(dto.getPasswordConfirm())) {
            result.reject("PasswordDoNotMatch", "비밀번호가 일치하지 않습니다.");
        }

        if (result.hasErrors()) {
            log.info("error={}", result);
            return "/member/signup";
        }

        memberService.signup(dto);

        return "member/login";
    }

    @GetMapping("/password")
    public String passwordModify() {
        return "member/password_modify";
    }

    @GetMapping("/profile")
    public String profile() {
        return "member/profile";
    }

    @GetMapping("/profile_detail")
    public String profileDetail() {
        return "member/profile_detail";
    }

    @GetMapping("/scrap")
    public String scrap() {
        return "member/scrap";
    }
}
