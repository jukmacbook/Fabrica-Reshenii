package com.test.application.karpov.controllers.admin;

import com.test.application.karpov.services.dto.Question;
import com.test.application.karpov.services.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/questions")
public class QuestionApiController {

    private final QuestionService questionService;

    @Autowired
    public QuestionApiController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(produces = "application/json")
    public List<Question> all(){
        return questionService.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Question one(@PathVariable Long id) {
        return questionService.findQuestionById(id);
    }

    @PostMapping
    public Question save(@RequestBody Question question){
        return questionService.save(question);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public Question replace(@PathVariable Long id, @RequestBody Question question){
        return questionService.update(question, id);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void delete(@PathVariable Long id){
        questionService.delete(id);
    }
}
