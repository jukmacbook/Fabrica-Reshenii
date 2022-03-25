package com.test.application.karpov.services.user;

import com.test.application.karpov.dto.Quiz;
import com.test.application.karpov.dto.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> findAll();

    User findUserById(Long id);

    User save(User newUser, Boolean isAnonymous);

    User update(User newUser, Long id);

    Set<Quiz> addQuiz(Long id, Quiz quiz);

    void delete(Long id);
}
