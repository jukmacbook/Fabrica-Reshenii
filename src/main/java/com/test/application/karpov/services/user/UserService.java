package com.test.application.karpov.services.user;

import com.test.application.karpov.dto.Quiz;
import com.test.application.karpov.dto.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findUserById(Long id);

    User save(User newUser);

    User getAnonymousUser();

    User update(User newUser, Long id);

    List<Quiz> addQuiz(Long id, Quiz quiz);

    void delete(Long id);
}
