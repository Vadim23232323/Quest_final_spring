package com.example.quest_spring.controller;

import com.example.quest_spring.entity.User;
import com.example.quest_spring.security.UserDetailsImpl;
import com.example.quest_spring.service.UserService;
import com.example.quest_spring.util.WebPaths;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@AllArgsConstructor
public class ProfileController {


    private final UserService userService;

    @GetMapping(WebPaths.PROFILE)
    public String viewProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.getUserById(((UserDetailsImpl) userDetails).getId());
        model.addAttribute("user", user);
        return WebPaths.WP_PROFILE;
    }

    @PostMapping(WebPaths.PROFILE)
    public String updateProfile(@AuthenticationPrincipal UserDetails userDetails,
                                @RequestParam String name,
                                @RequestParam String surname,
                                @RequestParam String email,
                                @RequestParam(required = false) String password,
                                Model model) {
        User user = userService.getUserById(((UserDetailsImpl) userDetails).getId());
        userService.updateProfile(user, name, surname, email, password);
        model.addAttribute("user", user);
        model.addAttribute("successMessage", String.format("%s profile updated successfully!", user.getLogin()));
        log.info("{} profile updated successfully!", user.getLogin());

        return WebPaths.WP_PROFILE;
    }
}
