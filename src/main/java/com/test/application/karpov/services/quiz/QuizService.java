package com.test.application.karpov.services.quiz;

import com.test.application.karpov.services.dto.Quiz;

import java.util.List;
import java.util.Optional;


public interface QuizService {

    List<Quiz> findAll();

    List<Quiz> findAllActive();

    Quiz findQuizById(Long id);

    Quiz update(Quiz newQuiz, Long id);

    Quiz save(Quiz newQuiz);

    void delete(Long id);

}
