package com.test.application.karpov.exceptions;

public class UserNotFoundException extends RuntimeException{
    private Long id;

    public UserNotFoundException(Long id) {
        super("Could not find quiz " + id);
    }
}
