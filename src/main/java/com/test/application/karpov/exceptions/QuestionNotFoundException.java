package com.test.application.karpov.exceptions;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
