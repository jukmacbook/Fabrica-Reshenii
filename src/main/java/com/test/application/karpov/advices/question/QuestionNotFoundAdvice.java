package com.test.application.karpov.advices.question;

import com.test.application.karpov.exceptions.question.QuestionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class QuestionNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(QuestionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String quizNotFoundHandler(QuestionNotFoundException ex){
        return ex.getMessage();
    }
}
