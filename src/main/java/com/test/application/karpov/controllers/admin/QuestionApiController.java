package com.test.application.karpov.controllers.admin;

import com.test.application.karpov.dto.Question;
import com.test.application.karpov.services.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionApiController {

    private final QuestionService questionService;

    @Autowired
    public QuestionApiController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(produces = "application/json")
    public List<Question> findAllQuestions(){
        return questionService.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Question getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id);
    }

    @PostMapping
    public void saveQuestion(@RequestBody Question question){
        questionService.saveOrUpdate(question);
    }


    @PutMapping(value = "/{id}", produces = "application/json")
    public void updateQuestion(@PathVariable Long id, @RequestBody Question question){
        questionService.saveOrUpdate(question);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void deleteQuestion(@PathVariable Long id){
        questionService.delete(id);
    }
}
