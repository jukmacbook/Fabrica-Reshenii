package com.test.application.karpov.services.question;

import com.test.application.karpov.exceptions.question.QuestionNotFoundException;
import com.test.application.karpov.dto.Question;
import com.test.application.karpov.repositories.question.QuestionRepository;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Getter
@Setter
@Service
public class QuestionServiceImpl implements QuestionService {

    private static final Logger logger = LogManager.getLogger(QuestionServiceImpl.class);
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question findQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));
    }

    @Override
    public Question save(Question newQuestion) {
        return questionRepository.save(newQuestion);
    }

    @Override
    public Question update(Question newQuestion, Long id) {
        return questionRepository.findById(id)
                .map(question -> {
                    question.setQuiz(newQuestion.getQuiz());
                    question.setAnswers(newQuestion.getAnswers());
                    question.setQuestionType(newQuestion.getQuestionType());
                    question.setQuestionText(newQuestion.getQuestionText());
                    return save(question);
                })
                .orElseGet(() -> {
                    newQuestion.setId(id);
                    return save(newQuestion);
                });
    }

    @Override
    public void delete(Long id) {
        questionRepository.delete(questionRepository.getById(id));
    }


}
