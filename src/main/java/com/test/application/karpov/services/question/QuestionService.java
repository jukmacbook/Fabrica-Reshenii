package com.test.application.karpov.services.question;

import com.test.application.karpov.services.dto.Question;

import java.util.List;
import java.util.Optional;


public interface QuestionService {

    List<Question> findAll();

    Question findQuestionById(Long id);

    Question save(Question newQuestion);

    Question update(Question newQuestion, Long id);

    void delete(Long id);

}
