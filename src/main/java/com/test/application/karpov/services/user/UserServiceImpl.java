package com.test.application.karpov.services.user;

import com.test.application.karpov.dto.Answer;
import com.test.application.karpov.exceptions.user.UserNotFoundException;
import com.test.application.karpov.repositories.user.UserRepository;
import com.test.application.karpov.dto.Quiz;
import com.test.application.karpov.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User save(User newUser) {

        return userRepository.save(newUser);
    }

    @Override
    public User update(User newUser, Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setSubmissions(newUser.getSubmissions());
                    user.setAnswers(newUser.getAnswers());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User addAnswers(User user, List<Answer> answers) {
        for (Answer answer : answers) {
            user.getAnswers().add(answer);
        }
        return user;
    }

    @Override
    public Quiz addQuiz(Long id, Quiz quiz) {
        return null;
    }
}
