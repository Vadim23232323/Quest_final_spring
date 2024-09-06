package com.example.quest_spring.service;

import com.example.quest_spring.entity.Answer;
import com.example.quest_spring.entity.Quest;
import com.example.quest_spring.entity.Question;
import com.example.quest_spring.repositoty.AnswerRepository;
import com.example.quest_spring.repositoty.QuestRepository;
import com.example.quest_spring.repositoty.QuestionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestRepository questRepository;

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("Question not found");
                    return new IllegalArgumentException("Question not found");
                });

    }

    public List<Answer> getAnswersForQuestion(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    public Quest getQuestById(Long questId) {
        return questRepository.findById(questId)
                .orElseThrow(() -> {
                    log.info("Quest not found");
                    return new IllegalArgumentException("Quest not found");
                });
    }

    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }

    public boolean isAnswerCorrect(Long answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> {
                    log.info("No answer found");
                    return new IllegalArgumentException("No answer found");
                });
        return answer.getNextQuestionId() != null;
    }

    public Optional<Answer> getAnswerById(Long answerId) {
        return answerRepository.findById(answerId);
    }

    public Question getCurrentQuestionAndQuest(Long questId, Long answerId) {
        Quest quest = getQuestById(questId);
        if (answerId != null && isAnswerCorrect(answerId)) {
            Answer previousAnswer = getAnswerById(answerId)
                    .orElseThrow(() -> {
                        log.info("Answer not found with id: " + answerId);
                        return new IllegalArgumentException("Answer not found with id: " + answerId);
                    });
            return getQuestionById(previousAnswer.getNextQuestionId());
        } else {
            return getQuestionById(quest.getStartQuestionId());
        }
    }
}

