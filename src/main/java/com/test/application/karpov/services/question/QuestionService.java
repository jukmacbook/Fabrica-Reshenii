package com.test.application.karpov.services.question;

import com.test.application.karpov.dto.Question;

import java.util.List;


public interface QuestionService {

    List<Question> findAll();

    Question getQuestionById(Long id);

    void saveOrUpdate(Question question);

    void delete(Long id);

}
