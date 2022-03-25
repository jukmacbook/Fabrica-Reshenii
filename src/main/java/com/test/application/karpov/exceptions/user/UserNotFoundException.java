package com.test.application.karpov.exceptions.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("Could not find quiz " + id);
    }
}
