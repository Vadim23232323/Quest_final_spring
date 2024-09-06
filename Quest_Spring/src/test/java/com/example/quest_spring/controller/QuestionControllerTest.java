package com.example.quest_spring.controller;

import com.example.quest_spring.entity.Question;
import com.example.quest_spring.entity.Quest;
import com.example.quest_spring.service.QuestionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @Test
    @WithMockUser(username = "Vadim232323", roles = {"USER"})
    public void testGetQuestion() throws Exception {
        Long questId = 1L;

        // Создаем экземпляр Quest
        Quest quest = new Quest();
        quest.setId(questId);
        quest.setName("Sample Quest");

        // Создаем экземпляр Question и устанавливаем Quest
        Question question = new Question();
        question.setId(questId);
        question.setQuest(quest);

        when(questionService.getQuestionById(questId)).thenReturn(question);

        mockMvc.perform(get("/question").param("questId", questId.toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("questionView")) // Замените на фактическое имя представления
                .andExpect(model().attribute("question", question));
    }
}
