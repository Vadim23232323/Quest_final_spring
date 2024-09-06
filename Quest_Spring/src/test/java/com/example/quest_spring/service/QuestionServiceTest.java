package com.example.quest_spring.service;

import com.example.quest_spring.entity.Quest;
import com.example.quest_spring.entity.Question;
import com.example.quest_spring.repositoty.AnswerRepository;
import com.example.quest_spring.repositoty.QuestRepository;
import com.example.quest_spring.repositoty.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    @MockBean
    private QuestionRepository questionRepository;

    @MockBean
    private AnswerRepository answerRepository;

    @MockBean
    private QuestRepository questRepository;

    @Test
    public void testGetCurrentQuestionAndQuest() {
        Quest quest = new Quest();
        quest.setStartQuestionId(1L);

        Question question = new Question();
        question.setId(1L);

        when(questRepository.findById(anyLong())).thenReturn(Optional.of(quest));
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));

        Question result = questionService.getCurrentQuestionAndQuest(1L, null);

        assertEquals(question.getId(), result.getId());
    }
}
