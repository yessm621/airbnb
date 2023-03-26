package com.airbnb.member;

import com.airbnb.common.security.CurrentUser;
import com.airbnb.common.security.CustomUserDetails;
import com.airbnb.mail.MailService;
import com.airbnb.mail.dto.MailSendDto;
import com.airbnb.member.dto.FindPasswordDto;
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
    private final MailService mailService;

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

    @GetMapping("/email")
    public String mailSend(Model model) {
        model.addAttribute("dto", new MailSendDto());
        return "/member/send_mail";
    }

    @PostMapping("/email")
    public String mailSendComplete(@Validated @ModelAttribute("dto") MailSendDto dto, BindingResult result) {

        Member findMember = memberRepository.findByEmail(dto.getEmail());
        if (findMember == null) {
            result.reject("DoesNotExistEmail", "존재하지 않는 이메일입니다. 다시 확인해주세요.");
        }

        if (result.hasErrors()) {
            log.info("error={}", result);
            return "/member/send_mail";
        }
        String tempPassword = randomPassword();
        dto.setTitle("[Airbnb] 비밀번호 변경");
        String content = "안녕하세요. Airbnb 관리자입니다. 임시 비밀번호 발급하였습니다. \n" +
                "임시 비밀번호 = " + tempPassword + "\n" +
                "홈페이지로 돌아가 비밀번호 변경을 완료하시기 바랍니다.\n" +
                "http://localhost:8080/members/login";
        dto.setContent(content);
        mailService.sendEmail(dto);

        FindPasswordDto passwordDto = new FindPasswordDto();
        passwordDto.setEmail(dto.getEmail());
        passwordDto.setPassword(tempPassword);
        memberService.passwordModify(passwordDto);

        return "/member/send_mail_complete";
    }

    @GetMapping("/password")
    public String passwordModify(Model model) {
        model.addAttribute("dto", new FindPasswordDto());
        return "member/password_modify";
    }

    @PostMapping("/password")
    public String passwordModify(@CurrentUser CustomUserDetails user, @Validated @ModelAttribute("dto") FindPasswordDto dto, BindingResult result) {

        if (!dto.getPassword().equals(dto.getPasswordConfirm())) {
            result.reject("PasswordDoNotMatch", "비밀번호가 일치하지 않습니다.");
        }
        if (result.hasErrors()) {
            log.info("error={}", result);
            return "/member/password_modify";
        }

        dto.setEmail(user.getEmail());
        memberService.passwordModify(dto);

        return "redirect:/members/logout";
    }

    private String randomPassword() {
        char[] pwCharSet = new char[]{
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };
        int idx = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            idx = (int) (pwCharSet.length * Math.random());
            sb.append(pwCharSet[idx]);
        }
        return sb.toString();
    }
}
