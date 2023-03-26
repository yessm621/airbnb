package com.airbnb.profile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("profile")
public class ProfileController {

    @GetMapping
    public String profile() {
        return "profile/profile";
    }

    @GetMapping("/detail")
    public String profileDetail() {
        return "profile/profile_detail";
    }

    @GetMapping("/scrap")
    public String scrap() {
        return "profile/scrap";
    }
}
