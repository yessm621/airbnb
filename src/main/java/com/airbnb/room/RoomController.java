package com.airbnb.room;

import com.airbnb.room.dto.RoomCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final AmenitiesRepository amenitiesRepository;
    private final FacilitiesRepository facilitiesRepository;
    private final HouseRuleRepository houseRuleRepository;

    @GetMapping("/create")
    public String room(Model model) {
        model.addAttribute("dto", new RoomCreateRequestDto());
        model.addAttribute("amenities", amenitiesRepository.findAll());
        model.addAttribute("facilities", facilitiesRepository.findAll());
        model.addAttribute("houseRules", houseRuleRepository.findAll());
        return "/room/room_create";
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
