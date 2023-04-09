package com.airbnb.room;

import com.airbnb.room.dto.RoomCreateDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @GetMapping("/create")
    public String room(Model model) {
        model.addAttribute("dto", new RoomCreateDto());
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
