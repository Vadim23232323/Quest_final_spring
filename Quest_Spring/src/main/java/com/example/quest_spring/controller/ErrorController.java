package com.example.quest_spring.controller;

import com.example.quest_spring.util.WebPaths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Slf4j
@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    private final ErrorAttributes errorAttributes;

    public ErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping(WebPaths.ERROR)
    public String handleError(Model model, WebRequest webRequest) {
        Map<String, Object> errors = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
        model.addAttribute("timestamp", errors.get("timestamp"));
        model.addAttribute("error", errors.get("error"));
        model.addAttribute("status", errors.get("status"));
        model.addAttribute("message", errors.get("message"));
        model.addAttribute("path", errors.get("path"));

        // Logging error information
        log.info("Error occurred: status={}, error={}, message={}, path={}, timestamp={}",
                errors.get("status"),
                errors.get("error"),
                errors.get("message"),
                errors.get("path"),
                errors.get("timestamp"));

        return WebPaths.WP_ERROR;
    }
}
