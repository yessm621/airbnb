package com.airbnb.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chats")
public class ChatController {

    @GetMapping("/send")
    public String chatSend() {
        return "/chat/send";
    }
}
