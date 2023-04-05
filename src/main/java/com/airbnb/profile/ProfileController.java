package com.airbnb.profile;

import com.airbnb.common.s3.S3Uploader;
import com.airbnb.common.security.CurrentUser;
import com.airbnb.common.security.CustomUserDetails;
import com.airbnb.profile.dto.ProfileDetailDto;
import com.airbnb.profile.dto.ProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final S3Uploader s3Uploader;

    @GetMapping
    public String profile(@CurrentUser CustomUserDetails user, Model model) {
        ProfileDto dto = profileService.profileInfo(user.getEmail());
        model.addAttribute("dto", dto);
        return "profile/profile";
    }

    @GetMapping("/detail")
    public String profileDetail(@CurrentUser CustomUserDetails user, Model model) {
        ProfileDetailDto dto = profileService.profileDetail(user.getEmail());
        model.addAttribute("dto", dto);
        return "profile/profile_detail";
    }

    @PostMapping("/modify")
    public String profileModify(@Validated @ModelAttribute("dto") ProfileDetailDto dto, BindingResult result,
                                @RequestParam(value = "profile", required = false) MultipartFile profile,
                                @CurrentUser CustomUserDetails user, Model model) throws IOException {

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
            return "/profile/profile_detail";
        }

        profileService.save(dto, profile);

        return "redirect:/profile";
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(String fileUrl) throws IOException {
        String filePath = fileUrl.substring(52);
        return s3Uploader.download(filePath);
    }

    @GetMapping("/scrap")
    public String scrap() {
        return "profile/scrap";
    }
}
