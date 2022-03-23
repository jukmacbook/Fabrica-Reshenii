package com.test.application.karpov.controllers.user;

import com.test.application.karpov.dto.Question;
import com.test.application.karpov.services.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/question")
public class QuestionUserController {

    private final QuestionService questionService;

    @Autowired
    public QuestionUserController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public void updateQuestion(@PathVariable Long id, @RequestBody Question question){
        questionService.saveOrUpdate(question);
    }

}
