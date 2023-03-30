package com.airbnb.profile;

import com.airbnb.common.security.CurrentUser;
import com.airbnb.common.security.CustomUserDetails;
import com.airbnb.profile.dto.ProfileDto;
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
@RequestMapping("profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public String profile() {
        return "profile/profile";
    }

    @GetMapping("/detail")
    public String profileDetail(@CurrentUser CustomUserDetails user, Model model) {
        ProfileDto dto = profileService.getProfileDetail(user.getEmail());
        model.addAttribute("dto", dto);
        return "profile/profile_detail";
    }

    @PostMapping("/modify")
    public String profileModify(@Validated @ModelAttribute("dto") ProfileDto dto, BindingResult result,
                                @CurrentUser CustomUserDetails user) {

        if (!dto.getEmail().equals(user.getEmail())) {
            result.reject("DoHaveNotPermission", "권한이 없습니다.");
        }

        if (dto.getEmail() == null || dto.getEmail().equals("")) {
            result.reject("ThisValueIsRequired", "해당 값은 필수 값입니다.");
        }

        if (dto.getName() == null || dto.getName().equals("")) {
            result.reject("ThisValueIsRequired", "해당 값은 필수 값입니다.");
        }

        if (result.hasErrors()) {
            log.info("error={}", result);
            return "/profile/profile";
        }

        profileService.save(dto);

        return "redirect:/profile/detail";
    }

    @GetMapping("/scrap")
    public String scrap() {
        return "profile/scrap";
    }
}
