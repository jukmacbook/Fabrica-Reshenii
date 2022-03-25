package com.test.application.karpov.services.quiz;

import com.test.application.karpov.dto.Quiz;

import java.util.List;


public interface QuizService {

    List<Quiz> findAll();

    List<Quiz> findAllActive();

    Quiz findQuizById(Long id);

    Quiz update(Quiz newQuiz, Long id);

    Quiz save(Quiz newQuiz);

    void delete(Long id);

}
