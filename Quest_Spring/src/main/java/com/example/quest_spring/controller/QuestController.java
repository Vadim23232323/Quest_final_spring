package com.example.quest_spring.controller;

import com.example.quest_spring.entity.Quest;
import com.example.quest_spring.repositoty.QuestRepository;
import com.example.quest_spring.util.WebPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class QuestController {

    @Autowired
    private QuestRepository questRepository;

    @GetMapping(WebPaths.LIST_QUEST)
    public String listQuests(Model model) {
        List<Quest> quests = questRepository.findAll();
        model.addAttribute("quests", quests);
        return WebPaths.WP_LIST_QUEST;
    }
}
