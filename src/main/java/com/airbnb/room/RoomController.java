package com.airbnb.room;

import com.airbnb.common.security.CurrentUser;
import com.airbnb.common.security.CustomUserDetails;
import com.airbnb.room.dto.RoomCreateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
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
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final AmenitiesRepository amenitiesRepository;
    private final FacilitiesRepository facilitiesRepository;
    private final HouseRuleRepository houseRuleRepository;

    @Secured({"ROLE_ADMIN", "ROLE_HOST"})
    @GetMapping("/create")
    public String room(Model model) {
        model.addAttribute("dto", new RoomCreateRequestDto());
        model.addAttribute("amenities", amenitiesRepository.findAll());
        model.addAttribute("facilities", facilitiesRepository.findAll());
        model.addAttribute("houseRules", houseRuleRepository.findAll());
        return "/room/room_create";
    }

    @Secured({"ROLE_ADMIN", "ROLE_HOST"})
    @PostMapping("/create")
    public String createRoom(@Validated @ModelAttribute("dto") RoomCreateRequestDto dto, BindingResult result,
                             @CurrentUser CustomUserDetails user) {

        if (result.hasErrors()) {
            log.info("error={}", result);
            return "/room/room_create";
        }

        roomService.createRoom(dto, user.getEmail());

        return "redirect:/";
    }

    @GetMapping("/detail")
    public String roomDetail() {
        return "/room/room_detail";
    }

    @GetMapping("/edit")
    public String roomEdit() {
        return "/room/room_edit";
    }
}
