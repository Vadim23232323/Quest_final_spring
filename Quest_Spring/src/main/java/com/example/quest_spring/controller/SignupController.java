package com.example.quest_spring.controller;
import com.example.quest_spring.entity.User;
import com.example.quest_spring.service.UserService;
import com.example.quest_spring.util.WebPaths;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@AllArgsConstructor
public class SignupController {
    private final UserService userService ;
    @GetMapping(WebPaths.SIGNUP)
    public String signup(Model model){
        model.addAttribute("user", new User());
        return WebPaths.WP_SIGNUP;
    }
    @PostMapping(WebPaths.SIGNUP)
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Validation errors occurred.");
            log.info("validation errors occurred when registering user " + user.getLogin());
            return WebPaths.WP_SIGNUP;
        }

        if (userService.isLoginTaken(user.getLogin())) {
            model.addAttribute("errorMessage", String.format("User with login %s already exists.", user.getLogin()));
            log.info("Attempting to register a user with an existing login " + user.getLogin());
            return WebPaths.WP_SIGNUP;
        }

        userService.save(user);
        return "redirect:" + WebPaths.LOGIN ;
    }
}

