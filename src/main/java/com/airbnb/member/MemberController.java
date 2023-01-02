package com.airbnb.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class MemberController {

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "member/signup";
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
}
