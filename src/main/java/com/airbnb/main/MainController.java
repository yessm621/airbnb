package com.airbnb.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main() {
        return "index";
    }


    @GetMapping("/404")
    public String error() {
        return "base/404";
    }
}
