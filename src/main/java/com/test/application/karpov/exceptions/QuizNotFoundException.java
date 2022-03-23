package com.test.application.karpov.exceptions;

public class QuizNotFoundException extends RuntimeException {

    public QuizNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}