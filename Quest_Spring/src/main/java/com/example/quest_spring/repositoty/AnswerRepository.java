package com.example.quest_spring.repositoty;

import com.example.quest_spring.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findByText(String text);
    List<Answer> findByQuestionId(Long questionId);
}