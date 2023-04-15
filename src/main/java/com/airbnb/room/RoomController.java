package com.airbnb.room;

import com.airbnb.amenity.AmenitiesRepository;
import com.airbnb.amenity.AmenitiesService;
import com.airbnb.amenity.dto.AmenitiesDto;
import com.airbnb.common.security.CurrentUser;
import com.airbnb.common.security.CustomUserDetails;
import com.airbnb.facility.FacilitiesRepository;
import com.airbnb.facility.FacilitiesService;
import com.airbnb.facility.dto.FacilitiesDto;
import com.airbnb.houseRule.HouseRuleRepository;
import com.airbnb.houseRule.HouseRuleService;
import com.airbnb.houseRule.dto.HouseRuleDto;
import com.airbnb.room.dto.RoomCreateRequestDto;
import com.airbnb.room.dto.RoomDetailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final AmenitiesRepository amenitiesRepository;
    private final FacilitiesRepository facilitiesRepository;
    private final HouseRuleRepository houseRuleRepository;
    private final AmenitiesService amenitiesService;
    private final FacilitiesService facilitiesService;
    private final HouseRuleService houseRuleService;

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
                             @RequestParam(value = "photos", required = false) MultipartFile[] photos,
                             @CurrentUser CustomUserDetails user, Model model) throws IOException {

        if (result.hasErrors()) {
            log.info("error={}", result);
            model.addAttribute("amenities", amenitiesRepository.findAll());
            model.addAttribute("facilities", facilitiesRepository.findAll());
            model.addAttribute("houseRules", houseRuleRepository.findAll());
            return "/room/room_create";
        }

        roomService.createRoom(dto, user.getEmail(), photos);

        return "redirect:/";
    }

    @GetMapping("/detail/{pk}")
    public String roomDetail(@PathVariable("pk") Long roomId, Model model) {
        RoomDetailDto roomDetailDto = roomService.roomDetail(roomId);
        List<AmenitiesDto> amenities = amenitiesService.findAmenities(roomId);
        List<FacilitiesDto> facilities = facilitiesService.findFacilities(roomId);
        List<HouseRuleDto> houseRules = houseRuleService.findHouseRules(roomId);

        model.addAttribute("roomDetail", roomDetailDto);
        model.addAttribute("amenities", amenities);
        model.addAttribute("facilities", facilities);
        model.addAttribute("houseRules", houseRules);

        return "/room/room_detail";
    }

    @GetMapping("/edit")
    public String roomEdit() {
        return "/room/room_edit";
    }
}
