package com.test.application.karpov.services.question;

import com.test.application.karpov.dto.Question;
import com.test.application.karpov.exceptions.QuestionNotFoundException;
import com.test.application.karpov.repositories.question.QuestionRepository;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.ofNullable;


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
    @Transactional
    public Question getQuestionById(Long id) {
        checkId(id);
        return questionRepository.getById(id);
    }

    @Override
    public void saveOrUpdate(Question question) {
//        TODO some logic to check quiz
        questionRepository.save(question);
    }

    @Override
    public void delete(Long id) {
        questionRepository.delete(questionRepository.getById(id));
    }

    private void checkId(Long id) {
        if (Objects.isNull(id)) {
            logger.info("Question ID should not be null");
            throw new QuestionNotFoundException(id);
        }

    }


}
