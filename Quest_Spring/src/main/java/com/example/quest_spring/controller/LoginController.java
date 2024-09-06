package com.example.quest_spring.controller;

import com.example.quest_spring.util.WebPaths;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@AllArgsConstructor
public class LoginController {

    @GetMapping("/login")
    public String login(){
        log.info("User login page is open");
        return WebPaths.WP_LOGIN;
    }
}
