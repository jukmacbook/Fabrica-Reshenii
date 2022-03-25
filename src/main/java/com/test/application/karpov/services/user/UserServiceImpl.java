package com.test.application.karpov.services.user;

import com.test.application.karpov.exceptions.user.UserNotFoundException;
import com.test.application.karpov.repositories.user.UserRepository;
import com.test.application.karpov.dto.Quiz;
import com.test.application.karpov.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final long ANONYMOUS_USER_ID = 1;

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
    public User getAnonymousUser() {
        User user = new User();
        user.setName("Anonymous");
        return save(user);
    }

    @Override
    public User save(User newUser) {

        return userRepository.save(newUser);
    }

    @Override
    public User update(User newUser, Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setQuizzes(newUser.getQuizzes());
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
    public List<Quiz> addQuiz(Long id, Quiz quiz) {
        List<Quiz> quizzes = findUserById(id).getQuizzes();
        if(Objects.isNull(quizzes)){
            quizzes = new ArrayList<>();
        }
        quizzes.add(quiz);

        return quizzes;
    }
}
