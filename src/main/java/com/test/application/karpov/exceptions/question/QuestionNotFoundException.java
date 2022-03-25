package com.test.application.karpov.exceptions.question;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(Long id) {
        super("Could not find question " + id);
    }
}
