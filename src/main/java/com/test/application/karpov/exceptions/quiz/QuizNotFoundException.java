package com.test.application.karpov.exceptions.quiz;

public class QuizNotFoundException extends RuntimeException {

    public QuizNotFoundException(Long id) {
        super("Could not find quiz " + id);
    }
}