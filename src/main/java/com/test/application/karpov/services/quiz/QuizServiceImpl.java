package com.test.application.karpov.services.quiz;

import com.test.application.karpov.exceptions.QuizNotFoundException;
import com.test.application.karpov.services.dto.Quiz;
import com.test.application.karpov.repositories.quiz.QuizRepository;
import com.test.application.karpov.repositories.user.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    public Quiz findQuizById(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException(id));
    }


    @Override
    public Quiz update(Quiz newQuiz, Long id) {
        return quizRepository.findById(id)
                .map(quiz -> {
                    quiz.setName(newQuiz.getName());
                    quiz.setDescription(newQuiz.getDescription());
                    quiz.setQuestions(newQuiz.getQuestions());
                    quiz.setStopDate(newQuiz.getStopDate());
                    quiz.setUsers(newQuiz.getUsers());
                    return save(quiz);
                })
                .orElseGet(() -> {
                    newQuiz.setId(id);
                    return save(newQuiz);
                });
    }

    @Override
    public Quiz save(Quiz newQuiz) {
        return quizRepository.save(newQuiz);
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
        quizRepository.deleteById(id);
    }
}

