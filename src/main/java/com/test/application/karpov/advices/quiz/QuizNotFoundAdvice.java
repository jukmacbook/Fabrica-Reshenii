package com.test.application.karpov.advices.quiz;

import com.test.application.karpov.exceptions.quiz.QuizNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class QuizNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(QuizNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String quizNotFoundHandler(QuizNotFoundException qx){
        return qx.getMessage();
    }
}
