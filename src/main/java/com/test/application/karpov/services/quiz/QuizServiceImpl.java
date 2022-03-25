package com.test.application.karpov.services.quiz;

import com.test.application.karpov.exceptions.quiz.QuizNotFoundException;
import com.test.application.karpov.dto.Quiz;
import com.test.application.karpov.repositories.quiz.QuizRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@Service
public class QuizServiceImpl implements QuizService {

    private QuizRepository quizRepository;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
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
        return findAll().stream()
                .filter(q -> q.getStopDate().after(new Date()) && q.getStartDate().before(new Date()))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        quizRepository.deleteById(id);
    }
}

