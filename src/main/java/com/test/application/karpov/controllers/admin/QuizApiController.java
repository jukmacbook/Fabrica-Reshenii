package com.test.application.karpov.controllers.admin;

import com.test.application.karpov.dto.Quiz;
import com.test.application.karpov.services.quiz.QuizService;
import com.test.application.karpov.services.quiz.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/quiz")
public class QuizApiController {

    private final QuizService quizService;

    @Autowired
    public QuizApiController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping(value = "/")
    public List<Quiz> findAllQuestions(){
        return quizService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Quiz getQuizById(@PathVariable Long id) {
        return quizService.getQuizById(id);
    }

    @PostMapping
    public void saveQuiz(@RequestBody Quiz quiz){
        quizService.saveOrUpdate(quiz);
    }


    @PutMapping(value = "/{id}")
    public void updateQuiz(@PathVariable Long id, @RequestBody Quiz quiz){
        quizService.saveOrUpdate(quiz);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void deleteQuiz(@PathVariable Long id){
        quizService.delete(id);
    }
}
