package com.test.application.karpov.controllers.admin;

import com.test.application.karpov.services.dto.Quiz;
import com.test.application.karpov.services.quiz.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin/quizzes")
public class QuizApiController {

    private final QuizService quizService;

    @Autowired
    public QuizApiController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public List<Quiz> all(){
        return quizService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Quiz one(@PathVariable Long id) {
        return quizService.findQuizById(id);
    }

    @PostMapping
    public Quiz save(@RequestBody Quiz quiz){
        return quizService.save(quiz);
    }


    @PutMapping(value = "/{id}")
    public Quiz replace(@PathVariable Long id, @RequestBody Quiz quiz){
        return quizService.update(quiz, id);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void delete(@PathVariable Long id){
        quizService.delete(id);
    }
}
