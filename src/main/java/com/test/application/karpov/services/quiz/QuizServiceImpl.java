package com.test.application.karpov.services.quiz;

import com.test.application.karpov.dto.Quiz;
import com.test.application.karpov.dto.User;
import com.test.application.karpov.exceptions.QuizNotFoundException;
import com.test.application.karpov.repositories.quiz.QuizRepository;
import com.test.application.karpov.repositories.user.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Getter
@Setter
@Service
public class QuizServiceImpl implements QuizService {
    private static final long ANONYMOUS_USER_ID = 1;

    private static final Logger logger = LogManager.getLogger(QuizServiceImpl.class);
    private QuizRepository quizRepository;
    private UserRepository userRepository;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository, UserRepository userRepository) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    @Transactional
    public Quiz getQuizById(Long id) {
        checkId(id);
        return quizRepository.getById(id);
    }

    @Override
    public void saveOrUpdate(Quiz quiz) {
        quizRepository.save(quiz);
    }

    @Override
    public void saveOrUpdate(Quiz quiz, Boolean isAnonymous) {
//        TODO some logic to check quiz
        if(isAnonymous) {
            quiz.getUsers().add(getAnonymousUser());
        }
        quizRepository.save(quiz);
    }

    private User getAnonymousUser() {
        //TODO getting User from DB with id from application properties
        return userRepository.getById(ANONYMOUS_USER_ID);
    }


    @Override
    public List<Quiz> findAllActive() {
        //TODO make select from DB to be faster
        return findAll().stream()
                .filter(q -> q.getStopDate().after(new Date()) && q.getStartDate().before(new Date()))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        quizRepository.delete(quizRepository.getById(id));
    }

    private void checkId(Long id) {
        if (Objects.isNull(id)) {
            logger.info("Question ID should not be null");
            throw new QuizNotFoundException(id);
        }

    }

}
