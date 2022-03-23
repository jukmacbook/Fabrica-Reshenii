package com.test.application.karpov.controllers.user;


import com.test.application.karpov.dto.Quiz;
import com.test.application.karpov.services.quiz.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user/quiz")
public class QuizUserController {

    private final QuizServiceImpl quizService;

    @Autowired
    public QuizUserController(QuizServiceImpl quizService) {
        this.quizService = quizService;
    }

    @GetMapping(value = "/active", produces = "application/json")
    private List<Quiz> getAllActiveQuiz() {
        return quizService.findAllActive();
    }

    @PostMapping(value = "/{isAnonymous}")
    public void saveQuiz(@RequestBody Quiz quiz, @PathVariable Boolean isAnonymous) {
        quizService.saveOrUpdate(quiz, isAnonymous);
    }

}


