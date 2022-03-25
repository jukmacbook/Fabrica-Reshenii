package com.test.application.karpov.services.user;

import com.test.application.karpov.exceptions.user.UserNotFoundException;
import com.test.application.karpov.repositories.user.UserRepository;
import com.test.application.karpov.dto.Quiz;
import com.test.application.karpov.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

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
    public User save(User newUser, Boolean isAnonymous) {
        if(isAnonymous){
            newUser.setName("Anonymous");
            return save(newUser, false);
        }
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
    public Set<Quiz> addQuiz(Long id, Quiz quiz) {
        Set<Quiz> quizzes = findUserById(id).getQuizzes();
        if(Objects.isNull(quizzes)){
            quizzes = new HashSet<>();
        }
        quizzes.add(quiz);

        return quizzes;
    }
}
