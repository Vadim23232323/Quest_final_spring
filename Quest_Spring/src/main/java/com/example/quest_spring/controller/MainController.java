package com.example.quest_spring.controller;

import com.example.quest_spring.service.UserService;
import com.example.quest_spring.util.WebPaths;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@AllArgsConstructor
public class MainController {
    UserService userService ;
    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("user",userService.getAll());
        log.info("The home page is open: main");
        return WebPaths.MAIN;
    }
}
