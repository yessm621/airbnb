package com.airbnb.member;

import com.airbnb.member.dto.RegisterDto;
import com.airbnb.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
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

        Member findMember = memberRepository.findByEmail(dto.getEmail());
        if (findMember != null) {
            result.reject("ValidateDuplicateEmail", "이미 가입된 이메일입니다.");
        }

        if (result.hasErrors()) {
            log.info("error={}", result);
            return "/member/signup";
        }

        memberService.signup(dto);

        return "redirect:/member/login";
    }
}
