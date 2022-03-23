package com.test.application.karpov.services.quiz;

import com.test.application.karpov.dto.Question;
import com.test.application.karpov.dto.Quiz;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface QuizService {

    List<Quiz> findAll();

    void saveOrUpdate(Quiz quiz, Boolean isAnonymous);

    List<Quiz> findAllActive();

    Quiz getQuizById(Long id);

    void saveOrUpdate(Quiz quiz);

    void delete(Long id);

}
