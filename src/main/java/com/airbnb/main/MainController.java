package com.airbnb.main;

import com.airbnb.room.RoomService;
import com.airbnb.room.dto.RoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final RoomService roomService;

    @GetMapping("/")
    public String main(@PageableDefault(size = 12, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        Page<RoomDto> roomList = roomService.roomList(pageable);
        model.addAttribute("roomList", roomList);

        return "index";
    }
}
