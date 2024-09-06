package com.example.quest_spring.controller;

import com.example.quest_spring.entity.Answer;
import com.example.quest_spring.entity.Question;
import com.example.quest_spring.service.QuestionService;
import com.example.quest_spring.util.WebPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(WebPaths.QUESTION)
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public String showQuestion(@RequestParam("questId") Long questId,
                               @RequestParam(value = "answerId", required = false) Long answerId,
                               Model model) {
        Question currentQuestion = questionService.getCurrentQuestionAndQuest(questId, answerId);

        List<Answer> answers = questionService.getAnswersForQuestion(currentQuestion.getId());

        model.addAttribute("quests", questionService.getAllQuests());
        model.addAttribute("currentQuestion", currentQuestion);
        model.addAttribute("filteredAnswers", answers);
        model.addAttribute("showResults", currentQuestion.isWin() || currentQuestion.isWasted());

        return WebPaths.WP_QUESTION;
    }
}

